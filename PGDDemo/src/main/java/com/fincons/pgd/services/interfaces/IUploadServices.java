package com.fincons.pgd.services.interfaces;

import com.fincons.pgd.exceptions.PGDException;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadServices {
	
	String saveDoc(MultipartFile file, Long id) throws PGDException;
	
	void removeDoc(String filename) throws PGDException;
	String buildUrl(String filename);

}
