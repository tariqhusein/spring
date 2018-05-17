package com.syria.xtErp;

import javax.persistence.Embeddable;

@Embeddable
public class ExcelColumn {
	private String name;
	private int excelColumnIndex;

	public ExcelColumn() {}
	public ExcelColumn(String name, int excelColumnIndex) {
		super();
		this.name = name;
		this.excelColumnIndex = excelColumnIndex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return excelColumnIndex;
	}
	public void setIndex(int excelColumnIndex) {
		this.excelColumnIndex = excelColumnIndex;
	}
	
	
}
