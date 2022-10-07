package ch.egli.lovelinebackend;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CorsConfig {

	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
						.allowedOrigins("http://localhost:4200/");
			}
		};
	}*/
}
