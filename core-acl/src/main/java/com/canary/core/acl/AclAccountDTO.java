package com.canary.core.acl;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by yxg on 2016/4/8.
 */
@Data
public class AclAccountDTO implements Serializable {

    private static final long serialVersionUID = 6598588611507462486L;

    private Integer id;

    private String account;

    private String password;

    private List<String> authorities;

    private String userName;

    private String accountName;

    private Integer deleteStatus;

    private Integer locked;

    private String description;

    private String creatorName;

    private Date createTime;

    private Date updateTime;

    private int roleId;

    private String roleName;

    private String roleKey;


}
