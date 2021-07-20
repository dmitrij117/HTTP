import fact.Cat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        final CloseableHttpClient httpclient = HttpClients.createDefault();
        final ObjectMapper mapper = new ObjectMapper();
        HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
        try {
            CloseableHttpResponse response = httpclient.execute(request);
            List<Cat> catList = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<Cat>>() {
            });
            List<Cat> finalCatList = catList.stream().filter(value -> value.getUpvotes() != null).collect(Collectors.toList());
            finalCatList.forEach(System.out::println);
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}