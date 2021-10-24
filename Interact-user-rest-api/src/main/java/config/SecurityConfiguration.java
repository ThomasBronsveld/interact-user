package config;

import authentication.DatabaseUserDetailsPasswordService;
import authentication.DatabaseUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final DatabaseUserDetailsService databaseUserDetailsService;
    private final DatabaseUserDetailsPasswordService databaseUserDetailsPasswordService;

    public SecurityConfiguration(
            DatabaseUserDetailsService databaseUserDetailsService,
            DatabaseUserDetailsPasswordService databaseUserDetailsPasswordService) {
        this.databaseUserDetailsService = databaseUserDetailsService;
        this.databaseUserDetailsPasswordService = databaseUserDetailsPasswordService;
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/registration")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(this.databaseUserDetailsService);
        provider.setUserDetailsPasswordService(this.databaseUserDetailsPasswordService);

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }
}
