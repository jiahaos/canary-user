package com.canary.core.acl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jiahao on 16/7/7.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArgsAddUserRoleDTO {

    private String userName;
    private String userAccountName;
    private String password;

    private int roleId;
}
