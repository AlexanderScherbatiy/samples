package particles.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ParticleSample {

    public static void main(String[] args) throws Exception {

        Particle photon = new Particle();
        photon.setName("Photon");
        photon.setCharge(0);
        photon.setMass(0);

        Particle electron = new Particle();
        electron.setName("Electron");
        electron.setCharge(-1);
        electron.setMass(9.1e-31);

        Particle[] particles = {photon, electron};

        RestClientBuilder clientBuilder = RestClient
                .builder(new HttpHost("localhost", 9200, "http"));

        try (RestHighLevelClient client = new RestHighLevelClient(clientBuilder)) {

            for (Particle particle : particles) {
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(particle);

                System.out.printf("json: %s%n", json);
                IndexRequest indexRequest = new IndexRequest("particles")
                        .id(particle.getName())
                        .source(json, XContentType.JSON);

                IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);

                System.out.printf("response: %s%n", response);
            }
        }
    }
}
