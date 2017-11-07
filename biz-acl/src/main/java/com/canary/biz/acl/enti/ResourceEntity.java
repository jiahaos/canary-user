package com.canary.biz.acl.enti;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ResourceEntity implements Serializable {

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
     * key资源标识
     */
    private String sourceKey;
    /*
     * 类型：菜单、按钮
     */
    private Integer type;
    /*
     * 菜单URL
     */
    private String sourceUrl;
    /*
     * 层级,即菜单的展开层级
     */
    private Integer level;
    /*
     * 图标,即菜单的图标
     */
    private String icon;
    /*
     * 是否隐藏
     */
    private Integer isHide;
    /*
     * 描述
     */
    private String description;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 更新时间
     */
    private Date updateTime;
    /*
     * 是否展开
     */
    private boolean isExpanded;
    /*
     * 是否叶子节点
     */
    private boolean isLeaf;
    /*
     * 是否加载完成
     */
    private boolean loaded = true;
    /*
     * 父节点名称
     */
    private String parentName;
    /*
     * 是否被选中
     */
    private boolean selected;

    private List<ResourceEntity> children = new ArrayList<ResourceEntity>();

    @Override
    public String toString() {
        return "ResourceEntity [id=" + id + ", parentId=" + parentId + ", name=" + name
                + ", sourceKey=" + sourceKey + ", type=" + type
                + ", sourceUrl=" + sourceUrl + ", level=" + level + ", icon="
                + icon + ", isHide=" + isHide + ", description=" + description
                + ", createTime=" + createTime + ", updateTime=" + updateTime
                + ", isExpanded=" + isExpanded + ", isLeaf=" + isLeaf
                + ", parentName=" + parentName + "]";
    }
}
