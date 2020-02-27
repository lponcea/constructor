package org.constructor.service.multimedia;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ValidationPath {
	private static final  Logger log = LoggerFactory.getLogger(ValidationPath.class);
	
	public static void createPath(String route) {
		log.debug("Save File: {}", route);
		File path = new File(route);
		try {
			//validating directory
			if(!path.exists()) {
				log.debug("directory not found: {}", route);
				if(path.mkdirs()) {
					log.debug("Creating directory: {}", route);
				}else {
					log.debug("Directory found: {}", route);
				}
			}
		}catch(Exception e) {
			log.debug("Error validating directory", e.getMessage());
	    }
	}
}