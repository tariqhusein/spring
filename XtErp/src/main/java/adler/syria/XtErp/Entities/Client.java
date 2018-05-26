package adler.syria.XtErp.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLEINT")
public class Client {
	@Id
	@GeneratedValue
	@Column(name = "UNIQUEID")
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {

		this.id = id;
	}
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "SIGEL")
	private String sigle;
	
	@Column(name = "TVA_CEE")
	private String tva_cee;
	
	@Column(name = "N_REG_COMM")
	private String n_reg_comm;
	@Column(name = "CAPITAL")
	private String capital;
	
	@Column(name = "NB_PERS")
	private int nbr_pers;
	
	@Column(name ="WEBSITE")
	private String website;
	
	@Column(name = "METIER")
	private String metier;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSigle() {
		return sigle;
	}
	public void setSigle(String sigle) {
		this.sigle = sigle;
	}
	public String getTva_cee() {
		return tva_cee;
	}
	public void setTva_cee(String tva_cee) {
		this.tva_cee = tva_cee;
	}
	public String getN_reg_comm() {
		return n_reg_comm;
	}
	public void setN_reg_comm(String n_reg_comm) {
		this.n_reg_comm = n_reg_comm;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public int getNbr_pers() {
		return nbr_pers;
	}
	public void setNbr_pers(int nbr_pers) {
		this.nbr_pers = nbr_pers;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getMetier() {
		return metier;
	}
	public void setMetier(String metier) {
		this.metier = metier;
	}
}
