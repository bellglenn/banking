package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import model.CatTransLnk;

public interface CatTransLnkMapper {

	@Select("SELECT * FROM cat_trans_lnk")
	public List<CatTransLnk> findAll();
	
	@Insert("INSERT INTO cat_trans_lnk_t (search, category, users, fye) VALUES (#{search}, #{category}, #{users}, #{fye}) ")
	public void insert(CatTransLnk catTransLnk);

	@Delete("delete from cat_trans_lnk_t where search = #{search} and users = #{users} and fye = #{fye}")
	public void delete(CatTransLnk catTransLnk);
}
