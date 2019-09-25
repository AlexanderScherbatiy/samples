package sample;

import org.apache.commons.io.FileUtils;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphFactory;
import org.janusgraph.core.JanusGraphTransaction;
import org.janusgraph.graphdb.database.StandardJanusGraph;
import org.janusgraph.graphdb.idmanagement.IDManager;

import java.io.File;
import java.io.IOException;

public class JanusGraphAddEdgeSample {

    private static final String VERTEX_LABEL = "SampleVertexLabel";
    private static final String EDGE_LABEL = "SampleEdgeLabel";
    private static final String KEY = "SampleKey";
    private static final String VALUE = "SampleValue";
    private static final int N = 100;

    public static void main(String[] args) {
        addEdgesWithGeneratedIds();
        addEdgesWithCustomIds();
    }

    private static JanusGraph getJanusGraph(boolean customIds) {

//        String storageDirectory = "/tmp/janusgraph/sample";
//
//        removeDir(storageDirectory);
//
//        JanusGraphFactory.Builder builder = JanusGraphFactory
//                .build()
//                .set("storage.backend", "berkeleyje")
//                .set("storage.directory", String.format("%s/graph", storageDirectory))
//                //.set("query.force-index", true)
//                .set("index.search.backend", "lucene")
//                .set("index.search.directory", String.format("%s/index", storageDirectory));

        JanusGraphFactory.Builder builder = JanusGraphFactory
                .build()
                //.set("query.force-index", true)
                .set("storage.backend", "inmemory");

        if (customIds) {
            builder = builder.set("graph.set-vertex-id", "true");
        }

        return builder.open();
    }

    static void clearGraph(JanusGraph graph) {
        try (JanusGraphTransaction tx = graph.newTransaction()) {
            GraphTraversalSource g = tx.traversal();
            g.V().drop().iterate();
            tx.commit();
        }
    }

    static void printStatistics(JanusGraph graph, String msg) {
        try (JanusGraphTransaction tx = graph.newTransaction()) {
            GraphTraversalSource g = tx.traversal();
            long vertices = g.V().count().next();
            long edges = g.E().count().next();
            System.out.printf("%s vertices: %d, edges: %d%n", msg, vertices, edges);
        }
    }

    public static void addEdgesWithGeneratedIds() {

        System.out.printf("Add edges with generated ids%n");

        try (JanusGraph graph = getJanusGraph(false)) {

            clearGraph(graph);

            try (JanusGraphTransaction tx = graph.newTransaction()) {
                GraphTraversalSource g = tx.traversal();

                printStatistics(graph, "before");
                System.out.printf("N: %d%n", N);
                long time = System.currentTimeMillis();
                Vertex prev = null;
                for (int i = 1; i <= N; i++) {
                    String value = String.format("%s-%d", VALUE, i);
                    Vertex current = g
                            .addV(VERTEX_LABEL)
                            .property(KEY, value)
                            .next();

                    if (i > 1) {
                        g.addE(EDGE_LABEL).from(prev).to(current).next();
                    }

                    prev = current;
                }

                tx.commit();
                System.out.printf("elapsed time: %d(ms)%n", System.currentTimeMillis() - time);
            }

            printStatistics(graph, "after");

        }
    }

    public static void addEdgesWithCustomIds() {

        System.out.printf("Add edges with custom ids%n");

        try (JanusGraph graph = getJanusGraph(true)) {

            clearGraph(graph);

            IDManager idManager = ((StandardJanusGraph) graph).getIDManager();

            try (JanusGraphTransaction tx = graph.newTransaction()) {
                GraphTraversalSource g = tx.traversal();

                System.out.printf("N: %d%n", N);
                printStatistics(graph, "before");
                Vertex prev = null;
                long time = System.currentTimeMillis();

                for (int i = 1; i <= N; i++) {
                    long id = idManager.toVertexId(i);
                    String value = String.format("%s-%d", VALUE, i);
                    Vertex current = g.
                            addV(VERTEX_LABEL)
                            .property(T.id, id)
                            .property(KEY, value)
                            .next();

                    if (i > 1) {
                        g.addE(EDGE_LABEL).from(prev).to(current).next();
                    }

                    prev = current;
                }
                tx.commit();
                System.out.printf("elapsed time: %d(ms)%n", System.currentTimeMillis() - time);
            }

            printStatistics(graph, "after");
        }
    }

    private static void removeDir(String dir) {
        try {
            FileUtils.deleteDirectory(new File(dir));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
