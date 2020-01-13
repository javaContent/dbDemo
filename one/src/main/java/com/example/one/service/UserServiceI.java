package com.example.one.service;


import com.example.one.entity.SysUser;

public interface UserServiceI {
	
	public SysUser selectById(Integer id);
	
	public SysUser login(String username, String password);

	public SysUser register(SysUser sysUser);

}
