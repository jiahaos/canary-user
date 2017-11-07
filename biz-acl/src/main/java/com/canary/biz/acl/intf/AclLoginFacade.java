package com.canary.biz.acl.intf;

import com.canary.biz.acl.srv.AclLoginService;
import com.canary.core.acl.AclAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jiahao on 2017/4/8.
 */
@Controller
public class AclLoginFacade {

    @Autowired
    private AclLoginService aclLoginService;

    /**
     * 登录验证
     */
    @RequestMapping(method = RequestMethod.GET, value = "/acl/login")
    @ResponseBody
    public AclAccountDTO login(String account, String passwd) {
        return aclLoginService.login(account, passwd);
    }

}
