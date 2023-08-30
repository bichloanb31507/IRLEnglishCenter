package com.graduate.IRLEnglishcenter.entity;

public enum EPaymentStatus {
	PENDING("Pending"), PAID("Paid"), OVERDUE("Overdue"), CANCELLED("Cancelled");

	private final String displayName;

	EPaymentStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
