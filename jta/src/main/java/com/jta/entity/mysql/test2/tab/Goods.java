package com.jta.entity.mysql.test2.tab;

import javax.persistence.*;
import java.io.Serializable;

/**
 * user_info 实体类
 * Fri Dec 15 16:11:36 CST 2023
 * @casia
 */ 
@Entity
@Table(name = "goods", schema = "test2", catalog = "")
public class Goods implements Serializable {

	private static final long serialVersionUID = -4294461808433313537L;
 	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "name")
	private String name;




	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

