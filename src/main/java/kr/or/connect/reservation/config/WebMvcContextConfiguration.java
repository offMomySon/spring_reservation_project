package kr.or.connect.reservation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.connect.reservation.objmapper.CustomObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

@Configuration
@EnableWebMvc
@Slf4j
@ComponentScan(basePackages = { "kr.or.connect.reservation.controller" })
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		log.info("configureDefaultServletHandling called");
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("addResourceHandlers called");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/").setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/img/").setCachePeriod(31556926);
		registry.addResourceHandler("/img_map/**").addResourceLocations("classpath:/img_map/").setCachePeriod(31556926);
		registry.addResourceHandler("/htmls/**").addResourceLocations("classpath:/htmls/").setCachePeriod(31556926);
		registry.addResourceHandler("/font/**").addResourceLocations("classpath:/font/").setCachePeriod(31556926);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		log.info("addViewControllers called");
		registry.addViewController("/").setViewName("index");
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		log.info("getInternalResourceViewResolver called");
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		log.info("configureMessageConverters called");
		converters.add(new MappingJackson2HttpMessageConverter(customObjectMapper()));
	}

	public ObjectMapper customObjectMapper() {
		log.info("customObjectMapper called");
		return new CustomObjectMapper();
	}

}
