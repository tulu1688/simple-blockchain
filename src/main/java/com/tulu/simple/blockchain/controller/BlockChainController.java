package com.tulu.simple.blockchain.controller;

import com.tulu.simple.blockchain.controller.request.TransactionRequest;
import com.tulu.simple.blockchain.controller.response.BlockChainResponse;
import com.tulu.simple.blockchain.factory.ResponseFactory;
import com.tulu.simple.blockchain.model.Block;
import com.tulu.simple.blockchain.service.AppInitializer;
import com.tulu.simple.blockchain.service.BlockChainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/v${api.version}")
public class BlockChainController {

    private static Logger logger = LogManager.getLogger(BlockChainController.class);

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private BlockChainService blockChainService;

    @RequestMapping(value = "/mine", method = RequestMethod.GET)
    public ResponseEntity mine(){
        Block lastBlock = blockChainService.getLastBlock();
        Long lastProof = lastBlock.getProof();
        Long newProof = blockChainService.proofOfWork(lastProof);

        blockChainService.newTransaction("0", AppInitializer.nodeIdentifier, BigDecimal.ONE);

        Block newBlock = blockChainService.newBlock(newProof);
        return responseFactory.success(newBlock, Block.class);
    }

    @RequestMapping(value = "/transaction/new", method = RequestMethod.POST)
    public ResponseEntity newTransaction(@RequestBody TransactionRequest request){
        long index = blockChainService.newTransaction(request.getSender(), request.getRecipient(), request.getAmount());
        logger.info("New transaction [{}] will be added into block [{}]", request, index);
        return responseFactory.success();
    }

    @RequestMapping(value = "/chain", method = RequestMethod.GET)
    public ResponseEntity viewFullChain(){
        BlockChainResponse blockChainResponse = new BlockChainResponse();
        blockChainResponse.setBlockChain(blockChainService.getChain());
        return responseFactory.success(blockChainResponse, BlockChainResponse.class);
    }
}
