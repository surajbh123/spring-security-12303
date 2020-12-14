package com.image.demo.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@RestController
@RequestMapping("test")
public class ImageController {

	@GetMapping
	@RequestMapping("hello")
	public ResponseEntity<String> greet() {
		System.out.println("hello");
		return ResponseEntity.status(HttpStatus.OK).body("Hellow");
	}

	@PostMapping
	@RequestMapping("uploadimage")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println(file.getOriginalFilename());
		String uploadDir = "\\Users\\suraj\\Desktop\\ImageUpload\\";
		uploadDir = uploadDir.replace("\\", "/");
		String filename =(Math.round(Math.random()*999)) +file.getOriginalFilename(); 
		Path saveTO = Paths.get(uploadDir + filename);
		System.out.println(saveTO);
		Files.copy(file.getResource().getInputStream(),saveTO);
		return null;
		
	}

	@GetMapping
	@RequestMapping("image")
	public ResponseEntity<Resource> getImage(HttpServletRequest request) throws IOException {
		String path = "c:\\Users\\suraj\\Desktop\\ImageUpload\\";
		path = path.replace("\\", "/");
		String fileName = "222.png";

		Path fileStorageLocation = Paths.get(path).toAbsolutePath().normalize();
		Path filePath = fileStorageLocation.resolve(fileName).normalize();
		Resource resource = new UrlResource(filePath.toUri());

		String contentType = null;
		contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

		System.out.println(resource.getInputStream().read());
		System.out.println(filePath);
		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		System.out.println(MediaType.parseMediaType(contentType));
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
	}

}
