package com.tomwen.utils;

/**
 * User: wenzhihong
 * Date: 10/31/13
 * Time: 1:18 PM
 */
public class KeyNotFoundException extends RuntimeException {
    public KeyNotFoundException(String key, String formattingString) {
        super("Couldn't find key \"" + key + "\" in string \"" + formattingString + "\".");
    }
}
