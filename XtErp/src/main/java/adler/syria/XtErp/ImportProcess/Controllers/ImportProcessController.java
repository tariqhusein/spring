package adler.syria.XtErp.ImportProcess.Controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import adler.syria.XtErp.storage.StorageService;
import adler.syria.XtErp.ImportProcess.ExcelColumn;
import adler.syria.XtErp.ImportProcess.ImportProcess;
import adler.syria.XtErp.ImportProcess.SearchProfile;
import adler.syria.XtErp.ImportProcess.UploadedFile;
import adler.syria.XtErp.ImportProcess.Repositories.ImportProcessRepository;

@RestController
@RequestMapping("/file/upload")

public class ImportProcessController {
	private final ImportProcessRepository importProcessRepository;
	private final StorageService storageService;

	@Autowired
	public ImportProcessController(ImportProcessRepository importProcessRepository, StorageService storageService) {
		super();
		this.importProcessRepository = importProcessRepository;
		this.storageService = storageService;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void startImportingProcess(@RequestParam("uploadedFile") MultipartFile file, HttpServletResponse response) {
		ImportProcess importProcess = new ImportProcess();
		UploadedFile uploadedFile = new UploadedFile();
		Set<ExcelColumn> columns = new HashSet<ExcelColumn>();
		FileInputStream excelFile = null;


		try {

			// Create the input stream to uploaded file to read data from it.
			excelFile = (FileInputStream) file.getInputStream();

			storageService.store(file);
			Workbook workbook = new XSSFWorkbook(excelFile);

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();

			Row currentRow = rows.next();

			Iterator<Cell> cellsInRow = currentRow.iterator();

			while (cellsInRow.hasNext()) {

				Cell currentCell = cellsInRow.next();

				columns.add(new ExcelColumn(currentCell.getStringCellValue(), currentCell.getColumnIndex()));

			}

			excelFile.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		uploadedFile.setExcelColumns(columns);
		uploadedFile.getMetaData().setFileName(file.getOriginalFilename());
		uploadedFile.getMetaData().setFileSize(file.getSize());
		String extension = "";

		int i = file.getOriginalFilename().lastIndexOf('.');
		if (i > 0) {
			extension = file.getOriginalFilename().substring(i + 1);
		}
		uploadedFile.getMetaData().setFileType(extension);
		importProcess.setDate(new java.util.Date());
		importProcess.setUploadedFile(uploadedFile);
		SearchProfile searchProfile = new SearchProfile();
		searchProfile.setLastUsed(new java.util.Date());
		
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().write("{success:true, id:'" + uploadedFile.getId() + "'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
