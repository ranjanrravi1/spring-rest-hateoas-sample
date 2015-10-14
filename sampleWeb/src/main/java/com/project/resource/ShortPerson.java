/**
 * 
 */
package com.project.resource;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author 492086
 *
 */
public class ShortPerson extends ResourceSupport{

	private int personId;

	private String name;
	
	public ShortPerson() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the personId
	 */
	public int getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
