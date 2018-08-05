package org.humh.ysdt.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/mini/login/{code}", method = RequestMethod.GET)
    public String login(@PathVariable("code") String code) {
    	
        return null;
    }
}
