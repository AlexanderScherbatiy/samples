package sample;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphFactory;
import org.janusgraph.core.JanusGraphTransaction;

public class JanusGraphSample {

    public static JanusGraph getJanusGraph() {
        return JanusGraphFactory.open("inmemory");


//        String storageDirectory = "/tmp/samples/janusgraph";
//        return JanusGraphFactory.build()
//                .set("storage.backend", "berkeleyje")
//                //.set("storage.backend", "inmemory")
//                .set("storage.directory", String.format("%s/graph", storageDirectory))
//                .set("query.force-index", true)
//                .open();
    }


    public static void main(String[] args) {
        System.out.printf("Hello, JanusGraph!%n");


        String label = "SampleLabel";
        String key = "sampleKey";
        String value = "sample_value_";

        int N = 100;

        try (JanusGraph graph = getJanusGraph();
             JanusGraphTransaction tx = graph.newTransaction()) {
            long time = System.currentTimeMillis();
            System.out.printf("N: %d%n", N);
            GraphTraversalSource g = tx.traversal();

            for (int i = 0; i < N; i++) {
                g.addV(label).property(key, value + i).next();
            }
            tx.commit();
            System.out.printf("elapsed time: %d(ms)%n", System.currentTimeMillis() - time);
        }
    }
}
