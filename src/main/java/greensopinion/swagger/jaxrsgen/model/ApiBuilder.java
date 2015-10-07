package greensopinion.swagger.jaxrsgen.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.ws.rs.Path;

import com.google.common.collect.Lists;

import io.swagger.annotations.Api;

public class ApiBuilder {

	private final List<ServiceBuilder> serviceBuilders = Lists.newArrayList();

	private String version;

	private String apiBasePath;

	public ApiBuilder service(Class<?> clazz) {
		checkNotNull(clazz, "Must provide a service class");

		ServiceBuilder builder = Service.builder();

		Path path = clazz.getAnnotation(Path.class);
		if (path != null) {
			builder.path(path.value());
		}
		Api api = clazz.getAnnotation(Api.class);
		if (api != null) {
			builder.path(api.value()).description(api.description());
		}

		builder.methods(clazz);

		serviceBuilders.add(builder);

		return this;
	}

	public ApiBuilder version(String version) {
		this.version = checkNotNull(version, "Must provide a version");
		return this;
	}

	public ApiBuilder basePath(String apiBasePath) {
		this.apiBasePath = checkNotNull(apiBasePath, "Must provide apiBasePath");
		return this;
	}

	public ApiRoot create() {
		List<Service> services = Lists.newArrayListWithCapacity(serviceBuilders.size());
		for (ServiceBuilder builder : serviceBuilders) {
			services.add(builder.version(version).basePath(apiBasePath).create());
		}
		return new ApiRoot(version, services);
	}

	ApiBuilder() {
	}

}
