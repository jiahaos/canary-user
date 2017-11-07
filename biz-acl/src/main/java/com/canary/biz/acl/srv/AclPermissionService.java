package com.canary.biz.acl.srv;

import com.canary.biz.acl.enti.ResourceEntity;
import com.canary.biz.acl.infra.TreeUtil;
import com.canary.biz.acl.repo.AclResourceMapper;
import com.canary.core.acl.AclPermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiahao on 2017/3/18.
 */
@Controller
public class AclPermissionService {

    @Autowired
    private AclResourceMapper aclResourceMapper;

    public List<AclPermissionDTO> getPermissionByAccountId(long accountId) {
        List<AclPermissionDTO> permissions = new ArrayList<AclPermissionDTO>();
        List<ResourceEntity> resources = aclResourceMapper.findResourcesByUserId(accountId);
        for (ResourceEntity rs : resources) {
            permissions.add(build(rs));
        }
        permissions = new TreeUtil().getChildAclPermission(permissions, null);
        return permissions;
    }

    public AclPermissionDTO build(ResourceEntity rs) {
        AclPermissionDTO dto = new AclPermissionDTO();
        dto.setId(rs.getId());
        dto.setParentId(rs.getParentId());
        dto.setName(rs.getName());
        dto.setType(rs.getType());
        dto.setSourceUrl(rs.getSourceUrl());
        dto.setIcon(rs.getIcon());
        dto.setDescription(rs.getDescription());
        return dto;
    }
}
