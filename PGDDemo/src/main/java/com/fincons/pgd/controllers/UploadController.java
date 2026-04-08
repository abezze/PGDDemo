package com.fincons.pgd.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fincons.pgd.models.services.interfaces.IMessaggioServices;
import com.fincons.pgd.models.services.interfaces.IUploadServices;
import com.fincons.pgd.response.Resp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/upload")
public class UploadController {

	private final IUploadServices uplS;
	private final IMessaggioServices msgS;
	
	@PostMapping(value = "/doc", consumes = "multipart/form-data")
	public ResponseEntity<Resp> uploadImage(
			@RequestParam MultipartFile file,
			@RequestParam Long id) {
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			 /*
			   Test del content type:
						PNG	image/png
						JPG	image/jpeg
						GIF	image/gif
			  */
			 if (file.getContentType() == null || !file.getContentType().startsWith("doc/")) {
				 r.setMsg(msgS.get("upload_invalid"));
				 return ResponseEntity.badRequest().body(r);	            
			 }
			 
			 r.setMsg(uplS.saveDoc(file, id));
			 
			 return ResponseEntity.status(HttpStatus.CREATED).body(r);
			 
		 } catch (Exception e) {
			 r.setMsg(e.getMessage());
			 return ResponseEntity.internalServerError().body(r);
		 }
	 }
	
	@GetMapping("getUrl")
	public ResponseEntity<Resp> getUrl(@RequestParam (required = true) String filename) {
		Resp r = new Resp();
		try {
			r.setMsg(uplS.buildUrl(filename));
			return ResponseEntity.status(HttpStatus.OK).body(r);
		} catch (Exception e) {
			r.setMsg(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
		}
	}

}
