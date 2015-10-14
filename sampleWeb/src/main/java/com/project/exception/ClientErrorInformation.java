/**
 * 
 */
package com.project.exception;

/**
 * @author 492086
 *
 */
public class ClientErrorInformation {

	private String message;
	private String uri;
	
	public ClientErrorInformation(String msg, String requestURI) {
		this.setMessage(msg);
		this.setUri(requestURI);
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

}
