package org.humh.ysdt.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountServiceTest {

    @Autowired
    private AccountServiceImpl accountService;
    
    @Test
    public void login() throws Exception {
    	
    	System.out.println(accountService.login("0815wN6A1zk3Pe0Xcr7A1aV57A15wN6g"));
    }
}

