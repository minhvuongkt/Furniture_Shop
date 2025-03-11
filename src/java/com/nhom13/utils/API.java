package com.nhom13.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class API {
    public static String getName(String fullName){
        String name[] = fullName.split(" ");
        return name[name.length -1];
    }
    public static String getSHA256Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes());
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
