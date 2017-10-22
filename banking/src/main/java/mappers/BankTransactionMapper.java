package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.BankStatement;
import model.BankTransaction;
import model.Session;
import model.TransactionSummary;

public interface BankTransactionMapper {

	@Select("select * from bank_transaction where fye = #{fye} and usr = #{usr}")
	public List<BankTransaction> findAll(Session session);

	@Insert("insert into bank_transaction_t (usr, fye, bank, account, tdate, description, amount, category) "
			+ "VALUES (#{usr}, #{fye}, #{bank}, #{account}, #{tdate}, #{description}, #{amount}, #{category})")
	public void insert(BankTransaction bankTransaction);

	@Select("select count(*) from bank_transaction where fye = #{fye} and usr = #{usr}")
	public Integer getTransactionCount(BankStatement bankStatement);

	@Select("select distinct bank, account, fye, usr from bank_transaction where fye = #{fye} and usr = #{usr}")
	public List<BankStatement> getStatements(Session session);

	@Delete("delete from bank_transaction where bank = #{bank} and account = #{account} and fye = #{fye} and usr = #{usr}")
	public void deleteStatement(BankStatement bankStatement);

	@Delete("delete from bank_transaction where id = #{id}")
	public void delete(@Param("id") Integer id);

	@Select("select * from transaction_summary where usr = #{usr} and fye = #{fye}")
	public List<TransactionSummary> summary(Session session);

	@Select("select * from group_summary  where grp = #{grp} and fye = #{fye}")
	public List<TransactionSummary> groupSummary(@Param("grp") String grp, @Param("fye") Integer fye);

	@Select("SELECT count(*) FROM bank_transaction where category is null")
	public int getNullCategoryCount();

	@Update("update bank_transaction set category = #{category} where id = #{id}")
	public void update(BankTransaction transaction);
}
