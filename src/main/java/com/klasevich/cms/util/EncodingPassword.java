package com.klasevich.cms.util;

import java.math.BigInteger;
import java.util.Base64;

public class EncodingPassword {
    public static String createEncodingPassword(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytesEncoded = encoder.encode(password.getBytes());
        BigInteger bigInteger = new BigInteger(1, bytesEncoded);
        String result = bigInteger.toString(16);
        return result;
    }
}
