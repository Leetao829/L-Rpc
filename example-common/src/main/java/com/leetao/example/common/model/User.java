package com.leetao.example.common.model;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author leetao
 */
public class User implements Serializable {

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
