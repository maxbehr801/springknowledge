package info.maxbehr.spring.restmitzertifikat.config;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.context.annotation.Configuration;

@Configuration
@Deployment(resources = "classpath:/processes/process.bpmn")
public class CamundaProcessDeployment {
}
