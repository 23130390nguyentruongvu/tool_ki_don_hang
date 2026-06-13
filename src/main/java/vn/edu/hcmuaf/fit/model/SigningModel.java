package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.model.enums.Algorithm;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SigningModel {
    public String signOrder(String privateKeyInput, String hashData, String algorithm) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyInput);

        String coreAlgo = algorithm.contains(Algorithm.RSA.name())
                ? Algorithm.RSA.name() : Algorithm.DSA.name();

        //tạo ra đối tượng kí với loại "SHA256withRSA", "SHA256withDSA"
        Signature signatureInstance = Signature.getInstance(algorithm);
        signatureInstance.initSign(genPrivateKey(keyBytes, coreAlgo));

        signatureInstance.update(hashData.getBytes(StandardCharsets.UTF_8));
        byte[] digitalSignatureBytes = signatureInstance.sign();

        return Base64.getEncoder().encodeToString(digitalSignatureBytes);
    }

    private PrivateKey genPrivateKey(byte[] keyBytes, String coreAlgo) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(coreAlgo);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;
    }
}
