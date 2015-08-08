package sk.octopuss.test;

import java.util.Date;

/**
 * Created by ivan.dolezal.ext on 8.8.2015.
 */
public interface TokenGenerator {

    String generateToken(String[] data, Date expirationDate);

    String generateToken(String[] data);

    Boolean validateToken(String token, String ... expectedData);
}
