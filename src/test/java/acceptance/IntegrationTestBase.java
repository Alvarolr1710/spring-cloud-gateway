package acceptance;

import gateway.Application;
import gateway.ApplicationTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTestBase {


    @LocalServerPort
    protected int port;

    @BeforeClass
    public static void startTestService() {
        testService = SpringApplication.run(ApplicationTest.TestService.class,
                "--server.port=8090");
    }

    @AfterClass
    public static void closeTestService() {
        testService.close();
    }

    private static ConfigurableApplicationContext testService;

}
