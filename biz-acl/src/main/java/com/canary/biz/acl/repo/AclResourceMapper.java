package com.canary.biz.acl.repo;

import com.canary.biz.acl.enti.ResourceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AclResourceMapper extends BaseMapper<ResourceEntity, Long> {

    /**
     * 自定义方法
     * 获取用户ID对应的资源信息
     *
     * @param userId
     * @return
     */
    public List<ResourceEntity> findResourcesByUserId(@Param(value = "userId") long userId);

    /**
     * 查询权限树集合
     *
     * @param parameter 参数中必须包含roleId,其他参数可参考mapping文件
     * @return
     */
    public List<ResourceEntity> queryResourceList(Map<String, Object> parameter);
}
