package com.lyf.es.model.po.tyy.tab;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sys_user_info", schema = "common", catalog = "")
public class SysUserInfo  implements Serializable {

	private static final long serialVersionUID = -4294461808433313537L;
 	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "username")
	private String username;

	@JSONField(serialize = true)
	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private Integer role;

	@Column(name = "role_level")
	private Integer roleLevel;

	@Column(name = "inst_id")
	private Integer instId;

	@Column(name = "adminsuper")
	private Integer adminsuper;

	@Column(name = "real_name")
	private String realName;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "email")
	private String email;

	@Column(name = "note")
	private String note;

	@JSONField(name="createTime",format="yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time")
	private Date createTime;


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

	public Integer getRole(){
		return role;
	}

	public void setRole(Integer role){
		this.role=role;
	}

	public Integer getRoleLevel(){
		return roleLevel;
	}

	public void setRoleLevel(Integer roleLevel){
		this.roleLevel=roleLevel;
	}

	public Integer getInstId(){
		return instId;
	}

	public void setInstId(Integer instId){
		this.instId=instId;
	}

	public Integer getAdminsuper(){
		return adminsuper;
	}

	public void setAdminsuper(Integer adminsuper){
		this.adminsuper=adminsuper;
	}

	public String getRealName(){
		return realName;
	}

	public void setRealName(String realName){
		this.realName=realName;
	}

	public String getTelephone(){
		return telephone;
	}

	public void setTelephone(String telephone){
		this.telephone=telephone;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getNote(){
		return note;
	}

	public void setNote(String note){
		this.note=note;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

}
