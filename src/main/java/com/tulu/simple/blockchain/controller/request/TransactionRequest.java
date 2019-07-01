package com.tulu.simple.blockchain.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Setter
@Getter
public class TransactionRequest {
    String sender;
    String recipient;
    BigDecimal amount;
}
