package adler.syria.XtErp.ImportProcess;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ImportProcess {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
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
		uploadedFile.setImportProcess(this);
		this.uploadedFile = uploadedFile;
	}

	public SearchProfile getSearchProfile() {
		return searchProfile;
	}

	public void setSearchProfile(SearchProfile searchProfile) {
		if(searchProfile.getImportProcessSet()==null)
		{
			searchProfile.setImportProcessSet(new HashSet<ImportProcess>());
		}
		searchProfile.getImportProcessSet().add(this);
		
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
