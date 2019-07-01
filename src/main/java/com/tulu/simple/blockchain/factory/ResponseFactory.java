package com.tulu.simple.blockchain.factory;

import com.tulu.simple.blockchain.controller.response.GeneralResponse;
import com.tulu.simple.blockchain.controller.response.ResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {

    public ResponseEntity success() {
        GeneralResponse<Object> responseObject = new GeneralResponse();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(ResponseStatus.SUCCESS_CODE);
        responseStatus.setMessage(ResponseStatus.SUCCESS_MESSAGE);
        responseObject.setStatus(responseStatus);
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity success(Object data, Class clazz) {
        GeneralResponse<Object> responseObject = new GeneralResponse();
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(ResponseStatus.SUCCESS_CODE);
        responseStatus.setMessage(ResponseStatus.SUCCESS_MESSAGE);
        responseObject.setStatus(responseStatus);
        responseObject.setData(clazz.cast(data));
        return ResponseEntity.ok(responseObject);
    }
}
