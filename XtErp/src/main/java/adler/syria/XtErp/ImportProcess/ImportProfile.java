package adler.syria.XtErp.ImportProcess;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class ImportProfile {
	@Id
	@GeneratedValue
	@Column(name ="UNIQUEID")
	private Long id;
	@OneToMany(mappedBy = "searchProfile",cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
	private Set<ImportProcess> importProcessSet;
	
	@Column(name ="LASTUSED")
	private Date lastUsed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<ImportProcess> getImportProcessSet() {
		return importProcessSet;
	}

	public void setImportProcessSet(Set<ImportProcess> importProcessSet) {
		this.importProcessSet = importProcessSet;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

}
