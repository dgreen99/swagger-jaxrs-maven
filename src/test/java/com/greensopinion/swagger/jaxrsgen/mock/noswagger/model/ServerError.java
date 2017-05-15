/*******************************************************************************
 * Copyright (c) 2007, 2014 David Green and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     David Green - initial API and implementation
 *******************************************************************************/

package com.greensopinion.swagger.jaxrsgen.mock.noswagger.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.text.MessageFormat;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;

public class ServerError {

	private final String code;

	private final String message;

	private final String detail;

	public ServerError(Throwable throwable) {
		checkNotNull(throwable, "Must provide throwable");
		code = throwable.getClass().getSimpleName();
		message = computeMessage(throwable);
		detail = Throwables.getStackTraceAsString(throwable);
	}

	private String computeMessage(Throwable t) {
		Optional<Throwable> found = Iterables.tryFind(Throwables.getCausalChain(t), new Predicate<Throwable>() {

			@Override
			public boolean apply(Throwable input) {
				return input != null && !Strings.isNullOrEmpty(input.getMessage());
			}
		});
		if (found.isPresent()) {
			return found.get().getMessage();
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDetail() {
		return detail;
	}

	@Override
	public String toString() {
		return MessageFormat.format("code: {0} message: {1}", code, message);
	}
}
