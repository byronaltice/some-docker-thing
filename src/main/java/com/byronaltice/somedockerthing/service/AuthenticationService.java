package com.byronaltice.somedockerthing.service;

import com.byronaltice.somedockerthing.handlers.AuthenticationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService {

    private final AuthenticationHandler handler;

    public String login(String request) {
        String user = this.handler.handleRequest(request);
        log.info("User authenticated {} with appId {}", user, request);
        return user;
    }
}
