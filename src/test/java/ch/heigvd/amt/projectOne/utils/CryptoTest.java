package ch.heigvd.amt.projectOne.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptoTest {

    @Test
    void passwordShouldBeDifferentAfterCrypto() {
        String pwd = "Hello";
        assertEquals(pwd, "Hello");
        pwd = Crypto.getCryptoHash(pwd);
        assertNotEquals(pwd, "Hello");
    }
}