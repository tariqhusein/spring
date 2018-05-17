package adler.syria.XtErp.ImportProcess;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class ImportableColumns {

	@Id
	@GeneratedValue
	private Long id;
	private ImportableEntities importableEntity;
	private String name;
	private String dataType;
	
	@OneToMany(mappedBy = "importableColumn")
	private Set<ExcelColumn> excelColumn;

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

	public Set<ExcelColumn> getExcelColumn() {
		return excelColumn;
	}

	public void setExcelColumn(Set<ExcelColumn> excelColumn) {
		this.excelColumn = excelColumn;
	}

}
