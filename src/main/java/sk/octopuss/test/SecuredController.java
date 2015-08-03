package sk.octopuss.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ivan.dolezal.ext on 3.8.2015.
 */

@Controller
public class SecuredController {


    @RequestMapping("/data")
    public  String data(){
        return "data";
    }

    @RequestMapping("/rest")
    public @ResponseBody String rest(){
        return "rest";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


}
