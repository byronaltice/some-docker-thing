package com.byronaltice.somedockerthing;

import com.byronaltice.somedockerthing.controller.AuthenticationController;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ServiceLauncherTests {

    @Autowired
    private AuthenticationController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNull();
    }

}
