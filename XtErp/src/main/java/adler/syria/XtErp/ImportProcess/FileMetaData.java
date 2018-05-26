package adler.syria.XtErp.ImportProcess;


import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FileMetaData {
	@Column(name = "FILENAME")
	private String fileName;
	
	@Column(name = "FILEPATH")
	private String filePath;
	
	@Column(name = "FILETYPE")
	private String fileType;
	
	@Column(name = "FILESIZE")
	private Long fileSize;
	

	public FileMetaData() {
		super();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

}