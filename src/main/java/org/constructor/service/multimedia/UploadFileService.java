package org.constructor.service.multimedia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.constructor.multimedia.response.VideoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import liquibase.util.file.FilenameUtils;

@Service
public class UploadFileService {
	
	private final Logger log = LoggerFactory.getLogger(UploadFileService.class);
	private static final String upload_folder =  System.getProperty("user.home") + "/resources" + File.separator + "nimbus";
	enum extVideo { MP4, VGA};
	enum extImage { JPG, PNG};
	enum extDocs { PDF, CSV};
	
	public VideoResponse saveFile(MultipartFile file) {
		    VideoResponse videoResponse = new VideoResponse();
			try {
					String extension = FilenameUtils.getExtension(file.getOriginalFilename());
					String replace = null; 
					videoResponse.setName(file.getOriginalFilename());
					log.debug("Save File: {}", file);
					//Create Path
					List<String> image = new ArrayList();
					StringBuilder builder = new StringBuilder();
					ValidationPath.createPath(upload_folder);
					
						if (extension.toUpperCase().equals(extVideo.MP4.toString())
								|| extension.toUpperCase().equals(extVideo.VGA.toString())) {
							builder.append(upload_folder);
							builder.append(File.separator);
							builder.append("video");
							log.debug("builder Video : {}", builder);
							ValidationPath.createPath(builder.toString());
						}
					
						if (extension.toUpperCase().equals(extImage.PNG.toString())
								|| extension.toUpperCase().equals(extImage.JPG.toString())) {
							builder.append(upload_folder);
							builder.append(File.separator);
							builder.append("image");
							log.debug("builder image : {}", builder);
							ValidationPath.createPath(builder.toString());
						}
					
						if (extension.toUpperCase().equals(extDocs.PDF.toString())
								|| extension.toUpperCase().equals(extDocs.CSV.toString())) {
							builder.append(upload_folder);
							builder.append(File.separator);
							builder.append("docs");
							log.debug("builder docs : {}", builder);
							ValidationPath.createPath(builder.toString());
						}
						
					builder.append(File.separator);
					builder.append(file.getOriginalFilename());
					
					//Creating and Writing  File
					byte[] fileBytes = file.getBytes();
					Path path = Paths.get(builder.toString());
					Files.write(path, fileBytes);
					
					int i = builder.indexOf("nimbus");
					log.debug("i {}", builder.indexOf("nimbus"));
					log.debug("path : {}", builder.substring(i));
					replace = (builder.substring(i)).replace("\\", "/");
					log.debug("replace : {}", replace);
					videoResponse.setPath(replace);
					return videoResponse;
				
			 }catch(IOException e){
				return videoResponse;
		} 
	}
		
}

