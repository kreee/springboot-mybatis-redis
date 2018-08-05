package org.humh.ysdt.dao;

import org.apache.ibatis.annotations.Param;
import org.humh.ysdt.domain.Account;
import org.humh.ysdt.domain.City;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 *  账号 DAO 接口类
 *
 * Created by humh on 05/08/2018.
 */
public interface AccountDao {

    /**
     *    根据openid
     *
     * @param openid
     * @return
     */
	Account findByOpenid(@Param("openid") String openid);

    Long saveAccount(Account account) throws MySQLIntegrityConstraintViolationException;

}
