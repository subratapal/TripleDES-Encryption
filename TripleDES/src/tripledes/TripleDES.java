/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tripledes;

/**
 *
 * @author Subrata
 */
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.security.Key;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class TripleDES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        byte[] key = "1234567812345678123456sdbhkjasd78".getBytes();
        byte[] data = "Subrata".getBytes();
        Key deskey = null;
        DESedeKeySpec spec;
        
        try{
            spec = new DESedeKeySpec(key);
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);
            
            Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
            
            long startTime = System.currentTimeMillis();
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] CipherText = cipher.doFinal(data);
            StringBuffer hexCiphertext = new StringBuffer();
            
            for(int i=0;i<CipherText.length;i++){
                hexCiphertext.append(Integer.toString((CipherText[i]&0xff)+0x100,16).substring(1));
            }
          
            long elapsedtime = System.currentTimeMillis()-startTime;                        
            System.out.println("Ciphertext is "+hexCiphertext);
            System.out.println("Encryption Elapsed Time Milliseconds : "+ elapsedtime );
            
            startTime = System.currentTimeMillis();
            cipher.init(Cipher.DECRYPT_MODE,deskey);
            byte[] plaintext = cipher.doFinal(CipherText);
            elapsedtime = System.currentTimeMillis()-startTime;
            
            System.out.println("Plain Text is " + new String(plaintext));
             System.out.println("Decryption Elapsed Time Milliseconds : "+ elapsedtime );
            
        } catch (InvalidKeyException ex){
            Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE,null,ex);
        } catch (NoSuchAlgorithmException ex){
            Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE,null,ex);
        } catch (InvalidKeySpecException ex){
            Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE,null,ex);
        } catch (NoSuchPaddingException ex){
            Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE,null,ex);
        } catch(IllegalBlockSizeException ex){
            Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE,null,ex);
        } catch (BadPaddingException ex){
            Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
}
