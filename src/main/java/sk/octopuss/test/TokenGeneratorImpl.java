package sk.octopuss.test;

import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * Created by ivan.dolezal.ext on 8.8.2015.
 */

@Service
public class TokenGeneratorImpl implements TokenGenerator {

    @Value("${temporary.token.password}")
    String temporaryTokenPassword;

    private static final String DATA_DELIMETER = "#";

    private static final String EXPIRES_NEVER_STRING = "NEVER";

    private static final String ALGORYTHM = "AES";


    Key key;

    private Key generateKey() throws Exception {
        key = new SecretKeySpec(temporaryTokenPassword.getBytes(), ALGORYTHM);
        return key;
    }

    @PostConstruct
    public void init() throws Exception{
        Security.addProvider(new BouncyCastleProvider());
        key = generateKey();
    }


    public String generateToken(String[] data, Date expirationDate) {
        String mergedData = StringUtils.arrayToDelimitedString(data,DATA_DELIMETER);
        if(expirationDate!=null) {
            mergedData = expirationDate.getTime() + DATA_DELIMETER + mergedData;
        } else {
            mergedData = EXPIRES_NEVER_STRING +DATA_DELIMETER +  mergedData;
        }

        try {
            Cipher cipher =  Cipher.getInstance(ALGORYTHM, "BC");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = cipher.doFinal(mergedData.getBytes());
            String encryptedValue = new BASE64Encoder().encode(encVal);
            return encryptedValue;
        } catch (Exception e) {
          e.printStackTrace();
        }
        return null;
    }

    public Boolean validateToken(String token, String ... expectedData) {

        try {
            boolean isValid = false;
            Cipher cipher = Cipher.getInstance(ALGORYTHM, "BC");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedValue = new BASE64Decoder().decodeBuffer(token);
            byte[] decValue = cipher.doFinal(decodedValue);
            String decryptedValue = new String(decValue);
            System.out.print(decryptedValue);

            String[] data=decryptedValue.split(DATA_DELIMETER);
            isValid = Objects.deepEquals(expectedData,Arrays.copyOfRange(data,1, data.length));

            if(!data[0].equals(EXPIRES_NEVER_STRING)) {
                Date expires = new Date(Long.valueOf(data[0]));
                if(expires.compareTo(new Date())<0) {
                    isValid = false;
                }
            }
            return isValid;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateToken(String[] data) {
        return generateToken(data, null);
    }
}