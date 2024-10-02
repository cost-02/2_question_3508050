package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;

public class TrustedRootCertificates {

    public static void main(String[] args) {
        try {
            // Carica il keystore di default dei certificati fidati di Java
            String filename = System.getProperty("java.home") + "/lib/security/cacerts".replace('/', java.io.File.separatorChar);
            FileInputStream is = new FileInputStream(filename);

            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            String password = "changeit"; // Password di default del keystore cacerts
            keystore.load(is, password.toCharArray());

            // Ottenere gli alias dei certificati nel keystore e stamparli
            Enumeration<String> aliases = keystore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                if (keystore.isCertificateEntry(alias)) {
                    Certificate certificate = keystore.getCertificate(alias);
                    System.out.println("Alias: " + alias);
                    System.out.println("Certificate: " + certificate.toString());
                }
            }
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            e.printStackTrace();
        }
    }
}
