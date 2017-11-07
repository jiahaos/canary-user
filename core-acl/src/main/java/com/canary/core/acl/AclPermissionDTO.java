package com.canary.core.acl;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxg on 2016/4/8.
 */
@Data
public class AclPermissionDTO {

    /*
     * id
     */
    private Integer id;

    /*
     * 父节点ID
     */
    private Integer parentId;

    /*
     * 名称
     */
    private String name;

    /*
     * 类型：菜单、按钮
     */
    private Integer type;
    /*
     * 菜单URL
     */
    private String sourceUrl;

    /*
     * 图标,即菜单的图标
     */
    private String icon;

    /*
     * 描述
     */
    private String description;


    private List<AclPermissionDTO> children = new ArrayList<AclPermissionDTO>();


}
