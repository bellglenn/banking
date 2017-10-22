package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.User;

public interface UserMapper {

	@Select("SELECT * FROM user")
	public List<User> findAll();

	@Select("SELECT * FROM user where grp = #{grp}")
	public List<User> findUsersForGroup(@Param("grp") String grp);

	@Select("SELECT * FROM user where usr = #{usr}")
	public User find(@Param("usr") String usr);

	@Insert("INSERT INTO user (usr, grp, logon, pwd) VALUES (#{usr}, #{grp}, #{logon}, #{pwd})")
	public void insert(User user);

	@Delete("delete from user where usr = #{usr}")
	public void delete(User user);

	@Update("update user set logon = #{logon}, pwd = #{pwd} where usr = #{usr}")
	public void update(User user);

}
