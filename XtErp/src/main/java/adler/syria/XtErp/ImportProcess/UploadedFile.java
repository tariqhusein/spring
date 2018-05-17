package adler.syria.XtErp.ImportProcess;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class UploadedFile {

	@Id
	@GeneratedValue
	private Long id;
	private FileMetaData metaData;

	@OneToOne(mappedBy = "uploadedFile")
	private ImportProcess importProcess;

	@OneToMany(mappedBy = "uploadedFile")
	private Set<ExcelColumn> excelColumns;

	public ImportProcess getImportProcess() {
		return importProcess;
	}

	public void setImportProcess(ImportProcess importProcess) {
		this.importProcess = importProcess;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FileMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(FileMetaData metaData) {
		this.metaData = metaData;
	}

	public Set<ExcelColumn> getExcelColumns() {
		return excelColumns;
	}

	public void setExcelColumns(Set<ExcelColumn> excelColumns) {
		this.excelColumns = excelColumns;
	}

}
