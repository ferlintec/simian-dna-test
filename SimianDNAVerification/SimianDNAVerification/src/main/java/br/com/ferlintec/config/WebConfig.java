package br.com.ferlintec.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.ferlintec.converter.YamlJackson2HttpMessageConverter;

/*
 * Content Negotiation
 * Permite gerar retorno em vários formatos.
 * JSON, XML e X-YAML
 * 
 * EnableWebMvc
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");
	
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer ) {
		
		// Via EXTENSION. localhost:8080/person.x-yaml
		
//		 configurer.favorParameter(false)
//		 .ignoreAcceptHeader(false)
//		 .defaultContentType(MediaType.APPLICATION_JSON)
//		 .mediaType("json", MediaType.APPLICATION_JSON) 
//		 .mediaType("xml", MediaType.APPLICATION_XML)
//		 .mediaType("x-yaml", MEDIA_TYPE_YML);
		 
		
		// Via QUERY PARAM. localhost:8080/person?mediaType=xml 
//		
		  configurer.favorPathExtension(false) .favorParameter(true)
		  .parameterName("mediaType") .ignoreAcceptHeader(true)
		  .useRegisteredExtensionsOnly(false)
		  .defaultContentType(MediaType.APPLICATION_JSON) 
			.mediaType("json", MediaType.APPLICATION_JSON) 
			.mediaType("xml", MediaType.APPLICATION_XML)
		  .mediaType("x-yaml", MEDIA_TYPE_YML);
		 
		
		 // Pelo cabeçalho (head)
		 // Incluir no head da requisião o parâmetro Accept com valor application/xml, por exemplo.
		 
//		configurer.favorPathExtension(false)
//		.favorParameter(false)
//		.ignoreAcceptHeader(false)
//		.useRegisteredExtensionsOnly(false)
//		.defaultContentType(MediaType.APPLICATION_JSON)
//		.mediaType("json", MediaType.APPLICATION_JSON)
//		.mediaType("xml", MediaType.APPLICATION_XML)
//		.mediaType("x-yaml", MEDIA_TYPE_YML);

	}

}
