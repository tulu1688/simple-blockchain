package com.tulu.simple.blockchain.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatus {
    public static final ResponseStatus SUCCESS = new ResponseStatus("success", "Success");

    public static String SUCCESS_CODE = "success";
    public static String SUCCESS_MESSAGE = "Success";

    private String code;
    private String message;
}
