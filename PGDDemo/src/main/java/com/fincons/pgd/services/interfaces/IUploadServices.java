package com.fincons.pgd.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadServices {
	
	String saveDoc(MultipartFile file, Long id) throws Exception;
	
	void removeDoc(String filename) throws Exception;
	String buildUrl(String filename);

}
