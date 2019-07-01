package com.tulu.simple.blockchain.controller;

import com.tulu.simple.blockchain.controller.request.NodeListRequest;
import com.tulu.simple.blockchain.factory.ResponseFactory;
import com.tulu.simple.blockchain.service.BlockChainService;
import com.tulu.simple.blockchain.service.NodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v${api.version}")
public class NodeController {

    private static Logger logger = LogManager.getLogger(NodeController.class);

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private BlockChainService blockChainService;

    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    public ResponseEntity getNodes(){
        return responseFactory.success(nodeService.getNodes(), List.class);
    }

    @RequestMapping(value = "/nodes/register", method = RequestMethod.POST)
    public ResponseEntity registerNodes(@RequestBody NodeListRequest request){
        nodeService.registerNewNodes(request.getNodes());
        return responseFactory.success();
    }

    @RequestMapping(value = "/nodes/resolve", method = RequestMethod.GET)
    public ResponseEntity consensus(){
        blockChainService.resolveConflicts();
        return responseFactory.success();
    }
}
