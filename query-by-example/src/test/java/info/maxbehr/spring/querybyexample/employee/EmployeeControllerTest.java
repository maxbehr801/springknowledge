package info.maxbehr.spring.querybyexample.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class EmployeeControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee sampleEmployee;
    private List<Employee> employeeList;

    @BeforeEach
    void setup() {
        sampleEmployee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .department("IT")
                .position("Software Engineer")
                .salary(new BigDecimal("85000.00"))
                .build();

        employeeList = Arrays.asList(
                sampleEmployee,
                Employee.builder()
                        .id(2L)
                        .firstName("Jane")
                        .lastName("Smith")
                        .department("HR")
                        .position("HR Manager")
                        .salary(new BigDecimal("75000.00"))
                        .build()
        );
    }
 }
