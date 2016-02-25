package org.diploma.personalaccess;

import org.diploma.personalaccess.config.WebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * With helps this class application set up a root context
 */
public class ApplicationInitializer implements WebApplicationInitializer {

    /**
     * Name of dispatcher servlet. Main root servlet of application
     */
    private static final String DISPATCHER = "dispatcher";

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

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER, new DispatcherServlet(context));
        dispatcher.addMapping("/");
        /* That init parameters gives us handle exceptions by controllers */
        dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        dispatcher.setLoadOnStartup(1);
    }

}
