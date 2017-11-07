package com.canary.biz.acl.enti;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserEntity implements Serializable {

    private Integer id;
    /*
     * 用户真实姓名
     */
    private String userName;
    /*
     * 这里账户名称统一使用邮箱
     */
    private String accountName;

    private String password;

    private Integer deleteStatus;

    private Integer locked;

    private String description;

    private String credentialsSalt;

    private String creatorName;

    private Date createTime;

    private Date updateTime;

    private RoleEntity role;

    private UserInfoEntity userInfo;

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", userName=" + userName + ", accountName="
                + accountName + ", password=" + password + ", deleteStatus="
                + deleteStatus + ", locked=" + locked + ", description="
                + description + ", credentialsSalt=" + credentialsSalt
                + ", creatorName=" + creatorName + ", createTime=" + createTime
                + ", updateTime=" + updateTime + ", role=" + role + "]";
    }

}
