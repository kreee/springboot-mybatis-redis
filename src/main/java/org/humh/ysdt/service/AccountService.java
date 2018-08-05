package org.humh.ysdt.service;

/**
 * 账号信息相关接口
 *
 * Created by humh on 08/05/2018.
 */
public interface AccountService {
    /**
     * 	
     * 小程序微信登陆
     * @param code
     * @return
     */
    String login(String code);

}
