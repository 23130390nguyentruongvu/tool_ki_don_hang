package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.model.enums.Algorithm;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class SigningModel {
    public String signOrder(String privateKeyInput, String hashData, String algorithm) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyInput);

        String coreAlgo = algorithm.contains(Algorithm.RSA.name())
                ? Algorithm.RSA.name() : Algorithm.DSA.name();

        Signature signatureInstance = Signature.getInstance(algorithm);
        signatureInstance.initSign(genPrivateKey(keyBytes, coreAlgo));

        byte[] hashBytes = hexStringToByteArray(hashData);

        signatureInstance.update(hashBytes);
        byte[] digitalSignatureBytes = signatureInstance.sign();

        return Base64.getEncoder().encodeToString(digitalSignatureBytes);
    }

    private PrivateKey genPrivateKey(byte[] keyBytes, String coreAlgo) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(coreAlgo);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;
    }

    //gom 2 hex thành 1 byte
    private byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
}
