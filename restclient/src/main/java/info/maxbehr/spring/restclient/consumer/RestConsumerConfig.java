package info.maxbehr.spring.restclient.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestConsumerConfig {

    private static final String BASE_URL = "http://localhost:8080/rest/notes";

    @Bean
    public RestClient restClient(RestClient.Builder builder, ClientLoggerRequestInterceptor requestInterceptor){
        return builder
                .baseUrl(BASE_URL)
                .requestInterceptor(requestInterceptor)
                .build();
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(BASE_URL)
                .build();
    }
}
