package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import model.CurrentFyeUsers;

public interface CurrentFyeUsersMapper {

	@Select("SELECT * FROM current_fye_users")
	public List<CurrentFyeUsers> findAll();

	@Insert("INSERT INTO current_fye_users (fye, users) VALUES (#{fye}, #{users})")
	public void insert(CurrentFyeUsers currentFyeUsers);

	@Delete("delete from current_fye_users")
	public void delete();
}
