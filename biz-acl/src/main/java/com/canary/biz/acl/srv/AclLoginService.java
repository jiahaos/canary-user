package com.canary.biz.acl.srv;

import com.canary.biz.acl.enti.UserEntity;
import com.canary.biz.acl.repo.AclUserMapper;
import com.canary.core.acl.AclAccountDTO;
import com.jaf.tools.security.ApMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by jiahao on 2017/4/8.
 */
@Controller
public class AclLoginService {

    @Autowired
    private AclUserMapper aclUserMapper;

    public AclAccountDTO login(String account, String passwd) {
        AclAccountDTO aclAccountDTO = new AclAccountDTO();

        UserEntity userEntity = aclUserMapper.findByName(account);
        if (userEntity != null) {
            if ("1".equals(userEntity.getDeleteStatus())) {
                throw new IllegalAccessError("帐号不可用"); // 帐号不可用
            }
            if ("2".equals(userEntity.getLocked())) {
                throw new IllegalAccessError("帐号被锁定"); // 帐号被锁定
            }
            String inputPasswd = ApMD5Util.getMD5(passwd);
            String userPasswd = userEntity.getPassword();

            if (!userPasswd.equals(inputPasswd))
                throw new IllegalAccessError("密码错误");

            aclAccountDTO.setId(userEntity.getId());
            aclAccountDTO.setAccount(account);
            aclAccountDTO.setPassword(passwd);

            List<String> authorities = aclUserMapper.findApiUrlsByAccountId(userEntity.getAccountName());
            aclAccountDTO.setAuthorities(authorities);
            return aclAccountDTO;
        } else {
            throw new IllegalAccessError("没找到帐号");// 没找到帐号
        }
    }
}
