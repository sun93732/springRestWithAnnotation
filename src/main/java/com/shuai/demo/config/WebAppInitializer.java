package com.shuai.demo.config;

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
@Order(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
    protected Class<?>[] getRootConfigClasses() {
		//MLGB，写在这里除非这些Config类上不能带@Configure，否则就会加载两次。
		return new Class[] {/* ServiceConfig.class, DatabaseConfig.class*/ };
    }

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MvcConfig.class  };
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/*"};
	}
	
	@Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }
}
