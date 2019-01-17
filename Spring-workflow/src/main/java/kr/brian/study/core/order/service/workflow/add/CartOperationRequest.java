package kr.brian.study.core.order.service.workflow.add;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartOperationRequest implements Serializable {

	private static final long serialVersionUID = 6831659187950691961L;

	protected boolean priceOrder;
	
	protected List<Long[]> multishipOptionsToDelete = new ArrayList<Long[]>();
	
	public CartOperationRequest(boolean priceOrder) {
		setPriceOrder(priceOrder);
    }
	
	public boolean isPriceOrder() {
		return priceOrder;
	}

	public void setPriceOrder(boolean priceOrder) {
		this.priceOrder = priceOrder;
	}

	public List<Long[]> getMultishipOptionsToDelete() {
		return multishipOptionsToDelete;
	}

	public void setMultishipOptionsToDelete(List<Long[]> multishipOptionsToDelete) {
		this.multishipOptionsToDelete = multishipOptionsToDelete;
	}

}
