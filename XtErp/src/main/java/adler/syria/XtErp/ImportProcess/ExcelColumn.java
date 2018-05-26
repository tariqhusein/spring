package adler.syria.XtErp.ImportProcess;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ExcelColumn {
	@Id
	@GeneratedValue
	@Column(name="UNIQUEID")
	private Long id;
	@Column(name ="NAME")
	private String name;
	@Column(name ="EXCELCOLUMNINDEX")
	private int excelColumnIndex;
	@ManyToOne
	private ImportableColumns importableColumn;
	@ManyToOne
	private UploadedFile uploadedFile;
	@Column(name = "DATATYPE")
	private String dataType;
	

	public ImportableColumns getImportableColumn() {
		return importableColumn;
	}

	public void setImportableColumn(ImportableColumns importableColumn) {
		this.importableColumn = importableColumn;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	private ExcelColumn() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getExcelColumnIndex() {
		return excelColumnIndex;
	}

	public void setExcelColumnIndex(int excelColumnIndex) {
		this.excelColumnIndex = excelColumnIndex;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {

		this.uploadedFile = uploadedFile;
	}

	public ExcelColumn(String name, int excelColumnIndex, UploadedFile uploadedFile, String dataType) {
		super();
		this.name = name;
		this.excelColumnIndex = excelColumnIndex;
		this.uploadedFile = uploadedFile;
		this.dataType = dataType;
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
