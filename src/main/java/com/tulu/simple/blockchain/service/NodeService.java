package com.tulu.simple.blockchain.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class NodeService {
    private static Logger logger = LogManager.getLogger(NodeService.class);

    private static HashSet<String> nodes = new HashSet<>();

    public List<String> getNodes(){
        return new ArrayList(nodes);
    }

    public void registerNewNodes(List<String> nodeUrls) {
        for (String url : nodeUrls)
            nodes.add(url);
    }
}
