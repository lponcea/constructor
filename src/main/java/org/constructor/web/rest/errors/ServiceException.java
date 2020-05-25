/**
 * 
 */
package org.constructor.web.rest.errors;

/**
 * clase de excepciones que permite el manejo de las escepciones en la capa de Service 
 *
 */
public class ServiceException extends Exception {

	/**
	 * id por default
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Metodo que permite la generacion y lanzamiento de excepciones con la excepcion origen
	 * @param exc excepcion obtenida
	 * @param mensaje mensaje relacionado a la excepcion obtenida
	 */
	public ServiceException(final String mensaje, final Throwable exc) {
		super(mensaje, exc);

	}
	
	/**
	 * Metodo que permite la generacion y lanzamiento del mensaje de excepciones
	 * @param mensaje descripcion del mensaje relacionado a la excepcion obtenida
	 */
	public ServiceException(final String mensaje) {
		super(mensaje);
	}

	/**
	 * Metodo que permite la generacion y lanzamiento de la excepcion
	 * 
	 */
	public ServiceException(final Throwable exc) {
		super(exc);
	}


}
