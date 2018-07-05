package com.htzhny.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.UserDao;
import com.htzhny.entity.User;
import com.htzhny.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired(required=true)
	UserDao userDao;
	@Transactional(readOnly=true)//登录核对，显示个人信息，
	@Override
	public User findUserByUserName(String user_name){
		return userDao.findUserByUserName(user_name);
	}
	//添加用户
	@Override
	public Integer addUser(User user){
		return userDao.addUser(user);
	}
	//修改密码
	@Override
	public Integer updatePassword(String user_name,String password){
		return userDao.updatePassword(user_name, password);
	}
	//修改个人信息
	@Override
	public Integer updateMessage(User user){
		return userDao.updateMessage(user);
	}
	@Override
	public List<User> selectAllUser() {
		return userDao.selectAllUser();
	}
	@Override
	public User selectUserByUserId(String user_id) {
		// TODO Auto-generated method stub
		return userDao.selectUserByUserId(user_id);
	}
	@Override
	public Integer updateUserId(String user_name, String openId) {
		// TODO Auto-generated method stub
		return userDao.updateUserId(user_name, openId);
	}

}
