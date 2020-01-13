package com.example.two.service;


import com.example.two.entity.SysUser;

public interface UserServiceI {
	
	public SysUser selectById(Integer id);
	
	public SysUser login(String username, String password);

	public SysUser register(SysUser sysUser);

}
