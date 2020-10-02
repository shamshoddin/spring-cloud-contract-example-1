package com.jd.spring.cloud.contract.create.provider;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
        , properties = {
        "management.server.port=0",
        "server.port=0"
}
)
@AutoConfigureMessageVerifier
public abstract class BaseClass {

    @LocalServerPort
    private int port;

    @Value("${app.employeeURI:http://localhost}")
    String employeeURI;

    @Value("${app.employeeBasePath:/employee-management/employee}")
    String employeeBasePath;

    @Before
    public void setup() {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = employeeURI;
        if (RestAssured.baseURI.contains("localhost")) {
            RestAssured.port = port;
        }
    }

    public String getUrlPath() {
        return employeeBasePath;
    }
}