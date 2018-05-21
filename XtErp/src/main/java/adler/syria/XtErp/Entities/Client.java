package adler.syria.XtErp.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Client {
	@Id
	@GeneratedValue
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String description;
	private String sigle;
	private String tva_cee;
	private String n_reg_comm;
	private String capital;
	private int nbr_pers;
	private String website;
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
