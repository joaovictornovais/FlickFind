package br.com.flickfind.auth.utils;

import java.security.SecureRandom;

public class GenerateVerificationCode {

    private static final String CHARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String generateVerificationCode() {
        StringBuilder sb = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i=0; i<6; i++) {
            int index = secureRandom.nextInt(CHARACTERES.length());
            sb.append(CHARACTERES.charAt(index));
        }

        return sb.toString();
    }

}
