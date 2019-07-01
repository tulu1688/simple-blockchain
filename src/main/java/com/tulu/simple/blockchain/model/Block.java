package com.tulu.simple.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Block {
    private Long index;
    private Long timeStamp;
    private List<Transaction> transactionList;
    private Long proof;
    private String previousHash;

    @Override
    public String toString() {
        return "Block{" +
                "index=" + index +
                ", timeStamp=" + timeStamp +
                ", transactionList=" + transactionList +
                ", proof=" + proof +
                ", previousHash='" + previousHash + '\'' +
                '}';
    }
}
