package com.example.one.service.impl;

import com.example.one.dao.operation.OperationUserDaoI;
import com.example.one.dao.query.QueryUserDaoI;
import com.example.one.entity.SysUser;
import com.example.one.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private QueryUserDaoI queryUserDao;
	@Autowired
	private OperationUserDaoI operationUserDao;
	
	@Override
	public SysUser selectById(Integer id) {
		try {
			queryUserDao.selectById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryUserDao.selectById(id);
	}

	@Override
	public SysUser login(String username, String password) {
		SysUser u = queryUserDao.login(username, password);
		return u;
	}

	@Override
	public SysUser register(SysUser sysUser) {
		operationUserDao.save(sysUser);
		return sysUser;
	}

}