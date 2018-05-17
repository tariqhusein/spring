package com.syria.xtErp;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "uploaded_files")
public class UploadedFile {
	@Id
	@GeneratedValue
	private Long UNIQUEID;

	@Embedded
	private FileMetaData metaData;

	@ElementCollection
	
	private Set<ExcelColumn> columns;

	public FileMetaData getMetaData() {
		return metaData;
	}
	public UploadedFile() {}
	public void setMetaData(FileMetaData metaData) {
		this.metaData = metaData;
	}

	public Set<ExcelColumn> getColumns() {
		return columns;
	}

	public void setColumns(Set<ExcelColumn> columns) {
		this.columns = columns;
	}

	public UploadedFile(Set<ExcelColumn> columns) {
		this.metaData = new FileMetaData();
		this.columns = columns;
	}

	public Long getUNIQUEID() {
		return UNIQUEID;
	}

	public void setUNIQUEID(Long uNIQUEID) {
		UNIQUEID = uNIQUEID;
	}

}
