package com.canary.biz.acl.enti;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserInfoEntity implements Serializable {

    private Integer id;

    private Integer sex;

    private Date birthday;

    private String telephone;

    private String email;

    private String address;

    private String avatar;

    private Date createTime;

    @Override
    public String toString() {
        return "UserInfo [id=" + id + ", sex=" + sex + ", birthday=" + birthday
                + ", telephone=" + telephone + ", email=" + email
                + ", address=" + address + ", createTime=" + createTime + "]";
    }

}
