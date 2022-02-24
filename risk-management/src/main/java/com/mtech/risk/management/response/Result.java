package com.mtech.risk.management.response;

import lombok.Data;

@Data
public class Result<T> {
    private boolean success;
    private String code;
    private String message;
    private T data;

    public static <T> Result<T> ok(T data) {
        Result<T> res = new Result<>();
        res.success = true;
        res.code = "OK";
        res.message = "";
        res.data = data;
        return res;
    }
}
