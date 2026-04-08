package com.fincons.pgd.services.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fincons.pgd.exceptions.PGDException;
import com.fincons.pgd.models.DocumentiAllegati;
import com.fincons.pgd.models.services.interfaces.IMessaggioServices;
import com.fincons.pgd.models.services.interfaces.IUploadServices;
import com.fincons.pgd.repositories.IDocumentiAllegatiRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UploadImpl implements IUploadServices{

	private final Path uploadPath;
	private final  IMessaggioServices msgS;
	private final IDocumentiAllegatiRepository docR;
	
	
	public UploadImpl(@Value("${app.upload.dir:uploads}") String uploadDir,  // valore per default della value
			IMessaggioServices msgS, IDocumentiAllegatiRepository prodR ) {
	        this.uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize(); // transform relative path in absolute  path
	        this.msgS = msgS;
	        this.docR = prodR;
	        init();
	    }	
	
	private void init() {
		try {
			if (Files.notExists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
		} catch (IOException e) {
			throw new RuntimeException(msgS.get("upload_create"));
		}
	}
	
	@Transactional (rollbackFor = PGDException.class)
	@Override
	public String saveDoc(MultipartFile file, Long id) throws Exception {
		log.debug("saveDoc {}", id);
		
		Assert.isTrue(!file.isEmpty(),() -> msgS.get("upload_empty")); // control file loaded
		
        String original = file.getOriginalFilename();
        String extension = "";
        String originalName = original.trim().replaceAll("\\s+", "_"); // normalize file name
 
        log.debug("originalName: {}" , originalName);
        
        extension = Optional.ofNullable(originalName)         // search extension file 
                .filter(name -> name.contains("."))
                .map(name -> name.substring(name.lastIndexOf(".")))
                .orElse("");

        // Build unique name
        String uniqueName =  originalName.substring(0, originalName.lastIndexOf(".")) + "-" +  UUID.randomUUID().toString() + extension;

        Path destinationFile = uploadPath.resolve(uniqueName);
        
        try {
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
            DocumentiAllegati v = docR.findById(id)
            	.orElseThrow(() -> new PGDException(msgS.get("doc_ntfnd")));	
            v.setNomeFile(uniqueName);
            
        } catch (IOException e) {
            throw new RuntimeException(msgS.get("upload_save_error"));
        }
    
        return uniqueName;
	}

	@Override
	public void removeDoc(String filename) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String buildUrl(String filename) {
		if (docR.safeExistsByNomeFile(filename)) {
			return ServletUriComponentsBuilder.fromCurrentContextPath()  // recupera la parte iniziale dell URL // localhost:8080/
	                .path("/Docs/")    // il prefisse sarebbe Doc
	                .path(filename)                 // il nome del file
	                .toUriString();
		}
		
		throw new PGDException("Immagine non trovata: " + filename);
	}
}
