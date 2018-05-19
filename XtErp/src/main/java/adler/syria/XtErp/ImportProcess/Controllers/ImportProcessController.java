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
import org.hibernate.boot.Metadata;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import adler.syria.XtErp.storage.StorageService;
import adler.syria.XtErp.ImportProcess.ExcelColumn;
import adler.syria.XtErp.ImportProcess.FileMetaData;
import adler.syria.XtErp.ImportProcess.ImportProcess;
import adler.syria.XtErp.ImportProcess.SearchProfile;
import adler.syria.XtErp.ImportProcess.UploadedFile;
import adler.syria.XtErp.ImportProcess.Repositories.ExcelCoumnRepository;
import adler.syria.XtErp.ImportProcess.Repositories.ImportProcessRepository;
import adler.syria.XtErp.ImportProcess.Repositories.SearchProfileRepository;
import adler.syria.XtErp.ImportProcess.Repositories.UploadedFileRepository;

@RestController
@RequestMapping("/file/upload")

public class ImportProcessController {
	
	private final StorageService storageService;
	private final UploadedFileRepository uploadedFileRepository;
	private final SearchProfileRepository searchProfileRepository;

	@Autowired
	public ImportProcessController(SearchProfileRepository searchProfileRepository,
			 StorageService storageService,UploadedFileRepository uploadedFileRepository) {
		super();
		this.uploadedFileRepository = uploadedFileRepository;
		this.storageService = storageService;
		this.searchProfileRepository = searchProfileRepository;
	}
	
	@RequestMapping(value= "/metadata",method = RequestMethod.GET)
	public FileMetaData getFileMetaData(@RequestParam("uploadedFileUniqueId") Long fileID) {
		return uploadedFileRepository.getOne(fileID).getMetaData();
	}
	
	@RequestMapping(value= "/columns",method = RequestMethod.GET)
	public String getFileColumns(@RequestParam("uploadedFileUniqueId") Long fileID) {
		JSONArray res =
		 uploadedFileRepository.getOne(fileID).getExcelColumnsAsJson();
		return res.toString();
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

				columns.add(
						new ExcelColumn(currentCell.getStringCellValue(), currentCell.getColumnIndex(), uploadedFile));
			}

			excelFile.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		uploadedFile.setExcelColumns(columns);
		FileMetaData metaData = new FileMetaData();
		metaData.setFileName(file.getOriginalFilename());
		metaData.setFileSize(file.getSize());
		uploadedFile.setMetaData(metaData);

		String extension = "";

		int i = file.getOriginalFilename().lastIndexOf('.');
		if (i > 0) {
			extension = file.getOriginalFilename().substring(i + 1);
		}
		uploadedFile.getMetaData().setFileType(extension);
		//uploadedFileRepository.save(uploadedFile);

		importProcess.setDate(new java.util.Date());
		importProcess.setUploadedFile(uploadedFile);
		SearchProfile searchProfile = new SearchProfile();
		searchProfile.setLastUsed(new java.util.Date());

		importProcess.setSearchProfile(searchProfile);
		searchProfileRepository.save(searchProfile);

		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().write("{success:true, id:'" + uploadedFile.getId() + "'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
