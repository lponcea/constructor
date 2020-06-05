package org.constructor.service.multimedia.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.constructor.multimedia.response.MultimediaResponse;
import org.constructor.multimedia.response.VideoResponse;
import org.constructor.service.CursoService;
import org.constructor.service.dto.MultimediaDTO;
import org.constructor.service.multimedia.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import liquibase.util.file.FilenameUtils;


@Service
public class MultimediaServiceImpl implements MultimediaService {
	
	/**
	 * CursoService Service
	 */
	@Autowired
	private  CursoService cursoService;
	
	
	private final Logger log = LoggerFactory.getLogger(MultimediaServiceImpl.class);
	private static final StringBuilder UPLOAD_FOLDER = new StringBuilder(System.getProperty("user.home") + "/resources" + File.separator);
	private static final String nimbus = "nimbus"; 
	enum extVideo { MP4, VGA};
	enum extImage { JPG, PNG};
	enum extDocs { PDF, CSV};

	/**
	 * saveFile
	 */
	@Override
	public VideoResponse saveFile(MultimediaDTO file) {
	    VideoResponse videoResponse = new VideoResponse();
		try {
			log.debug("*********************** FileUploadServiceImplement **********************");
				Long idCurso = file.getId();
				MultipartFile multimedia =  file.getFile();
				String extension = FilenameUtils.getExtension(multimedia.getOriginalFilename());
				String replace = null; 
				videoResponse.setName(multimedia.getOriginalFilename());
				//Create Path
				StringBuilder builder = new StringBuilder();
				PathValidation.createPath(UPLOAD_FOLDER.toString());
				
					if (extension.toUpperCase().equals(extVideo.MP4.toString())
							|| extension.toUpperCase().equals(extVideo.VGA.toString())) {
						builder.append(UPLOAD_FOLDER.toString());
						builder.append(File.separator);
						builder.append(nimbus);
						builder.append(File.separator);
						builder.append(idCurso);
						builder.append(File.separator);
						builder.append("video");
						log.debug("builder Video : {}", builder);
						PathValidation.createPath(builder.toString());
					}
				
					if (extension.toUpperCase().equals(extImage.PNG.toString())
							|| extension.toUpperCase().equals(extImage.JPG.toString())) {
						builder.append(UPLOAD_FOLDER.toString());
						builder.append(File.separator);
						builder.append(nimbus);
						builder.append(File.separator);
						builder.append(idCurso);
						builder.append(File.separator);
						builder.append("image");
						log.debug("builder image : {}", builder);
						PathValidation.createPath(builder.toString());
					}
				
					if (extension.toUpperCase().equals(extDocs.PDF.toString())
							|| extension.toUpperCase().equals(extDocs.CSV.toString())) {
						builder.append(UPLOAD_FOLDER.toString());
						builder.append(File.separator);
						builder.append(nimbus);
						builder.append(File.separator);
						builder.append(idCurso);
						builder.append(File.separator);
						builder.append("docs");
						log.debug("builder docs : {}", builder);
						PathValidation.createPath(builder.toString());
					}else {
						videoResponse.setPath(null);
					}
					
				builder.append(File.separator);
				builder.append(multimedia.getOriginalFilename());
				
				//Creating and Writing  File
				byte[] fileBytes = multimedia.getBytes();
				Path path = Paths.get(builder.toString());
				Files.write(path, fileBytes);
				int i = builder.indexOf("nimbus");
				log.debug("i {}", builder.indexOf("nimbus"));
				log.debug("path : {}", builder.substring(i));
				replace = (builder.substring(i)).replace("\\", "/");
				videoResponse.setPath(replace);
				return videoResponse;
			
		 }catch(IOException e){
			return videoResponse;
		 } 
	}

	/**
	 * deleteCourseCover 
	 * 
	 * 
	 */
	@Override
	public MultimediaResponse deleteCourseCover(Long id) {
		MultimediaResponse multimediaResponse = new MultimediaResponse();
		String status = "";
		String filePath = "";
		String response = "";
		
		log.debug("*************************   deleteCourseCover *******************");
		
		filePath =  cursoService.FindCourseCover(id);
		log.debug("filePath : {}", filePath);
		
		if(filePath == null) {
			log.debug("ID no encontrado ");
			multimediaResponse.setStatus(false);
			multimediaResponse.setMessage("Id not found");
			return multimediaResponse;
		}
			
		status = deleteFile(filePath);
		
		if(status.equals("successful")) {
			multimediaResponse.setStatus(true);
			multimediaResponse.setMessage("File removed successfully");
		}
		if (status.equals("failed")) {
			multimediaResponse.setStatus(false);
			multimediaResponse.setMessage("File not found");
		}
		
		if (status.equals("dependent")) {
			multimediaResponse.setStatus(false);
			multimediaResponse.setMessage("File not found");
		}
		
		
		
		
		return multimediaResponse;
	}

	/**
	 * deleteFile
	 */
	@Override
	public String deleteFile(String pathfile) {
		log.debug("deleteFile: {} ", pathfile);
		String status = "";
		boolean found = false;
		
		found = otherCourseCoverExists(pathfile);
		
		if(found) {
			status = "dependent";
			return status;
		}
		
		File file = new File(UPLOAD_FOLDER.append(pathfile).toString());
		if (file.exists()) {

			if (file.delete()) {
				status = "successful";
			} else {
				status = "failed";
			}
		}
		return status;
	}
	
	/**
	 * otherCourseCoverExists
	 */
	public boolean otherCourseCoverExists(String content) {
		boolean isExists = false;
	    long find = 0;
		
		find = cursoService.FindByContentCourseCover(content);	
		
		if(find > 0) {
			isExists = true;
		}else {
			isExists = false;
		}
		return isExists;
	}
	
}
