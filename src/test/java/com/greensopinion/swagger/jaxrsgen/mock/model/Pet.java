/*******************************************************************************
 * Copyright (c) 2014, 2015 Tasktop Technologies.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Tasktop EULA
 * which accompanies this distribution, and is available at
 * http://tasktop.com/legal
 *******************************************************************************/

package com.greensopinion.swagger.jaxrsgen.mock.model;

import java.net.URI;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class Pet extends PetValues {

	@ApiModelProperty(required = true)
	private long id;

	private URI url;

	private Date created;

	public long getId() {
		return id;
	}

	public Date getCreated() {
		return created;
	}

	public URI getUrl() {
		return url;
	}

}
