package adler.syria.XtErp.ImportProcess;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "IMPORTABLE_COLUMNS")
public class ImportableColumns {

	public ImportableColumns(ImportableEntities importableEntity, String name, String dataType,
			Set<ExcelColumn> excelColumnSet) {
		super();
		this.importableEntity = importableEntity;
		this.name = name;
		this.dataType = dataType;
		this.excelColumnSet = excelColumnSet;
	}
	private ImportableColumns() {}
	@Id
	@GeneratedValue
	@Column(name = "UNIQUEID")
	private Long id;
	
	@Column(name = "IMPORTABLEENTITY")
	private ImportableEntities importableEntity;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DATATYPE")
	private String dataType;
	
	@OneToMany(mappedBy = "importableColumn",cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
	private Set<ExcelColumn> excelColumnSet;

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

	public Set<ExcelColumn> getExcelColumnSet() {
		return excelColumnSet;
	}

	public void setExcelColumnSet(Set<ExcelColumn> excelColumnSet) {
		this.excelColumnSet = excelColumnSet;
	}

}
