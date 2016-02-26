package org.diploma.personalaccess.config;

import org.apache.log4j.Logger;
import org.diploma.personalaccess.holder.PeriodHolder;
import org.diploma.personalaccess.holder.impl.PeriodHolderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * Spring web configuration
 *
 * @author Maksim Patapenka
 */
@Configuration
@EnableWebMvc
@PropertySource("classpath:web.properties")
@ComponentScan("org.diploma.personalaccess")
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * Logger Log4j
     */
    private static final Logger log = Logger.getLogger(WebConfig.class);

    /**
     * Properties file holder. Configured by @PropertySource
     */
    @Resource
    private Environment env;



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/resources/favicon.ico");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
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
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
        bundle.setBasename("localization/messages");
        bundle.setDefaultEncoding("UTF-8");

        return bundle;
    }

    @Bean
    public LocaleResolver localeResolver() {
        final Locale russianLocale = new Locale("ru");

        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(russianLocale);

        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");

        return interceptor;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        /* Default max upload file size */
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

    @Bean
    public PeriodHolder periodHolder() {
        return new PeriodHolderImpl();
    }

}
