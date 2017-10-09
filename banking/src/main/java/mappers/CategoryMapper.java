package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import model.Category;

public interface CategoryMapper {

	@Select("SELECT * FROM category ORDER BY name")
	public List<Category> findAll();
	
	@Insert("INSERT INTO category (name, deduction, type, users, usage)\r\n" + 
			"        VALUES (#{name}, #{deduction}, #{type}, #{users}, #{usage})")
	public void insert(Category category);

	@Delete("delete from category where name = #{name} and users = #{users}")
	public void delete(Category category);
}
