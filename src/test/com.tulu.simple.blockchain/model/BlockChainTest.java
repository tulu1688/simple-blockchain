package com.tulu.simple.blockchain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BlockChainTest {

    @Test
    public void test_proofOfWork() {
        BlockChain bc = new BlockChain();
        System.out.println(bc.proofOfWork(10L));
    }
}