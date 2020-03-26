package pl.sda.scheduler.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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

    @Configuration
    @Order(2)
    public static class AppSecurity extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests().antMatchers("/login", "/").permitAll()
                    .antMatchers("/app/**")
                    .authenticated()
                    .and()
                    .formLogin().loginPage("/login").successForwardUrl("/app/loggedIn/")
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/app/logout"))
                    .logoutSuccessUrl("/");
        }
    }

    @Configuration
    @Order(1)
    public static class ApiSecurity extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers("/api/clients/", "/api/appointments")
                    .authenticated()
                    .and().httpBasic()
                    .and().csrf().disable();
        }
    }
    @Override //TODO remove after done
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }
}
