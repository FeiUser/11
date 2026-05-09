package com.jta.entity.mysql.test1.tab;

import com.alibaba.fastjson.annotation.JSONField;
import com.jta.entity.Gender;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * user_info 实体类
 * Fri Dec 15 16:11:36 CST 2023
 * @casia
 */ 
@Entity
@ToString
@Table(name = "user", schema = "test1", catalog = "")
public class User implements Serializable {

	private static final long serialVersionUID = -4294461808433313537L;
 	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "sex")
	private Gender gender;

	@JSONField(name="createTime",format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_time")
	private Date updateTime;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username=username;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}

