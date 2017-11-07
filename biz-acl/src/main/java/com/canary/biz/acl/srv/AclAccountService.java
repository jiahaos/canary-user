package com.canary.biz.acl.srv;

import com.canary.biz.acl.enti.RoleEntity;
import com.canary.biz.acl.enti.UserEntity;
import com.canary.biz.acl.enti.UserInfoEntity;
import com.canary.biz.acl.infra.AclConstants;
import com.canary.biz.acl.repo.AclUserMapper;
import com.canary.core.acl.*;
import com.jaf.tools.bean.ApBeanUtils;
import com.jaf.tools.security.ApMD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jiahao on 2017/3/18.
 */
@Controller
public class AclAccountService {

    @Autowired
    private AclUserMapper aclUserMapper;

    private static Logger logger = LoggerFactory.getLogger(AclAccountService.class);

    public AclAccountDetailDTO getAccountInfoByAccountId(long accountId) {
        UserEntity userEntity = aclUserMapper.findById(accountId);
        if (userEntity == null) return null;

        AclAccountDTO aclAccountDTO = build(userEntity);
        AclAccountDetailDTO aclAccountDetailDTO = ApBeanUtils.map(aclAccountDTO, AclAccountDetailDTO.class);
        aclAccountDetailDTO.setAvatarKey(userEntity.getUserInfo() == null ? "" : userEntity.getUserInfo().getAvatar());
        return aclAccountDetailDTO;
    }

    public List<AclAccountDetailDTO> getAccountInfoDTOByIds(List<Integer> ids) {
        List<UserEntity> list = aclUserMapper.selectAccountDTOByIds(ids);
        List<AclAccountDetailDTO> accList = new ArrayList<AclAccountDetailDTO>();
        if (list == null || list.size() == 0)
            return accList;
        for (UserEntity entity : list) {
            AclAccountDTO aclAccountDTO = build(entity);
            AclAccountDetailDTO aclAccountDetailDTO = ApBeanUtils.map(aclAccountDTO, AclAccountDetailDTO.class);
            aclAccountDetailDTO.setAvatarKey(entity.getUserInfo() == null ? "" : entity.getUserInfo().getAvatar());
            accList.add(aclAccountDetailDTO);
        }
        return accList;
    }

    public AclAccountDTO getAccountInfoByAccount(String account) {
        UserEntity userEntity = aclUserMapper.findByName(account);
        if (userEntity == null) return null;
        return build(userEntity);
    }

    public Integer getUserIdByName(String accountName) {
        UserEntity entity = aclUserMapper.findAccountByAccountName(accountName);
        return entity == null ? null : entity.getId();
    }

    public boolean modifyPassword(ArgsModifyPassword dto) {
        logger.info("[Acl]接收到用户修改密码的申请. " + dto);
        UserEntity userEntity = aclUserMapper.findByName(dto.getAccount());
        String orgPassword = dto.getOrgPassword();
        if (!userEntity.getPassword().equals(ApMD5Util.getMD5(orgPassword))) {
            return false;
        }
        String newPassword = dto.getNewPassword();
        userEntity.setPassword(ApMD5Util.getMD5(newPassword));
        aclUserMapper.update(userEntity);
        return true;
    }

    public boolean modifyDefPassword(ArgsModifyPassword dto) {
        logger.info("[Acl]接收到用户修改密码的申请. " + dto);
        UserEntity userEntity = aclUserMapper.findByName(dto.getAccount());
        String newPassword = ApMD5Util.getMD5(dto.getNewPassword());
        userEntity.setPassword(newPassword);
        aclUserMapper.update(userEntity);
        return true;
    }

    public boolean modifyAccount(AclAccountDTO dto) {
        logger.info("[Acl]接收到用户账号申请. " + dto);
        UserEntity userEntity = aclUserMapper.findByName(dto.getAccount());
        userEntity.setUserName(dto.getUserName());
        aclUserMapper.update(userEntity);
        return true;
    }

