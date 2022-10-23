package br.com.atech.teste.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Configuração do servidor de recursos (API)
 * @author Seigi
 *
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/usuarios/**").authenticated()
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/oauth/**").permitAll();
    }
}