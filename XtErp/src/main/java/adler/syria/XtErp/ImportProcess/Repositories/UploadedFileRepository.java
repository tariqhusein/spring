package adler.syria.XtErp.ImportProcess.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import adler.syria.XtErp.ImportProcess.UploadedFile;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {

}
