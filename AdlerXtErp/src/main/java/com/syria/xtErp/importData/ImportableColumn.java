package com.syria.xtErp.importData;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.syria.xtErp.ExcelColumn;

@Entity
public class ImportableColumn {
	
	@Id
	@GeneratedValue
	private Long id;
	private ImportableEntities importableEntity;
	private String name;
	private String dataType;
	@Embedded
	private ExcelColumn excelColumn;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ImportableEntities getImportableEntity() {
		return importableEntity;
	}
	public void setImportableEntity(ImportableEntities importableEntity) {
		this.importableEntity = importableEntity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public ExcelColumn getExcelColumn() {
		return excelColumn;
	}
	public void setExcelColumn(ExcelColumn excelColumn) {
		this.excelColumn = excelColumn;
	}

}
