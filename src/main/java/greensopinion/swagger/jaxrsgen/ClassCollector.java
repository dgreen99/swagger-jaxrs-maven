/*******************************************************************************
 * Copyright (c) 2015 Tasktop Technologies.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package greensopinion.swagger.jaxrsgen;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.annotation.Annotation;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

public class ClassCollector {

	private final Set<Class<?>> classes = new HashSet<>();

	public void addClasses(ClassLoader classLoader, String packageName, Class<? extends Annotation> annotation) {
		checkNotNull(classLoader);
		checkNotNull(packageName);
		checkNotNull(annotation);
		ConfigurationBuilder configurationBuilder = ConfigurationBuilder.build(packageName, classLoader);
		Reflections reflections = new Reflections(configurationBuilder);

		classes.addAll(reflections.getTypesAnnotatedWith(annotation));
	}

	public List<Class<?>> getClasses() {
		return classes.stream().sorted(Comparator.comparing(Class::getName)).collect(Collectors.toList());
	}
}
