package springboot.mapper;

import org.apache.ibatis.annotations.Param;

import springboot.pojo.User;

public interface UserMapper {
	
	public  User selectByName(@Param("name")String name);
	

}
