package org.humh.ysdt.service.impl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.humh.ysdt.dao.AccountDao;
import org.humh.ysdt.domain.Account;
import org.humh.ysdt.domain.City;
import org.humh.ysdt.param.Param;
import org.humh.ysdt.service.AccountService;
import org.humh.ysdt.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * 账号逻辑实现类
 * <p>
 * Created by humh on 05/08/2018.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取城市逻辑：
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     */
/*    public Account findCityById(Long id) {
        // 从缓存中获取城市信息
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            City city = operations.get(key);

            LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + city.toString());
            return city;
        }

        // 从 DB 中获取城市信息
        City city = cityDao.findById(id);

        // 插入缓存
        operations.set(key, city, 10, TimeUnit.SECONDS);
        LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());

        return city;
    }*/

    @Override
    public String login(String code) {
    	
    	String getOpenIdUrl = Param.GET_OPENID_URL + "?appid=" + Param.YSDT_APPID + "&secret=" + Param.YSDT_SECRET
    			+ "&js_code=" + code +"&grant_type" + Param.YSDT_GRANT_TYPE;
    	String responseBody = null;		
    	try {
    		responseBody = HttpUtil.httpGet(getOpenIdUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Gson gson = new Gson();
    	JsonObject obj = gson.fromJson(responseBody, JsonObject.class);
    	Account account = new Account();
    	String openid = "";
    	if(obj.has("openid")) {
    		openid = obj.get("openid").getAsString();
    		account.setOpenid(openid);
    	}
        // 从缓存中获取用户信息
        String key = "account_" + openid;
        ValueOperations<String, Account> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
        	account = operations.get(key);

            LOGGER.info("AccountServiceImpl.login() : 从缓存中获取了用户账号>> " + account.toString());
            return account.getOpenid();
        }

        //数据库存在
        account = accountDao.findByOpenid(openid);
    	if(account.getOpenid() != null) {
	        // 插入缓存
	        operations.set(key, account, 10, TimeUnit.DAYS);
            LOGGER.info("AccountServiceImpl.login() : 从数据库中获取了用户账号>> " + account.toString());
            return account.getOpenid();
    	}
    	
    	try {

			accountDao.saveAccount(account);
		} catch (MySQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return openid;
    }

}
