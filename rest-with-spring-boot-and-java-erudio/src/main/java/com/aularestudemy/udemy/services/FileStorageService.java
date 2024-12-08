package com.aularestudemy.udemy.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aularestudemy.udemy.config.FileStorageConfig;
import com.aularestudemy.udemy.exceptions.FileStorageException;
import com.aularestudemy.udemy.exceptions.MyFileNotFoundException;

@Service
public class FileStorageService {
	
	private final Path fileStorageLocation;
	
	@Autowired
	public FileStorageService(FileStorageConfig finaFileStorageConfig) {
		Path path = Paths.get(finaFileStorageConfig.getUploadDir())
				.toAbsolutePath().normalize();
		
		this.fileStorageLocation = path;
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be Storage!!", e);
		}
		
	}
	
	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
			
		} catch (Exception e) {
			throw new FileStorageException("Could not store file "+ fileName +". Please try again!", e);
		}
	}
	
	public Resource loadFileAsResource(String filename) {
		try {
			Path filePath = this.fileStorageLocation.resolve(filename).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) return resource ;
			else throw new MyFileNotFoundException("File not found ");		
			
			
		} catch (Exception e) {
			throw new MyFileNotFoundException("File not found "+filename, e);
		}
	}
	
}
