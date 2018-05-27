package adler.syria.XtErp.ImportProcess;

import java.util.Date;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="IMPORT_PROCESS")
public class ImportProcess {

	@Id
	@GeneratedValue
	@Column(name = "UNIQUEID")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UploadedFile uploadedFile;

	@Column(name = "DATE")
	private Date date;
	@ManyToOne
	private ImportProfile importProfile;

	public Long getId() {
		return id;
	}

	public UploadedFile getUploadedFile() {

		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		uploadedFile.setImportProcess(this);
		this.uploadedFile = uploadedFile;
	}

	public ImportProfile getSearchProfile() {
		return importProfile;
	}

	public void setSearchProfile(ImportProfile importProfile) {
		if (importProfile.getImportProcessSet() == null) {
			importProfile.setImportProcessSet(new HashSet<ImportProcess>());
		}
		importProfile.getImportProcessSet().add(this);

		this.importProfile = importProfile;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
