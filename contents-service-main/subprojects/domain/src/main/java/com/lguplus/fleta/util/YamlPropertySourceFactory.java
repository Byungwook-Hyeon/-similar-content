package com.lguplus.fleta.util;

import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

/**
 *
 * @author Minwoo Lee
 * @since 1.0
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

    /**
     *
     */
    @Override
    public PropertySource<?> createPropertySource(final String name,
                                                  final EncodedResource resource) {

        final YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        final Resource yaml = resource.getResource();
        factory.setResources(yaml);

        final Properties properties = factory.getObject();
        final String sourceName = name == null ? yaml.getFilename() : name;
		return new PropertiesPropertySource(sourceName == null ? "" : sourceName,
				Optional.of(properties).get());
	}
}
