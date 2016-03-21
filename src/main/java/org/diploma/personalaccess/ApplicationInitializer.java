package org.diploma.personalaccess;

import org.diploma.personalaccess.config.WebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * With helps this class application set up a root context
 *
 * @author Maksim Patapenka
 */
public class ApplicationInitializer implements WebApplicationInitializer {

    /**
     * Name of dispatcher servlet. Main root servlet of application
     */
    private static final String DISPATCHER = "dispatcher";

    /**
     * Name of character encoding filter
     */
    private static final String CHARSET_FILTER = "charsetFilter";


    /**
     * Execute prepared configs of application
     *
     * @param servletContext of application
     *
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);

        servletContext.addListener(new ContextLoaderListener(context));


        /* Creating character encoding filter */
        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter(CHARSET_FILTER,
                new CharacterEncodingFilter());
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
        characterEncodingFilter.addMappingForUrlPatterns(null, true, "/*");


        /* Creating Dispatcher servlet */
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER, new DispatcherServlet(context));
        dispatcher.addMapping("/");
        /* That init parameters gives us handle exceptions by controllers */
        dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        dispatcher.setLoadOnStartup(1);
    }

}
