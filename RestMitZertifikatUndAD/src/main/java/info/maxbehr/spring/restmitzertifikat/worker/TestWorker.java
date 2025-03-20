package info.maxbehr.spring.restmitzertifikat.worker;

import info.maxbehr.spring.restmitzertifikat.worker.model.Input;
import info.maxbehr.spring.restmitzertifikat.worker.model.Output;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.response.CompleteJobResponse;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import org.springframework.stereotype.Component;

@Component
public class TestWorker {

    @JobWorker(type = "springworker")
    public void execute(final JobClient jobClient, final ActivatedJob activatedJob, @VariablesAsType Input input) {
        System.out.println("Input: " + input);
        System.out.println("Check: https://bru-2.operate.camunda.io/3b0fc391-7ab3-4b20-bcb5-1903e5ba2bda/processes/" + activatedJob.getProcessInstanceKey());
        var output = new Output(input.workerInput().toUpperCase());
        jobClient.newCompleteCommand(activatedJob.getKey())
                .variables(output)
                .send()
                .join();
    }
}
