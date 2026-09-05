import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CatsFactsService {

    private static final String URL =
            "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public List<CatFact> getFactsWithUpvotes() throws IOException {
        List<CatFact> allFacts = fetchFacts();

        return allFacts.stream()
                .filter(fact -> fact.getUpvotes() != null)
                .collect(Collectors.toList());
    }

    private List<CatFact> fetchFacts() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(URL);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String json = org.apache.http.util.EntityUtils.toString(response.getEntity());

            ObjectMapper mapper = new ObjectMapper();
            // Ответ сервера - это JSON-массив, поэтому парсим в CatFact[]
            CatFact[] factsArray = mapper.readValue(json, CatFact[].class);

            return List.of(factsArray);
        } finally {
            httpClient.close();
        }
    }
}
