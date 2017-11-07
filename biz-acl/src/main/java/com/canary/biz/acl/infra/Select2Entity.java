package com.canary.biz.acl.infra;

import lombok.Data;

import java.io.Serializable;

@Data
public class Select2Entity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String text;
	
	private String name;

}
