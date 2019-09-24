package sample;

import org.apache.commons.io.FileUtils;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphFactory;
import org.janusgraph.core.JanusGraphTransaction;
import org.janusgraph.graphdb.database.StandardJanusGraph;
import org.janusgraph.graphdb.idmanagement.IDManager;

import java.io.File;
import java.io.IOException;

public class JanusGraphSample {

    private static final String LABEL = "SampleLabel";
    private static final String KEY = "SampleKey";
    private static final String VALUE = "SampleValue";
    private static final int N = 100;

    public static void main(String[] args) {
        addVerticesWithGeneratedIds();
        addVerticesWithCustomIds();
    }

    private static JanusGraph getJanusGraph(boolean customIds) {

        String storageDirectory = "/tmp/janusgraph/sample";

        removeDir(storageDirectory);

        JanusGraphFactory.Builder builder = JanusGraphFactory
                .build()
                .set("storage.backend", "berkeleyje")
                .set("storage.directory", String.format("%s/graph", storageDirectory))
                //.set("query.force-index", true)
                .set("index.search.backend", "lucene")
                .set("index.search.directory", String.format("%s/index", storageDirectory));

//        JanusGraphFactory.Builder builder = JanusGraphFactory
//                .build()
//                //.set("query.force-index", true)
//                .set("storage.backend", "inmemory");

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

    static long verticesCount(JanusGraph graph) {
        try (JanusGraphTransaction tx = graph.newTransaction()) {
            GraphTraversalSource g = tx.traversal();
            return g.V().count().next();
        }
    }

    public static void addVerticesWithGeneratedIds() {

        System.out.printf("Add vertices with generated ids%n");

        try (JanusGraph graph = getJanusGraph(false)) {

            clearGraph(graph);

            try (JanusGraphTransaction tx = graph.newTransaction()) {
                GraphTraversalSource g = tx.traversal();

                System.out.printf("N: %d%n", N);
                long time = System.currentTimeMillis();
                for (int i = 1; i <= N; i++) {
                    String value = String.format("%s-%d", VALUE, i);
                    g.addV(LABEL).property(KEY, value).next();
                }
                tx.commit();
                System.out.printf("elapsed time: %d(ms)%n", System.currentTimeMillis() - time);
            }

            long vertices = verticesCount(graph);
            System.out.printf("added vertices: %d%n", vertices);
        }
    }

    public static void addVerticesWithCustomIds() {

        System.out.printf("Add vertices with custom ids%n");

        try (JanusGraph graph = getJanusGraph(true)) {

            clearGraph(graph);

            IDManager idManager = ((StandardJanusGraph) graph).getIDManager();

            try (JanusGraphTransaction tx = graph.newTransaction()) {
                GraphTraversalSource g = tx.traversal();

                System.out.printf("N: %d%n", N);
                long time = System.currentTimeMillis();
                for (int i = 1; i <= N; i++) {
                    long id = idManager.toVertexId(i);
                    String value = String.format("%s-%d", VALUE, i);
                    g.addV(LABEL).property(T.id, id).property(KEY, value).next();

                }
                tx.commit();
                System.out.printf("elapsed time: %d(ms)%n", System.currentTimeMillis() - time);
            }

            long vertices = verticesCount(graph);
            System.out.printf("added vertices: %d%n", vertices);
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
