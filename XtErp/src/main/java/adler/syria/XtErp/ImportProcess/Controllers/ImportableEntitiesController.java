package adler.syria.XtErp.ImportProcess.Controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import adler.syria.XtErp.ImportProcess.ExcelColumn;
import adler.syria.XtErp.ImportProcess.ImportableColumns;
import adler.syria.XtErp.ImportProcess.ImportableEntities;
import adler.syria.XtErp.ImportProcess.UploadedFile;
import adler.syria.XtErp.ImportProcess.Repositories.ImportableColumnsRepository;
import adler.syria.XtErp.ImportProcess.Repositories.UploadedFileRepository;
import adler.syria.XtErp.ImportProcess.Repositories.ExcelCoumnRepository;

@RestController
@RequestMapping("/entities")
public class ImportableEntitiesController {
	private final ImportableColumnsRepository importableColumnsRepository;
	private final UploadedFileRepository uploadedFileRepository;
	private final ExcelCoumnRepository excelCoumnRepository;

	@Autowired
	public ImportableEntitiesController(ImportableColumnsRepository importableColumnsRepository,
			UploadedFileRepository uploadedFileRepository, ExcelCoumnRepository excelCoumnRepository) {
		super();
		this.importableColumnsRepository = importableColumnsRepository;
		this.uploadedFileRepository = uploadedFileRepository;
		this.excelCoumnRepository = excelCoumnRepository;
	}

	@RequestMapping(method = RequestMethod.GET)

	public List<ImportableEntities> getEntities() {
		List<ImportableEntities> res = Arrays.asList(ImportableEntities.values());
		return res;
	}

	@RequestMapping(value = "/{entity}/columns", method = RequestMethod.GET)
	public List<ImportableColumns> getColumns(@PathVariable String entity) {
		return importableColumnsRepository.getByImportableEntity(ImportableEntities.valueOf(entity));
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void linkColumns(@RequestParam("entity") String entity, @RequestParam("entityColumn") String entityColumn,
			@RequestParam("fileId") Long fileId, @RequestParam("columnIndex") int columnIndex,
			HttpServletResponse response) {
		ImportableColumns importableColumn = null;

		List<ImportableColumns> importableColumnSet = importableColumnsRepository
				.getByImportableEntity(ImportableEntities.valueOf(entity));

		Iterator<ImportableColumns> iterator = importableColumnSet.iterator();
		while (iterator.hasNext()) {
			importableColumn = iterator.next();
			if (importableColumn.getName().equals(entityColumn))
				break;
		}
		UploadedFile uploadedFile = uploadedFileRepository.getOne(fileId);
		ExcelColumn excelColumn = excelCoumnRepository.getByUploadedFileAndExcelColumnIndex(uploadedFile, columnIndex);
		if (excelColumn.getDataType().equals(importableColumn.getDataType())) {
			excelColumn.setImportableColumn(importableColumn);

			if (importableColumn.getExcelColumnSet() == null)
				importableColumn.setExcelColumnSet(new HashSet<ExcelColumn>());
			importableColumn.getExcelColumnSet().add(excelColumn);
			uploadedFileRepository.save(uploadedFile);
			response.setContentType("text/html; charset=UTF-8");
			try {
				response.getWriter().write("{success:true}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.setContentType("text/html; charset=UTF-8");
			try {
				response.getWriter().write("{success:false, message: Data Types did not match}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
