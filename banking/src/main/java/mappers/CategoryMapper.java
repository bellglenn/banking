package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Category;
import model.Session;

public interface CategoryMapper {

	@Select("SELECT * FROM category where fye = #{fye} and usr = #{usr}")
	public List<Category> findAll(Session session);
	
	@Insert("INSERT INTO category (name, deduction, type, usr, fye)\r\n" + 
			"        VALUES (#{name}, #{deduction}, #{type}, #{usr}, #{fye})")
	public void insert(Category category);

	@Delete("delete from category where name = #{name}")
	public void delete(Category category);

	@Update("update category set deduction = #{deduction} where name = #{name}")
	public void update(Category category);
}
