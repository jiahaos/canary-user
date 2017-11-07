package com.canary.biz.acl.repo;

import com.canary.biz.acl.enti.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface AclRoleMapper extends BaseMapper<RoleEntity, Long> {

    /**
     * 查询该角色是否有权限信息
     * roleId	角色id
     * 使用@Param注解主要是设置mapping中可以使用参数名进行取值
     *
     * @return
     */
    public int findRoleResourceById(@Param(value = "roleId") int roleId);

    /**
     * 删除角色的权限信息
     * roleId	角色id
     * 使用@Param注解主要是设置mapping中可以使用参数名进行取值
     *
     * @return
     */
    public int deleteRoleResource(@Param(value = "roleId") int roleId);

    /**
     * 添加角色和权限映射信息
     * roleId	角色id
     * list(Long)
     *
     * @return
     */
    public int addRoleResource(Map<String, Object> parameter);


}
