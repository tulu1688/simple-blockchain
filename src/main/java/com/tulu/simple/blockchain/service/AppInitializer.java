package com.tulu.simple.blockchain.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppInitializer implements CommandLineRunner {
    private static Logger logger = LogManager.getLogger(AppInitializer.class);

    public static String nodeIdentifier;

    @Override
    public void run(String...args) {
        nodeIdentifier = UUID.randomUUID().toString();
    }
}
