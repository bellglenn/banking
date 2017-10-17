package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.CurrentFyeUsers;

public interface CurrentFyeUsersMapper {

	@Select("SELECT * FROM current_fye_users_t")
	public List<CurrentFyeUsers> findAll();

	@Select("SELECT * FROM current_fye_users")
	public CurrentFyeUsers findSelected();

	@Insert("INSERT INTO current_fye_users_t (fye, users, selected) VALUES (#{fye}, #{users}, #{selected})")
	public void insert(CurrentFyeUsers currentFyeUsers);

	@Delete("delete from current_fye_users_t where fye = {fye} and users = #{users}")
	public void delete(CurrentFyeUsers currentFyeUsers);

	@Update("update current_fye_users_t set selected = null")
	public void deselectAll();

	@Update("update current_fye_users_t set selected = #{selected} where fye = #{fye} and users = #{users}")
	public void updateSelection(CurrentFyeUsers currentFyeUsers);

}
