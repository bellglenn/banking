package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import model.CatTransLnk;

public interface CatTransLnkMapper {

	@Select("SELECT * FROM cat_trans_lnk order by search")
	public List<CatTransLnk> findAll();
	
	@Insert("INSERT INTO cat_trans_lnk (search, category, users) VALUES (#{search}, #{category}, #{users}) ")
	public void insert(CatTransLnk catTransLnk);

	@Delete("delete from cat_trans_lnk where search = #{search} and users = #{users}")
	public void delete(CatTransLnk catTransLnk);
}
