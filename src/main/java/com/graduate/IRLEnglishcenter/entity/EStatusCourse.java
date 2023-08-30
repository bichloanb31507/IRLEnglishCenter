package com.graduate.IRLEnglishcenter.entity;

public enum EStatusCourse {
	DANG_MO("Đang mở"), CHUA_MO("Chưa mở"), DANG_CHO("Đang chờ");

	private final String displayName;

	EStatusCourse(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
