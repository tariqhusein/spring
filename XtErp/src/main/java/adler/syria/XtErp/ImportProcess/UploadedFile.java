package adler.syria.XtErp.ImportProcess;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "UPLOADED_FILE")
public class UploadedFile {

	@Id
	@GeneratedValue
	@Column(name ="UNIQUEID")
	private Long id;
	private FileMetaData metaData;

	@OneToOne(mappedBy = "uploadedFile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ImportProcess importProcess;

	@OneToMany(mappedBy = "uploadedFile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

	public JSONArray getExcelColumnsAsJson() {
		/*String res = "{";
		Iterator<ExcelColumn> iterator = this.excelColumns.iterator();
		while (iterator.hasNext()) {
			ExcelColumn excelColumn = iterator.next();
			res += "{ name :" + excelColumn.getName() + ",excelColumnIndex :" + excelColumn.getIndex() + ",dataType :"
					+ excelColumn.getName() + "},";
		}

		res = res.substring(0, res.length() - 1);
		res += "}";*/
		JSONArray res = new JSONArray();
		Iterator<ExcelColumn> iterator = this.excelColumns.iterator();
		while (iterator.hasNext()) {
			ExcelColumn excelColumn = iterator.next();
			JSONObject obj = new JSONObject();
			try {
				obj.put("name", excelColumn.getName());
				obj.put("excelColumnIndex", excelColumn.getIndex());
				obj.put("dataType", excelColumn.getName());
				res.put(obj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}		
		return res;

	}

	public void setExcelColumns(Set<ExcelColumn> excelColumns) {
		this.excelColumns = excelColumns;
	}

}
