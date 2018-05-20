package adler.syria.XtErp.ImportProcess.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import adler.syria.XtErp.ImportProcess.ImportableColumns;
import adler.syria.XtErp.ImportProcess.ImportableEntities;

public interface ImportableColumnsRepository extends JpaRepository<ImportableColumns, Long>{

	public List<ImportableColumns> getByImportableEntity(ImportableEntities entity);
}
