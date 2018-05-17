package com.syria.xtErp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.syria.xtErp.storage.StorageService;

@RestController
@RequestMapping("/file/upload")

public class FileUploadController {
	private final UploadedFileRepository fileRepositoy;
	private final StorageService storageService;

	@Autowired
	FileUploadController(UploadedFileRepository fileRepositoy, StorageService storageService) {
		this.fileRepositoy = fileRepositoy;
		this.storageService = storageService;
	}

	@RequestMapping(value = "/metadata", method = RequestMethod.GET)

	public FileMetaData getMetaData(@RequestParam("uploadedFileUniqueId") Long uniqueId) {
		FileMetaData meta = this.fileRepositoy.getOne(uniqueId).getMetaData();
		return meta;
	}

	@RequestMapping(value = "/columns", method = RequestMethod.GET)

	public Iterator<ExcelColumn> getColumns(@RequestParam("uploadedFileUniqueId") Long uniqueId) {
		return this.fileRepositoy.getOne(uniqueId).getColumns().iterator();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void uploadFile(@RequestParam("uploadedFile") MultipartFile file, HttpServletResponse response) {
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
		UploadedFile uploadedFile = fileRepositoy.save(new UploadedFile(columns));
		uploadedFile.getMetaData().setFileName(file.getOriginalFilename());
		uploadedFile.getMetaData().setFileSize(file.getSize());
		String extension = "";

		int i = file.getOriginalFilename().lastIndexOf('.');
		if (i > 0) {
			extension = file.getOriginalFilename().substring(i + 1);
		}
		uploadedFile.getMetaData().setFileType(extension);

		uploadedFile = fileRepositoy.saveAndFlush(uploadedFile);
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().write("{success:true, id:'" + uploadedFile.getUNIQUEID() + "'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}