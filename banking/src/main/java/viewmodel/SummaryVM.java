package viewmodel;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import mappers.BankTransactionMapper;

public class SummaryVM extends BaseVM {

	@WireVariable
	private BankTransactionMapper bankTransactionMapper;
	private SummaryGroupingModel model;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws Exception {
		refresh();
	}

	@Command
	public void refresh() throws Exception {
		model = new SummaryGroupingModel(filter(bankTransactionMapper.summary()), new SummaryComparator(), true);
		BindUtils.postNotifyChange(null,  null,  this,  "model");
	}
	
	public SummaryGroupingModel getModel() {
		return model;
	}
	
	

	
}
