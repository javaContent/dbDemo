package com.example.two.service.impl;

import com.example.two.annotation.DataSourceSign;
import com.example.two.common.ContextConst;
import com.example.two.dao.UserDaoI;
import com.example.two.entity.SysUser;
import com.example.two.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserDaoI userDao;

//	@DataSourceSign(ContextConst.DataSourceType.SLAVE)
	@Override
	public SysUser selectById(Integer id) {
		try {
			userDao.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDao.selectById(id);
	}

	@Override
	public SysUser login(String username, String password) {
		SysUser u = userDao.login(username, password);
		return u;
	}

	@Override
	public SysUser register(SysUser sysUser) {
		userDao.save(sysUser);
		return sysUser;
	}

}