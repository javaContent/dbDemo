package com.example.one.dao;

import com.example.one.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDaoI {

	public SysUser selectById(Integer id);

	public SysUser login(@Param(value="userName") String userName, @Param(value="password") String password);

	public SysUser findByUserName(String userName);

	public int save(SysUser sysUser);

	public List<SysUser> queryList();

}
