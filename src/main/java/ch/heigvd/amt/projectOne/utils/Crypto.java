package ch.heigvd.amt.projectOne.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {

    /**
     *
     * @param input a String that we want to hash with SHA-512 algorithme
     * @return The digest of the String
     */
    public static String getCryptoHash(String input) {

        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-512");
            byte[] inputDigest = msgDigest.digest(input.getBytes());
            BigInteger inputDigestBigInt = new BigInteger(1, inputDigest);
            String hashtext = inputDigestBigInt.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

