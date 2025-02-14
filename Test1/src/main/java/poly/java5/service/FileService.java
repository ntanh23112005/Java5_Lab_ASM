package poly.java5.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	/**
	 * Lưu file upload vào thư mục
	 * @
	 * @param multipartFile là file upload
	 * @param folder        là thư mục chứa
	 * @return File đã lưu
	 */
	File save(MultipartFile multipartFile, String folder);

	/**
	 * Lưu nhiều file upload vào thư mục
	 * 
	 * @param multipartFiles là các file upload
	 * @param folder         là thư mục chứa
	 * @return List<File> đã lưu
	 */
	List<File> save(MultipartFile[] multipartFiles, String folder);

	/**
	 * Đọc nội dung file
	 * 
	 * @param path là đường dẫn file cần đọc
	 * @return Nội dung file
	 */
	byte[] read(String path);
}
