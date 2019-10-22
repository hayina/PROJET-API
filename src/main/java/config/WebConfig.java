package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import interceptor.LoginInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan({"controllers"})
public class WebConfig implements WebMvcConfigurer {

	
//	@Bean
//   	public InternalResourceViewResolver resolver() {
//	   InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//      	resolver.setViewClass(JstlView.class);
//      	resolver.setPrefix("/WEB-INF/");
//      	resolver.setSuffix(".jsp");
//      	return resolver;
//   	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(30000000);
//	    multipartResolver.setMaxUploadSize(-1);
	    return multipartResolver;
	}
   
	
	@Bean
	public LoginInterceptor loginInterceptor() {
	    return new LoginInterceptor();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/REACT-APP/**")
		.addResourceLocations("/REACT-APP/")
//         .resourceChain(true)
//         .addResolver(new PathResourceResolver())
		; 
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( loginInterceptor() );
    }
}
