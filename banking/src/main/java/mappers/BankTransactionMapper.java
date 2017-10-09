package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import model.BankStatementV;
import model.BankTransaction;
import model.TransactionSummary;

public interface BankTransactionMapper {

	@Select("select * from bank_transaction")
	public List<BankTransaction> findAll();

	@Insert("insert into bank_transaction (id, who, fye, bank, when, description, amount, users) "
			+ "VALUES (#{id}, #{who}, #{fye}, #{bank}, #{when}, #{description}, #{amount}, #{users})")
	public void insert(BankTransaction bankTransaction);

	@Select("select count(*) from bank_transaction "
			+ "where fye = #{fye} and who = #{who} and bank = #{bank} and users = #{users}")
	public Integer getTransactionCount(Integer fye, String who, String bank, String users);

	@Select("select distinct bank, fye, users, who from bank_transaction "
			+ "where fye = #{fye} and users = #{users} order by fye desc, who")
	public List<BankStatementV> getBankStatements(@Param("fye") Integer fye, @Param("users") String users);

	@Select("select bt_seq.nextval from dual")
	public int getNextId();

	@Delete("delete from bank_transaction_t where fye = #{fye} and who = #{who} and bank = #{bank} and users = #{users}")
	public void deleteStatement(@Param("fye") Integer fye, @Param("who") String who, @Param("bank") String bank, @Param("users") String users);

	@Delete("delete from bank_transaction where id = #{id}")
	public void delete(@Param("id") Integer id);

	@Select("select * from transaction_summary")
	public List<TransactionSummary> summary();

}
