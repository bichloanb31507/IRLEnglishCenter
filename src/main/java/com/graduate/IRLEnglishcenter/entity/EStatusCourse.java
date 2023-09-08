package com.graduate.IRLEnglishcenter.entity;

public enum EStatusCourse {
	DANG_MO("Đang mở"), DANG_CHO("Đang chờ"), DA_KET_THUC("Đã kết thúc"),DA_KHOA("đã khóa");

	private final String displayName;

	EStatusCourse(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
