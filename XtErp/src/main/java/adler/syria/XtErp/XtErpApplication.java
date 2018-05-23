package adler.syria.XtErp;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import adler.syria.XtErp.ImportProcess.ExcelColumn;
import adler.syria.XtErp.ImportProcess.ImportableColumns;
import adler.syria.XtErp.ImportProcess.ImportableEntities;
import adler.syria.XtErp.ImportProcess.Repositories.ImportableColumnsRepository;
import adler.syria.XtErp.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class XtErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(XtErpApplication.class, args);
	}

	@Bean
	    CommandLineRunner init(ImportableColumnsRepository repository) {
	        return (args) -> {
	           repository.save(new ImportableColumns(ImportableEntities.Client, 
	        		   "description", String.class.getName(), new HashSet<ExcelColumn>()));
	           repository.save(new ImportableColumns(ImportableEntities.Client, 
	        		   "sigle", String.class.getName(), new HashSet<ExcelColumn>()));
	           repository.save(new ImportableColumns(ImportableEntities.Client, 
	        		   "tva_cee" , String.class.getName(), new HashSet<ExcelColumn>()));
	           repository.save(new ImportableColumns(ImportableEntities.Client, 
	        		   "n_reg_comm" 
	        		   , String.class.getName(), new HashSet<ExcelColumn>()));
	           repository.save(new ImportableColumns(ImportableEntities.Client, 
	        		   "capital", String.class.getName(), new HashSet<ExcelColumn>()));
	           repository.save(new ImportableColumns(ImportableEntities.Client, 
	        		   "nbr_pers", int.class.getName(), new HashSet<ExcelColumn>()));
	           repository.save(new ImportableColumns(ImportableEntities.Client, 
	        		   "website", String.class.getName(), new HashSet<ExcelColumn>()));
	           repository.save(new ImportableColumns(ImportableEntities.Client, 
	        		   "metier", String.class.getName(), new HashSet<ExcelColumn>()));
	           	           
	        };
	    }
}
