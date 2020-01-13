package com.example.one.dao.operation;

import com.example.one.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OperationUserDaoI {
	
	public int save(SysUser sysUser);

	public int delete(Integer userId);

	public int update(SysUser sysUser);

}
