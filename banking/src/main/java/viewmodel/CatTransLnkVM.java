package viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import mappers.BankTransactionMapper;
import mappers.CatTransLnkMapper;
import mappers.CategoryMapper;
import mappers.CurrentFyeUsersMapper;
import model.CatTransLnk;
import model.CatTransV;
import model.Category;

public class CatTransLnkVM extends BaseVM {

	private CatTransLnk catTransLnk = new CatTransLnk();
	private List<Category> categories = new ArrayList<>();
	private Category category;

	@WireVariable
	BankTransactionMapper bankTransactionMapper;
	@WireVariable
	CatTransLnkMapper catTransLnkMapper;
	@WireVariable
	CategoryMapper categoryMapper;
	@WireVariable
	private CurrentFyeUsersMapper currentFyeUsersMapper;

	private boolean inserted = false;

	@Init
	public void init(@ExecutionArgParam("transaction") CatTransV transaction) {
		catTransLnk.setSearch(transaction.getDescription());
	}

	@AfterCompose
	public void afterCompose() {
		refresh();
	}

	public void refresh() {
		categories.addAll(categoryMapper.findAll());
	}
	
	@NotifyChange("inserted")
	@Command
	public void add() {
		if (category == null) {
			Messagebox.show("Please select a category");
			return;
		}
		catTransLnk.setCategory(category.getName());
		catTransLnk.setUsers(currentFyeUsersMapper.findAll().get(0).getUsers());
		catTransLnk.setFye(currentFyeUsersMapper.findAll().get(0).getFye());

		catTransLnkMapper.insert(catTransLnk);
		inserted = true;
	}

	public boolean isInserted() {
		return inserted;
	}

	@NotifyChange("inserted")
	@Command
	public void delete() {
		catTransLnkMapper.delete(catTransLnk);
		inserted = false;
	}

	public CatTransLnk getCatTransLnk() {
		return catTransLnk;
	}

	public void setCatTransLnk(CatTransLnk catTransLnk) {
		this.catTransLnk = catTransLnk;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
