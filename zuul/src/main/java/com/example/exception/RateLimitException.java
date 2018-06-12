package com.example.exception;

/**
 * @author xuan
 * @create 2018-06-06 20:58
 **/
public class RateLimitException extends RuntimeException {
    public RateLimitException(){
        super();
    }
    public RateLimitException(String msg){
        super(msg);
    }
}
