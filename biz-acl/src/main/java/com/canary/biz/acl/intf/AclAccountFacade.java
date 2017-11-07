package com.canary.biz.acl.intf;

import com.canary.biz.acl.infra.AclConstants;
import com.canary.biz.acl.srv.AclAccountService;
import com.canary.biz.acl.srv.AclPermissionService;
import com.canary.core.acl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jiahao on 2017/4/8.
 */
@Controller
public class AclAccountFacade {

    @Autowired
    private AclAccountService aclAccountService;

    @Autowired
    private AclPermissionService aclPermissionService;

    /**
     * 获取帐号基本信息,通过帐号Id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/acl/account/account_id")
    @ResponseBody
    public AclAccountDetailDTO getAccountInfoByAccountId(int accountId) {
        return aclAccountService.getAccountInfoByAccountId(accountId);
    }

    /**
     * 获取帐号集合
     */
    @RequestMapping(method = RequestMethod.POST, value = "/acl/account/account_by_ids")
    @ResponseBody
    public List<AclAccountDetailDTO> getAccountDTOByIds(@RequestBody List<Integer> ids) {
        return aclAccountService.getAccountInfoDTOByIds(ids);
    }

    /**
     * 获取帐号基本信息,通过帐号
     */
    @RequestMapping(method = RequestMethod.GET, value = "/acl/account/account")
    @ResponseBody
    public AclAccountDTO getAccountInfoByAccount(String account) {
        return aclAccountService.getAccountInfoByAccount(account);
    }

    /**
     * 获取权限菜单，通过帐号Id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/acl/permission/account")
    @ResponseBody
    public List<AclPermissionDTO> getPermissionByAccountId(int accountId) {
        return aclPermissionService.getPermissionByAccountId(accountId);
    }

    /**
     * 修改密码
     */
    @RequestMapping(method = RequestMethod.POST, value = "/acl/account/modify_password")
    @ResponseBody
    public boolean modifyPassword(@RequestBody ArgsModifyPassword dto) {
        return aclAccountService.modifyPassword(dto);
    }

    /**
     * 获取帐号密码
     */
    @RequestMapping(method = RequestMethod.POST, value = "/acl/account/modify_default_password")
    @ResponseBody
    public boolean modifyDefPassword(@RequestBody ArgsModifyPassword dto) {
        return aclAccountService.modifyDefPassword(dto);
    }

    /**
     * 获取帐号密码
     */
    @RequestMapping(method = RequestMethod.POST, value = "/acl/account/modify_account")
    @ResponseBody
    public boolean modifyAccount(@RequestBody AclAccountDTO dto) {
        return aclAccountService.modifyAccount(dto);
    }

    /**
     * 添加用户账户
     */
    @RequestMapping(method = RequestMethod.POST, value = "/acl/account/add_user_account")
    @ResponseBody
    public Integer addUserAccount(@RequestBody ArgsAddUserDTO dto) {
        return aclAccountService.addUserAccount(dto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/acl/account/add_user_account_role")
    @ResponseBody
    public Integer addUserAccountWithRole(@RequestBody ArgsAddUserRoleDTO dto) {
        int code = aclAccountService.addUserAccount(
                dto.getUserAccountName(), dto.getUserName(), dto.getPassword()
        );
        if (AclConstants.SUCCESS != code) {
            return code;
        }
        return aclAccountService.addUserRole(new ArgsAddAclDTO(dto.getUserAccountName(), dto.getRoleId()));
    }

    /**
     * 根据用户名称获取用户ID
     */
    @RequestMapping(method = RequestMethod.POST, value = "/acl/account/userId_by_name")
    @ResponseBody
    public Integer getUserIdByName(@RequestParam String accountName) {
        return aclAccountService.getUserIdByName(accountName);
    }

    /**
     * 添加账户和角色关系
     */
    @RequestMapping(method = RequestMethod.POST, value = "/acl/account/add_user_role")
    @ResponseBody
    public Integer addUserRole(@RequestBody ArgsAddAclDTO dto) {
        return aclAccountService.addUserRole(dto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/acl/account/save_user_info")
    @ResponseBody
    public Integer saveAccountInfo(@RequestBody AclAccountInfoDTO dto) {
        return aclAccountService.saveAccountInfo(dto);
    }
}
