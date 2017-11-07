package com.canary.biz.acl.enti;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class RoleEntity implements Serializable {

	private Integer id;

	private String name;
	
	private String key;

	private Integer status;

	private String description;

	private Date createTime;

	private Date updateTime;
	
	private List<UserEntity> userList;

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", name=" + name + ", key=" + key + ", status="
				+ status + ", description=" + description + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", userList="
				+ userList + "]";
	}

}