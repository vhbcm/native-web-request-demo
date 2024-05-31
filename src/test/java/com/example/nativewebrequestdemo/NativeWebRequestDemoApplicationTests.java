package com.example.nativewebrequestdemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class NativeWebRequestDemoApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(NativeWebRequestDemoApplicationTests.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void contextLoads() {
        final String result = this.restTemplate.getForObject("http://localhost:" + port + "/test", String.class);
        LOGGER.info("Received: {}", result);
        Assertions.assertThat(result).contains("Hello World from /test");
    }

}
