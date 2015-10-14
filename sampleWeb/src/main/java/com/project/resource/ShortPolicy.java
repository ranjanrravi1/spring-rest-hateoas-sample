/**
 * 
 */
package com.project.resource;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author 492086
 *
 */
public class ShortPolicy extends ResourceSupport {

	private int policyId;
	
	private String name;
	
//	private int term;
//	
//	private float premium;

	private List<ShortPerson> persons;
	
	public ShortPolicy() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the policyId
	 */
	public int getPolicyId() {
		return policyId;
	}

	/**
	 * @param policyId the policyId to set
	 */
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
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
	
	/**
	 * @return the term
	 *//*
	public int getTerm() {
		return term;
	}

	*//**
	 * @param term the term to set
	 *//*
	public void setTerm(int term) {
		this.term = term;
	}

	*//**
	 * @return the premium
	 *//*
	public float getPremium() {
		return premium;
	}

	*//**
	 * @param premium the premium to set
	 *//*
	public void setPremium(float premium) {
		this.premium = premium;
	}
*/

	/**
	 * @return the persons
	 */
	public List<ShortPerson> getPersons() {
		return persons;
	}
	/**
	 * @param persons the persons to set
	 */
	public void setPersons(List<ShortPerson> persons) {
		this.persons = persons;
	}

	
	
	
}
