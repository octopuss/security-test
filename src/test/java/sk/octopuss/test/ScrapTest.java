package sk.octopuss.test;

import junit.framework.Assert;
import org.apache.commons.validator.routines.CodeValidator;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.apache.commons.validator.routines.RegexValidator;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.junit.Test;

/**
 * Created by ivan.dolezal.ext on 5.8.2015.
 */
public class ScrapTest {

    @Test
    public void luhnTest(){

        RegexValidator MAESTRO_REGEX = new RegexValidator(new String[] {
                "^(5[0678])(\\d{11,18})$", "^(6[^0357])\\d{11,18}$",
        "^(601)[^1]\\d{9,16}$",
        "^(6011)\\d{9,11}$",
        "^(6011)\\d{13,16}$",
        "^(65)\\d{11,13}$",
        "^(65)\\d{15,18}$",
        "^(633)[^34](\\d{9,16}$)",
        "^(6333)[0-4](\\d{8,10}$)",
        "^(6333)[0-4](\\d{12}$)",
        "^(6333)[0-4](\\d{15}$)",
        "^(6333)[5-9](\\d{8,10}$)",
        "^(6333)[5-9](\\d{12}$)",
        "^(6333)[5-9](\\d{15}$)",
        "^(6334)[0-4](\\d{8,10}$)",
        "^(6334)[0-4](\\d{12}$)",
        "^(6334)[0-4](\\d{15}$)",
        "^(67)[^(59)](\\d{9,16}$)",
        "^(6759)](\\d{9,11}$)",
        "^(6759)](\\d{13}$)",
        "^(6759)](\\d{16}$)",
        "^(67)[^(67)](\\d{9,16}$)",
        "^(6767)](\\d{9,11}$)",
        "^(6767)](\\d{13}$)",
        "^(6767)](\\d{16}$)"});
        CodeValidator codeValidator = new CodeValidator("^(5[06-8]|6\\d)(\\d{10,17})$", LuhnCheckDigit.LUHN_CHECK_DIGIT);
        CodeValidator[] codeValidators = {codeValidator};
        CreditCardValidator creditCardValidator = new CreditCardValidator(codeValidators);
        //Assert.assertTrue(creditCardValidator.isValid("6759649826438453"));
        Assert.assertTrue(creditCardValidator.isValid("5892290000217106"));
        Assert.assertTrue(creditCardValidator.isValid("6759649826438453"));
        Assert.assertTrue(creditCardValidator.isValid("6799990100000000019"));
        Assert.assertTrue(creditCardValidator.isValid("6759000000005"));
        Assert.assertTrue(creditCardValidator.isValid("5020328220504211"));
        Assert.assertTrue(creditCardValidator.isValid("6304591116606219"));
        Assert.assertTrue(creditCardValidator.isValid("6763839380591110"));
        Assert.assertTrue(creditCardValidator.isValid("6777000000007"));
        Assert.assertTrue(creditCardValidator.isValid("6766000000000"));

    }





}
