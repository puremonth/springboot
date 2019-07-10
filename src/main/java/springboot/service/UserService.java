package springboot.service;


import springboot.pojo.User;


public interface UserService {
	public User selectByName(String name);

}
