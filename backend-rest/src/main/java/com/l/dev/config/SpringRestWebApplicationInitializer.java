//package com.l.dev.config;
//
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//public class SpringRestWebApplicationInitializer implements WebApplicationInitializer {
//
//    public static final String DISPATCHER_NAME = "dispatcher";
//    public static final String MAIN_MAPPING_URL = "/";
//    public static final int LOAD_ON_STARTUP = 1;
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
//        applicationContext.setConfigLocation("WEB-INF/applicationContext.xml");
////        applicationContext.register(JpaRepositoryConfig.class);
//        servletContext.addListener(new ContextLoaderListener(applicationContext));
//        applicationContext.setServletContext(servletContext);
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_NAME,
//                new DispatcherServlet(applicationContext));
//        dispatcher.addMapping(MAIN_MAPPING_URL);
//        dispatcher.setLoadOnStartup(LOAD_ON_STARTUP);
//
//    }
//}
