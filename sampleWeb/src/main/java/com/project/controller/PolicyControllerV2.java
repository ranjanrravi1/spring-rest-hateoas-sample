package com.project.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.bean.PolicyBean;
import com.project.resource.ShortPerson;
import com.project.resource.ShortPolicy;
import com.vo.PersonVO;
import com.vo.PolicyVO;

@Controller
@RequestMapping(value ="/policy",
		produces = "application/v2+json", 
		consumes ="application/v2+json")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class PolicyControllerV2 {

	private static final Logger log = LoggerFactory.getLogger(PolicyControllerV2.class);
			
	@Autowired
	public PolicyBean policyBean;
	
	/**
	 * @param policyBean the policyBean to set
	 */
	public void setPolicyBean(PolicyBean policyBean) {
		this.policyBean = policyBean;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ShortPolicy>> getAllPolicies() {

		System.out.println("Version - v2");
		List<PolicyVO> policies = policyBean.getPolicy();

		List<ShortPolicy> resources = new ArrayList<ShortPolicy>(policies.size());
		for (PolicyVO p : policies) {
			ShortPolicy shortPolicy = new ShortPolicy();
			
			shortPolicy.setPolicyId(p.getId());
			shortPolicy.setName(p.getName());
			List<PersonVO> persons = p.getPersonVOs();
			
			List<ShortPerson> shortPersons = new ArrayList<ShortPerson>(p.getPersonVOs().size());
			for (PersonVO personVO : persons) {
				ShortPerson person = new ShortPerson();
				person.setName(personVO.getName());
				person.setPersonId(personVO.getId());
				person.add(linkTo(PolicyControllerV2.class).slash(p.getId()).slash("/ph").
						slash(person.getPersonId()).
						withSelfRel());
				shortPersons.add(person);
			}
					
			shortPolicy.setPersons(shortPersons);
			
			Link detail = linkTo(PolicyControllerV2.class).slash(p.getId()).withSelfRel();
			shortPolicy.add(detail);
			resources.add(shortPolicy);
		}
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Resource<PolicyVO>> getOnlyPolicy(@PathVariable int id) {

		PolicyVO policy = policyBean.getPolicy(id);
		
		Resource<PolicyVO> resource = new Resource<>(policy, linkTo(PolicyControllerV2.class).slash(policy.getId()).withSelfRel());
		if(policy == null){
			return new ResponseEntity<>(resource, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> addPolicy(UriComponentsBuilder b, @RequestBody PolicyVO policyVO){
		log.info("Str "+policyVO);
		
		int returnInt = 0 ;
		
		returnInt =	policyBean.CreatePolicy(policyVO);
		HttpHeaders headers = new HttpHeaders();
		UriComponents uriComponents = b.path("/policy/{id}/ph/").buildAndExpand(returnInt);
		headers.setLocation(uriComponents.toUri());
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> updatePolicy(@RequestBody PolicyVO policyVO){
		
		
		boolean returnStatus =	policyBean.updatePolicy(policyVO);		
		if(returnStatus){
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePolicy(@PathVariable int id){
		log.info("Id: "+id);
		boolean status = policyBean.deletePolicy(id);
		if(status){
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}

}
