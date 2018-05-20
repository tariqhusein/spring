package adler.syria.XtErp.ImportProcess.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import adler.syria.XtErp.ImportProcess.ExcelColumn;
import adler.syria.XtErp.ImportProcess.UploadedFile;

public interface ExcelCoumnRepository extends JpaRepository<ExcelColumn, Long> {

	public ExcelColumn getByUploadedFileAndExcelColumnIndex(UploadedFile uploadeFile,int excelColumnIndex);
}
