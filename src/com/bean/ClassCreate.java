package com.bean;

import java.io.Serializable;

public class ClassCreate implements Serializable{
	public String classname;
	public String sql;
	
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public ClassCreate(String classname, String sql) {
		this.classname = classname;
		this.sql = sql;
	}
	
	public ClassCreate()
	{
		
	}

}
