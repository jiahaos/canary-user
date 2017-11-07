package com.canary.core.acl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by liubh on 16/6/20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArgsModifyPassword {

    private int accountId;
    private String account;
    private String orgPassword;
    private String newPassword;
}
