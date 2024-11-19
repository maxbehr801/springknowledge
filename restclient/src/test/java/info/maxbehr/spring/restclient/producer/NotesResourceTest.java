package info.maxbehr.spring.restclient.producer;

import info.maxbehr.spring.restclient.model.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(NotesResource.class)
class NotesResourceTest {

    @Autowired
    private WebTestClient client;

    @Test
    void findAllNotes() {
        client.get()
                .uri("/rest/notes")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Note.class).hasSize(10);
    }

    @Test
    void findThirdNote() {
        var expectedNote = new Note(3, "third title", "third content");
        client.get()
                .uri("/rest/notes/2")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Note.class)
                .isEqualTo(expectedNote);
    }

    @Test
    void addAndDeleteNote() {
        var newNote = new Note(99, "magic title", "magic content");
        Integer id = client.post()
                .uri("/rest/notes")
                .bodyValue(newNote)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Integer.class)
                .returnResult()
                .getResponseBody();
        client.delete()
                .uri("/rest/notes/" + id)
                .exchange()
                .expectBody(Note.class)
                .isEqualTo(newNote);
    }
}