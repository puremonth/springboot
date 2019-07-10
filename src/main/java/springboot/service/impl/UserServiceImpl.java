package springboot.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import springboot.mapper.UserMapper;
import springboot.pojo.User;
import springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	UserMapper userMapper;
	
	@Override
	public User selectByName(String name){
		
		return userMapper.selectByName(name); 
		
		
	}

}
