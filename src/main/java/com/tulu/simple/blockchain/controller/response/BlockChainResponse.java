package com.tulu.simple.blockchain.controller.response;

import com.tulu.simple.blockchain.model.Block;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlockChainResponse {
    private List<Block> blockChain;
}
