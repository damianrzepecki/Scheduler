package pl.sda.scheduler.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebAppSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                inMemoryAuthentication()
                .withUser("admin").password("{noop}1234").roles("ADMIN")
                .and()
                .withUser("user").password("{noop}1234").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/login","/").permitAll()
                .antMatchers("/app/**","/api/**")
                .authenticated()
                .and()
                .formLogin().loginPage("/login").successForwardUrl("/app/loggedIn/")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/app/logout"))
                .logoutSuccessUrl("/");
    }
}