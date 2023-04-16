package com.digitalbooking.Back.security;


import com.digitalbooking.Back.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers( "/v2/api-docs", "/swagger-ui/**",
                        "/swagger-resources/**").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/users/create").permitAll()
                .antMatchers("/features/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/cities/create", "/cities/update", "/cities/delete/{id}").permitAll()
                .antMatchers("/products/create", "/products/update", "/products/delete/{id}").permitAll()
                /* USER */
                .antMatchers("/reservations/create").hasAuthority("USER")
                /* ADMIN */
                //.antMatchers("/cities/create", "/cities/update", "/cities/delete/{id}").hasAuthority("ADMIN")
                //.antMatchers("/products/create", "/products/update", "/products/delete/{id}").hasAuthority("ADMIN")
                /*.antMatchers("/features/**").hasAuthority("ADMIN")
                .antMatchers("/images/**").hasAuthority("ADMIN")*/
                //.antMatchers("/products/**", "/cities/**", "/categories").permitAll()

                .anyRequest().permitAll();
        //.anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
