package sk.octopuss.test;

import org.apache.commons.validator.routines.checkdigit.CheckDigit;
import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by ivan.dolezal.ext on 5.8.2015.
 */
public class CustomLuhnCheckDigit implements CheckDigit {
    public String calculate(String code) throws CheckDigitException {
        throw new NotImplementedException();
    }

    public boolean isValid(String code) {
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(code).reverse().toString();
        for(int i = 0 ;i < reverse.length();i++){
            int digit = Character.digit(reverse.charAt(i), 10);
            if(i % 2 == 0){//this is for odd digits, they are 1-indexed in the algorithm
                s1 += digit;
            }else{//add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                s2 += 2 * digit;
                if(digit >= 5){
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }
}
