package info.maxbehr.spring.restclient.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.maxbehr.spring.restclient.model.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(components = {NotesClient.class, RestConsumerConfig.class, ClientLoggerRequestInterceptor.class})
class NotesClientTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private NotesClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindAllNotes() throws JsonProcessingException {
        var notes = List.of(
                new Note(1, "first", "content1"),
                new Note(2, "second", "content2")
        );

        server.expect(requestTo("http://localhost:8080/rest/notes"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(notes), MediaType.APPLICATION_JSON));

        List<Note> allNotes = client.findAllNotes();
        assertThat(allNotes).hasSize(2);
    }

    @Test
    void shouldFindSingleNote() throws JsonProcessingException {
        var note = new Note(1, "first", "content1");

        server.expect(requestTo("http://localhost:8080/rest/notes/1"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(note), MediaType.APPLICATION_JSON));

        Note noteById = client.findNoteById(1);
        assertThat(noteById).isEqualTo(note);
    }

    @Test
    void shouldCreateNote() throws JsonProcessingException {
        var id = 1;
        var note = new Note(id, "first", "content1");

        server.expect(requestTo("http://localhost:8080/rest/notes/"))
                .andRespond(withSuccess((objectMapper.writeValueAsString(id)), MediaType.APPLICATION_JSON));

        int noteId = client.createNote(note);
        assertThat(noteId).isEqualTo(1);
    }

    @Test
    void shouldDeleteNote() throws JsonProcessingException {
        var note = new Note(1, "first", "content1");

        server.expect(requestTo("http://localhost:8080/rest/notes/1"))
                .andRespond(withSuccess((objectMapper.writeValueAsString(note)), MediaType.APPLICATION_JSON));

        var deletedNote = client.deleteNoteById(note.id());
        assertThat(deletedNote).isEqualTo(note);
    }
}