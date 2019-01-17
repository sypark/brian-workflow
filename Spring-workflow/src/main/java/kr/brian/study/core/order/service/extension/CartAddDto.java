package kr.brian.study.core.order.service.extension;

import java.io.Serializable;

public class CartAddDto implements Serializable{
	
	private static final long serialVersionUID = -1891757266610943234L;
	
	protected String referenceNumber;

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
}
