package adler.syria.XtErp.ImportProcess;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ImportProcess {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private UploadedFile uploadedFile;
	
	private Date date;
	@ManyToOne
	private SearchProfile searchProfile;

	public Long getId() {
		return id;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public SearchProfile getSearchProfile() {
		return searchProfile;
	}

	public void setSearchProfile(SearchProfile searchProfile) {
		this.searchProfile = searchProfile;
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
