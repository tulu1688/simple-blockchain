package com.tulu.simple.blockchain.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BlockChainServiceTest {

    @Test
    public void test_proofOfWork() {
        BlockChainService blockChainService = new BlockChainService();
        System.out.println(blockChainService.proofOfWork(16L));
    }

}