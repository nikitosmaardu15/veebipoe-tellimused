package ee.nikita.tellimused;

import org.springframework.boot.SpringApplication;

public class TestVeebipoeTellimusedApplication {

    public static void main(String[] args) {
        SpringApplication.from(VeebipoeTellimusedApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
