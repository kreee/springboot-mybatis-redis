package org.humh.ysdt.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 * 城市实体类
 *
 * Created by bysocket on 07/02/2017.
 */
public class Account implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 小程序openid
     */
    private String openid;

    private String createTime;

    /**
     * 描述
     */
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", openid=" + openid +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                '}';
    }
}
