package com.byronaltice.somedockerthing.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ResourceService {

    public InputStream getResource(String file) {
        return this.getClass().getClassLoader().getResourceAsStream(file);
    }
}
