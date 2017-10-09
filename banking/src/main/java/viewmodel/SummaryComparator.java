package viewmodel;

import java.io.Serializable;
import java.util.Comparator;

import org.zkoss.zul.GroupComparator;

import model.TransactionSummary;

public class SummaryComparator
		implements Comparator<TransactionSummary>, GroupComparator<TransactionSummary>, Serializable {

	private static final long serialVersionUID = 1L;

	public int compare(TransactionSummary s1, TransactionSummary s2) {
		return s1.getCategory().compareTo(s2.getCategory());
	}

	public int compareGroup(TransactionSummary s1, TransactionSummary s2) {
		if(s1.getType().equals(s2.getType()))
			return 0;
		else
			return 1;
	}
}
