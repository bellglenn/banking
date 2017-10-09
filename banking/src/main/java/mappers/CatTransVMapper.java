package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import model.CatTransV;

public interface CatTransVMapper {

	@Select("SELECT * FROM cat_trans_v order by category")
	public List<CatTransV> findAll();

	@Select("SELECT count(*) FROM cat_trans_v where category is null")
	public int getNullCategoryCount();

}
