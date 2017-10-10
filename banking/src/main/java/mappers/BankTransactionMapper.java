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

	@Select("select count(*) from bank_transaction where who = #{who} and bank = #{bank}")
	public Integer getTransactionCount(String who, String bank);

	@Select("select distinct bank, fye, who from bank_transaction order by fye desc, who")
	public List<BankStatementV> getBankStatements();

	@Select("select bt_seq.nextval from dual")
	public int getNextId();

	@Delete("delete from bank_transaction_t where fye = #{fye} and who = #{who} and bank = #{bank}")
	public void deleteStatement(@Param("fye") Integer fye, @Param("who") String who, @Param("bank") String bank);

	@Delete("delete from bank_transaction where id = #{id}")
	public void delete(@Param("id") Integer id);

	@Select("select * from transaction_summary")
	public List<TransactionSummary> summary();

	@Select("select * from transaction_summary_grp")
	public List<TransactionSummary> usersSummary();

}
