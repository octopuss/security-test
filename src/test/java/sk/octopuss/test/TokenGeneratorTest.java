package sk.octopuss.test;

/**
 * Created by ivan.dolezal.ext on 8.8.2015.
 */

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TokenGeneratorTest {

    @Autowired
    TokenGenerator tokenGenerator;

    @Value("${temporary.token.validity}")
    int temporaryTokenValidity;

    private static final String [] data = {"Something special"};

    @Test
    public void encryptDecryptTest(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND,temporaryTokenValidity);
        String token = tokenGenerator.generateToken(data,calendar.getTime());
        Assert.assertTrue(tokenGenerator.validateToken(token,data));
    }

    @Test
    public void expirationTest(){
        String token = tokenGenerator.generateToken(data);
        Assert.assertTrue(tokenGenerator.validateToken(token, data));
    }

    @Test
    public void tokenVerificationFailTest(){
        String token = tokenGenerator.generateToken(data);
        String [] notValidData = {"Not valid data"};
        Assert.assertFalse(tokenGenerator.validateToken(token, notValidData));
    }

    @Test
    public void expiredTokenFailTest(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND,-temporaryTokenValidity);
        String token = tokenGenerator.generateToken(data,calendar.getTime());
        Assert.assertFalse(tokenGenerator.validateToken(token, data));
    }




}
