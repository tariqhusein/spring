package adler.syria.XtErp.ImportProcess;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ExcelColumn {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int excelColumnIndex;
	@ManyToOne
	private ImportableColumns importableColumn;
	@ManyToOne
	private UploadedFile uploadedFile;
	private ExcelColumn () {}
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

	public ExcelColumn(String name, int excelColumnIndex, UploadedFile uploadedFile) {
		super();
		this.name = name;
		this.excelColumnIndex = excelColumnIndex;
		this.uploadedFile = uploadedFile;
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
