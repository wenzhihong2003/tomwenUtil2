package com.tomwen.utils;

/**
 * User: wenzhihong
 * Date: 10/31/13
 * Time: 1:17 PM
 */
public class MissingKeyException extends RuntimeException {
    public MissingKeyException(String key) {
        super("You didn't pass an arg for key " + key);
    }
}
