/**
 * 
 */
package org.constructor.utils;

/**
 * @author Norman Erick Estrada
 *
 */
import java.sql.SQLException;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.constructor.web.rest.errors.ErrorConstants;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.protobuf.ServiceException;

/**
 * @author Edukai
 *
 */

@ControllerAdvice
public class EntityResponse extends ResponseEntityExceptionHandler {

	/**
	 * Log
	 */
	private static final Logger LOG = LoggerFactory.getLogger(EntityResponse.class);
	/**
	 * Instance of environment
	 */
	@Autowired
	private Environment env;
	
	/**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
	@Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,HttpHeaders headers,
            HttpStatus status, WebRequest request ) {
    	final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        final String error = "Parámetro requerido: " + ex.getParameterName() ;
        final ParamOutputTO<Void> response = new ParamOutputTO<>();
        response.setSuccess(Boolean.FALSE);
		response.setMessage(error);

        return new ResponseEntity<>(response, httpStatus);
    }
    
	
	/**
	 *  handleMissingServletRequestPart  
	 *  exception for file parameter
	 */
	@Override
    protected ResponseEntity<Object>handleMissingServletRequestPart(MissingServletRequestPartException ex,
    		HttpHeaders headers, HttpStatus
    		status, WebRequest request) {
    	final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        final String error = "Parámetro requerido: " + ex.getRequestPartName() ;
        final ParamOutputTO<Void> response = new ParamOutputTO<>();
        response.setSuccess(Boolean.FALSE);
		response.setMessage(error);

        return new ResponseEntity<>(response, httpStatus);
    }
    
	
	
    /**
     * NoResultException
     * @param exc
     * @return
     */
	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<ParamOutputTO<Void>> createResponseEntity(final NoResultException exc) {
		LOG.info("Ingresando al handler para NoResultException");
		final HttpStatus httpStatus = HttpStatus.OK;
		final ParamOutputTO<Void> response = new ParamOutputTO<>();
		response.setSuccess(Boolean.FALSE);
		response.setMessage(ErrorConstants.STATUS_MENSSAGE_201);
		return new ResponseEntity<>(response, httpStatus);
	}
	/**
	 *  ResponseEntity
	 * @param exc
	 * @return response, httpStatus
	 */
	@ExceptionHandler(PersistenceException.class)
	public ResponseEntity<ParamOutputTO<Void>> createResponseEntity(final PersistenceException exc) {
		LOG.info("Ingresando al handler para PersistenceException");
		String message = ErrorConstants.STATUS_MENSSAGE_201;
		if (exc.getCause() instanceof SQLException) {
			final SQLException sqlex = (SQLException) exc.getCause();
			message = env.getProperty(String.valueOf(sqlex.getErrorCode()));
		}
		if (exc.getCause() instanceof JDBCException) {
			final JDBCException sqlex = (JDBCException) exc.getCause();
			message = env.getProperty(String.valueOf(sqlex.getErrorCode()));
		}
		final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		final ParamOutputTO<Void> response = new ParamOutputTO<>();
		response.setSuccess(Boolean.FALSE);
		response.setMessage(message);
		return new ResponseEntity<>(response, httpStatus);
	}
	
	/**
	 * 
	 * @param sEx
	 * @return ResponseEntity
	 */
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ParamOutputTO<Void>> createResponseEntity(ServiceException sEx) {
		LOG.info("Entrando al ResponseEntity");
		final String message = sEx.getMessage();
		final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		final ParamOutputTO<Void> response = new ParamOutputTO<>();
		response.setSuccess(Boolean.FALSE);
		response.setMessage(message);
		return new ResponseEntity<>(response, httpStatus);
	}
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ParamOutputTO<Void>> sqlResponseEntity(final DataAccessException exc) {
		LOG.info("Ingresando al handler para DataAccessException");
		LOG.error(exc.getMessage(), exc);
		String message = ErrorConstants.STATUS_MENSSAGE_201;
		if (exc.getCause() instanceof SQLException) {
			final SQLException sqlex = (SQLException) exc.getCause();
			message = env.getProperty(String.valueOf(sqlex.getErrorCode()));
		}
		if (exc.getCause() instanceof JDBCException) {
			final JDBCException sqlex = (JDBCException) exc.getCause();
			message = env.getProperty(String.valueOf(sqlex.getErrorCode()));
		}
		final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		final ParamOutputTO<Void> response = new ParamOutputTO<>();
		response.setSuccess(Boolean.FALSE);
		response.setMessage(message);
		return new ResponseEntity<>(response, httpStatus);
	}
	
}
	
	