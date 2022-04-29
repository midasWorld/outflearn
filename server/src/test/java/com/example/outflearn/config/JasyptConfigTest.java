package com.example.outflearn.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class JasyptConfigTest {

    StandardPBEStringEncryptor encryptor;

    @Test
    void 코드_암호화된_문자열_출력() {
        // given
        String key = "key";
        String url = "url";
        String username = "username";
        String password = "password";

        setup(key);
        // when
        String encryptUrl = jasyptEncrypt(url);
        String encryptUsername = jasyptEncrypt(username);
        String encryptPassword = jasyptEncrypt(password);

        System.out.println("encryptUrl: " + encryptUrl);
        System.out.println("encryptUsername: " + encryptUsername);
        System.out.println("encryptPassword: " + encryptPassword);

        // then
        assertThat(jasyptDecrypt(encryptUrl), is(url));
        assertThat(jasyptDecrypt(encryptUsername), is(username));
        assertThat(jasyptDecrypt(encryptPassword), is(password));
    }

    void setup(String key) {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
    }

    private String jasyptEncrypt(String str) {
        return encryptor.encrypt(str);
    }

    private String jasyptDecrypt(String str) {
        return encryptor.decrypt(str);
    }
}