    public Integer addUserAccount(ArgsAddUserDTO dto) {
        return addUserAccount(dto.getUserAccountName(), dto.getUserName(), "111111");
    }

    public Integer addUserAccount(String account, String username, String password) {
        logger.info("[Acl]接收到用户添加的申请. " + username + ",account:" + account);
        Integer userId = getUserIdByName(account);
        if (userId != null) {
            logger.info("[Acl]用户已存在." + account);
            return AclConstants.ACCOUNT_EXISTED;
        }
        UserEntity user = new UserEntity();
        user.setAccountName(account);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setCreatorName("admin");
        user.setLocked(0);
        user.setUserName(username);
        user.setDeleteStatus(0);
        user.setDescription("");
        user.setPassword(password);
//        user.setPassword(ApMD5Util.getMD5(password));
        aclUserMapper.insert(user);
        return AclConstants.SUCCESS;
    }

    public Integer addUserRole(ArgsAddAclDTO dto) {
        logger.info("[Acl]接收到用户角色添加的申请. " + dto);
        Integer userId = getUserIdByName(dto.getAccountName());
        if (userId == null) {
            logger.info("[Acl]接收根据用户名称获取用户ID失败. " + dto);
            return AclConstants.ACCOUNT_NO_EXIST;
        }
        UserEntity userEntity = aclUserMapper.findById(Long.valueOf(userId));
        if (userEntity != null) {
            RoleEntity role = userEntity.getRole();
            int id = role.getId();
            if (id != dto.getRoleId()) {
                logger.info("[Acl]该用户已经拥有该角色权限. " + dto);
                return AclConstants.ACCOUNT_ACL_EXISTED;
            }
        }

        UserEntity user = new UserEntity();
        user.setId(userId);
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(dto.getRoleId());
        user.setRole(roleEntity);
        aclUserMapper.insertUserRole(user);
        return AclConstants.SUCCESS;
    }

    public Integer saveAccountInfo(AclAccountInfoDTO dto) {
        UserEntity userEntity = aclUserMapper.findById(Long.valueOf(dto.getId()));
        // userInfo 对象默认会构建，利用Birthday属性进行判断
        if (userEntity.getUserInfo().getBirthday() == null) {
            userEntity.setUserInfo(buildUserInfoEntity(dto.getId(), dto.getAvatar()));
            aclUserMapper.insertUserInfo(userEntity);
        } else {
            userEntity.getUserInfo().setAvatar(dto.getAvatar());
            aclUserMapper.updateUserInfo(userEntity);
        }
        return AclConstants.SUCCESS;
    }

    private UserInfoEntity buildUserInfoEntity(int id, String avatar) {
        UserInfoEntity entity = new UserInfoEntity();
        entity.setId(id);
        entity.setAddress("");
        entity.setAvatar(avatar);
        entity.setBirthday(new Date(0));
        entity.setCreateTime(new Date(0));
        entity.setEmail("");
        entity.setSex(0);
        entity.setTelephone("");
        return entity;
    }

    private AclAccountDTO build(UserEntity userEntity) {
        AclAccountDTO dto = new AclAccountDTO();
        dto.setId(userEntity.getId());
        dto.setAccountName(userEntity.getAccountName());
        dto.setAccount(userEntity.getAccountName());
        dto.setPassword(userEntity.getPassword());
        dto.setUserName(userEntity.getUserName());
        dto.setDeleteStatus(userEntity.getDeleteStatus());
        dto.setCreatorName(userEntity.getCreatorName());
        dto.setCreateTime(userEntity.getCreateTime());
        dto.setUpdateTime(userEntity.getUpdateTime());
        dto.setLocked(userEntity.getLocked());
        dto.setDescription(userEntity.getDescription());
        dto.setRoleId(userEntity.getRole().getId());
        dto.setRoleName(userEntity.getRole().getName());
        dto.setRoleKey(userEntity.getRole().getKey());
        return dto;
    }
}
