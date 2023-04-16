package com.digitalbooking.Back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@EnableWebMvc
@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) {
		/*prepararBD();*/
		SpringApplication.run(BackApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};
	}

		/*private static void prepararBD(){
		Connection connection=null;
		try{
			Class.forName("org.h2.Driver");

			connection= DriverManager.getConnection("jdbc:h2:~/Alexander" +
							";INIT=RUNSCRIPT FROM 'data.sql'",
					"sa","sa");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try{
				connection.close();
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}*/
}
