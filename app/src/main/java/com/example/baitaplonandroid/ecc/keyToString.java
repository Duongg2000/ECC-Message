/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecc3;

import static ecc3.Test.generateECKeys;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

/**
 *
 * @author Dươngg
 */
public class keyToString {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        //publickey 
      KeyPair keyPairA = generateECKeys();PublicKey public_key =keyPairA.getPublic();
        byte[] byte_pubkey = public_key.getEncoded();
        System.out.println("\nBYTE KEY::: " + byte_pubkey);
        //converting byte to String 
        String str_key = Base64.getEncoder().encodeToString(byte_pubkey);
        // String str_key = new String(byte_pubkey,Charset.);
        System.out.println("\nSTRING KEY::" + str_key);
        //converting string to Bytes
        byte_pubkey  = Base64.getDecoder().decode(str_key);
        System.out.println("BYTE KEY::" + byte_pubkey);
        //converting it back to public key
        KeyFactory factory = KeyFactory.getInstance("ECDSA", "BC");
        public_key = (BCECPublicKey) factory.generatePublic(new X509EncodedKeySpec(byte_pubkey));
        System.out.println("FINAL OUTPUT" + public_key);
        //privatekey
        
         String privKey = Base64.getEncoder().encodeToString(keyPairA.getPrivate().getEncoded());
        byte[] encoded = Base64.getDecoder().decode(privKey);
        KeyFactory kf = KeyFactory.getInstance("ECDSA", "BC");
        EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        PrivateKey privateKey = (BCECPrivateKey)kf.generatePrivate(keySpec);
         System.out.println("FINAL OUTPUT" + privateKey);
    }
     
}
