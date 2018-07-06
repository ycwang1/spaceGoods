package com.htzhny.dao;

import java.util.List;

import com.htzhny.entity.User;

public interface UserDao {
	public User findUserByUserName(String user_name) ;
	public Integer addUser(User user);
	public Integer updatePassword(String user_name,String password);
	public Integer updateMessage(User user);
	public List<User> selectAllUser();
	public User selectUserByUserId(String user_id);
	public Integer updateUserId(String user_name,String openId);
	public Integer addOneUser(User user);
}
