package viewmodel;

import java.util.Comparator;
import java.util.List;

import org.zkoss.zul.GroupsModelArray;

import model.TransactionSummary;

public class SummaryGroupingModel extends GroupsModelArray<TransactionSummary, String, String, Object> {
	private static final long serialVersionUID = 1L;

	private static final String footerString = "Total %d items";

	private boolean showGroup;

	public SummaryGroupingModel(List<TransactionSummary> data, Comparator<TransactionSummary> cmpr, boolean showGroup) {
		super(data.toArray(new TransactionSummary[0]), cmpr);

		this.showGroup = showGroup;
	}

	protected String createGroupHead(TransactionSummary[] groupdata, int index, int col) {
		String ret = "";
		if (groupdata.length > 0) {
			ret = groupdata[0].getType();
		}

		return ret;
	}

	protected String createGroupFoot(TransactionSummary[] groupdata, int index, int col) {
		return String.format(footerString, groupdata.length);
	}

	public boolean hasGroupfoot(int groupIndex) {
		boolean retBool = false;

		if (showGroup) {
			retBool = super.hasGroupfoot(groupIndex);
		}

		return retBool;
	}
}
