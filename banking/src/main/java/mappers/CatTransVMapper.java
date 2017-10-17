package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.CatTransV;

public interface CatTransVMapper {

	@Select("SELECT * FROM cat_trans_v order by description, category, tdate")
	public List<CatTransV> findAll();

	@Select("SELECT count(*) FROM cat_trans_v where category is null")
	public int getNullCategoryCount();

	@Update("update bank_transaction set category = #{category} where id = #{id}")
	public void saveCategory(CatTransV catTransV);

}
