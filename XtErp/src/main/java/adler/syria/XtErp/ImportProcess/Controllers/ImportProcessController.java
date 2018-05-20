package adler.syria.XtErp.ImportProcess.Controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import adler.syria.XtErp.ImportProcess.Repositories.SearchProfileRepository;
import adler.syria.XtErp.ImportProcess.Repositories.UploadedFileRepository;

@RestController
@RequestMapping("/file/upload")

public class ImportProcessController {

	private final StorageService storageService;
	private final UploadedFileRepository uploadedFileRepository;
	private final SearchProfileRepository searchProfileRepository;

	@Autowired
	public ImportProcessController(SearchProfileRepository searchProfileRepository, StorageService storageService,
			UploadedFileRepository uploadedFileRepository) {
		super();
		this.uploadedFileRepository = uploadedFileRepository;
		this.storageService = storageService;
		this.searchProfileRepository = searchProfileRepository;
	}

	@RequestMapping(value = "/metadata", method = RequestMethod.GET)
	public FileMetaData getFileMetaData(@RequestParam("uploadedFileUniqueId") Long fileID) {
		return uploadedFileRepository.getOne(fileID).getMetaData();
	}

	@RequestMapping(value = "/columns", method = RequestMethod.GET)
	public String getFileColumns(@RequestParam("uploadedFileUniqueId") Long fileID) {
		JSONArray res = uploadedFileRepository.getOne(fileID).getExcelColumnsAsJson();
		return res.toString();
	}

	public void startImportingProcess(@RequestParam("uploadedFile") MultipartFile file,
			@RequestParam("searchProfileId") Long searchProfileID, HttpServletResponse response) {
		ImportProcess importProcess = new ImportProcess();
		UploadedFile uploadedFile = new UploadedFile();
		Set<ExcelColumn> oldColumns;
		Set<ExcelColumn> columns = new HashSet<ExcelColumn>();

		SearchProfile searchProfile = searchProfileRepository.getOne(searchProfileID);
		oldColumns = searchProfile.getImportProcessSet().iterator().next().getUploadedFile().getExcelColumns();

		storageService.store(file);

		searchProfile.setLastUsed(new java.util.Date());

		importProcess.setDate(new java.util.Date());
		importProcess.setSearchProfile(searchProfile);
		importProcess.setUploadedFile(uploadedFile);
		uploadedFile.setImportProcess(importProcess);
		Iterator<ExcelColumn> iterator = oldColumns.iterator();
		while (iterator.hasNext()) {
			ExcelColumn column = iterator.next();
			columns.add(new ExcelColumn(column.getName(), column.getExcelColumnIndex(), uploadedFile,column.getDataType()));
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
		searchProfileRepository.saveAndFlush(searchProfile);
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().write("{success:true, id:'" + uploadedFile.getId() + "'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				String dataType;
				if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC)
					dataType = int.class.toString();
				else if (currentCell.getCellType() == Cell.CELL_TYPE_STRING)
					dataType = String.class.toString();
				else if (currentCell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
					dataType = boolean.class.toString();
				else
					dataType = String.class.toString();
				
				columns.add(new ExcelColumn(currentCell.getStringCellValue(), currentCell.getColumnIndex(),
						uploadedFile, dataType));
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
		// uploadedFileRepository.save(uploadedFile);

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
