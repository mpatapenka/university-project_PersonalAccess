package org.diploma.personalaccess.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.Resource;

@Configuration
@EnableWebMvc
@PropertySource("classpath:web.properties")
@ComponentScan("org.diploma.personalaccess")
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger log = Logger.getLogger(WebConfig.class);

    @Resource
    private Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/resources/favicon.ico");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Bean
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
        bundle.setBasename("messages/messages");

        return bundle;
    }

    @Bean
    public SimpleMappingExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        resolver.setDefaultErrorView("error");
        resolver.setWarnLogCategory("warn");

        return resolver;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        final int defaultUploadSizeInMb = 10;

        int maxUploadSizeInMb = defaultUploadSizeInMb;
        try {
            maxUploadSizeInMb = Integer.valueOf(env.getProperty("files.maxUploadSize"));
        } catch (NumberFormatException e) {
            log.warn("Max upload file size set to default value: '" + defaultUploadSizeInMb
                    + "' megabytes. Use 'web.properties' for configure that size.");
        }

        /* Convert megabytes to bytes */
        final long maxUploadSize = maxUploadSizeInMb * 1024 * 1024;

        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(maxUploadSize);

        return resolver;
    }

}
