package info.maxbehr.spring.restclient.consumer;

import info.maxbehr.spring.restclient.model.Note;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class NotesClient {

    private final RestClient restClient;
    private final WebClient webClient;

    public NotesClient(RestClient restClient, WebClient webClient) {
        this.restClient = restClient;
        this.webClient = webClient;
    }

    public List<Note> findAllNotes() {
        return restClient.get()
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
