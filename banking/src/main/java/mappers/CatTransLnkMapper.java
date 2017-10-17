package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import model.CatTransLnk;

public interface CatTransLnkMapper {

	@Select("SELECT * FROM cat_trans_lnk")
	public List<CatTransLnk> findAll();
	
	@Insert("INSERT INTO cat_trans_lnk_t (search, category, usr, fye) VALUES (#{search}, #{category}, #{usr}, #{fye}) ")
	public void insert(CatTransLnk catTransLnk);

	@Delete("delete from cat_trans_lnk where search = #{search}")
	public void delete(CatTransLnk catTransLnk);
}
