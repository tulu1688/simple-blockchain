package com.tulu.simple.blockchain.service;

import com.google.common.hash.Hashing;
import com.tulu.simple.blockchain.controller.response.BlockChainResponse;
import com.tulu.simple.blockchain.controller.response.GeneralResponse;
import com.tulu.simple.blockchain.model.Block;
import com.tulu.simple.blockchain.model.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlockChainService {
    private static Logger logger = LogManager.getLogger(BlockChainService.class);

    private static List<Transaction> transactions = new ArrayList<>();
    private static List<Block> chain;

    @Autowired
    private NodeService nodeService;

    public BlockChainService(){
        transactions = new ArrayList<>();
        chain = new ArrayList<>();
        newBlock(16L);
    }

    public List<Block> getChain(){
        return chain;
    }

    public Block newBlock(Long proof){
        String previousHash = null;
        if (chain.size() > 0)
            previousHash = hash(chain.get(chain.size() - 1));

        Block block = new Block(
                Long.valueOf(chain.size() + 1),
                System.currentTimeMillis(),
                transactions,
                proof,
                previousHash);

        transactions = new ArrayList<>();
        chain.add(block);
        return block;
    }

    public long newTransaction(String sender, String recipient, BigDecimal amount){
        Transaction transaction = new Transaction(sender, recipient, amount);
        transactions.add(transaction);
        return getLastBlock().getIndex();
    }

    public String hash(Block block) {
        String sha256hex = Hashing.sha256()
                .hashString(block.toString(), StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }

    public Block getLastBlock(){
        if (chain.size() > 0)
            return chain.get(chain.size() - 1);
        return null;
    }

    public Long proofOfWork(Long lastProof){
        long proof = 0;
        while (!validProof(lastProof, proof)) {
            proof ++;
        }

        logger.info("Get next proof [{}] with generated hash [{}]", proof, computedHash(lastProof, proof));

        return proof;
    }

    private String computedHash(Long lastProof, Long proof) {
        String guess = "KhaiTN" + lastProof + "_" + proof;
        return Hashing.sha256().hashString(guess, StandardCharsets.UTF_8).toString();
    }

    private boolean validProof(Long lastProof, Long proof) {
        String guessHash = computedHash(lastProof, proof);
        return "0000".equals(guessHash.substring(guessHash.length() - 4));
    }

    public void resolveConflicts() {
        List<String> neighbours = nodeService.getNodes();
        int maxLength = chain.size();
        List<Block> newChain = null;

        for (String neighbour : neighbours) {
            List<Block> neighbourNodeChain = getNeighbourNodeChain("http://" + neighbour + "/simple-blockchain/v1.0/chain");
            if (neighbourNodeChain.size() > maxLength) {
                maxLength = neighbourNodeChain.size();
                newChain = neighbourNodeChain;
            }
        }

        if (newChain != null)
            chain = newChain;
    }

    private List<Block> getNeighbourNodeChain(String neighbourNodeUrl){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity request = new HttpEntity<>(httpHeaders);

        try {
            ResponseEntity<GeneralResponse<BlockChainResponse>> exchange = new RestTemplate().exchange(
                    neighbourNodeUrl,
                    HttpMethod.GET,
                    request,
                    new ParameterizedTypeReference<GeneralResponse<BlockChainResponse>>() {});

            return exchange.getBody().getData().getBlockChain();
        } catch (Exception ex) {
            logger.info("Failed to sent sms to mock server", ex);
            return new ArrayList<>();
        }
    }
}
