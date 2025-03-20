package info.maxbehr.spring.restmitzertifikat.controller;

import io.camunda.zeebe.client.ZeebeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StartController {

    private static final String PROCESS_TEST = "Process_Test";
    private static final Logger log = LoggerFactory.getLogger(StartController.class);

    private final ZeebeClient zeebeClient;

    public StartController(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }

    // write a method that implements a get request to api/start with an empty body@GetMapping("/start")
    @GetMapping("/start")
    public String start() {
        log.info("Start");
        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(PROCESS_TEST)
                .latestVersion()
                .variables("{\"input\":\"ein kleiner text\"}")
                .send()
                .join();
        return "Secured REST API with certificate";
    }

}
