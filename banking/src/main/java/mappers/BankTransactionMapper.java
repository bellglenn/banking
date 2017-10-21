package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import model.BankStatement;
import model.BankTransaction;
import model.TransactionSummary;

public interface BankTransactionMapper {

	@Select("select * from bank_transaction")
	public List<BankTransaction> findAll();

	@Insert("insert into bank_transaction_t (usr, fye, bank, account, tdate, description, amount, category) "
			+ "VALUES (#{usr}, #{fye}, #{bank}, #{account}, #{tdate}, #{description}, #{amount}, #{category})")
	public void insert(BankTransaction bankTransaction);

	@Select("select count(*) from bank_transaction")
	public Integer getTransactionCount(BankStatement bankStatement);

	@Select("select distinct bank, account, fye, usr from bank_transaction")
	public List<BankStatement> getStatements();

	@Delete("delete from bank_transaction where bank = #{bank} and account = #{account}")
	public void deleteStatement(BankStatement bankStatement);

	@Delete("delete from bank_transaction where id = #{id}")
	public void delete(@Param("id") Integer id);

	@Select("select * from transaction_summary where usr = #{usr}")
	public List<TransactionSummary> summary(@Param("usr") String usr);

	@Select("select * from group_summary  where grp = #{grp}")
	public List<TransactionSummary> groupSummary(@Param("grp") String grp);

}
