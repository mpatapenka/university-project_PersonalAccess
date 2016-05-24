package org.diploma.personalaccess.config;

import org.diploma.personalaccess.service.impl.CustomLdapAuthoritiesPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.search.LdapUserSearch;

import javax.annotation.Resource;

/**
 * Spring Security configuration. Used UserDetailsService implementation for application database
 *
 * @author Maksim Patapenka
 */
@Configuration
@EnableWebSecurity
@PropertySource("classpath:ldap.properties")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("org.diploma.personalaccess.service")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Properties file holder. Configured by @PropertySource
     */
    @Resource
    private Environment env;


    @Bean
    public BaseLdapPathContextSource contextSource() {
        final String url = env.getRequiredProperty("url");
        final String dnPattern = env.getRequiredProperty("dn-pattern");
        final String dnPassword = env.getRequiredProperty("dn-password");

        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(url);
        contextSource.setUserDn(dnPattern);
        contextSource.setPassword(dnPassword);

        return contextSource;
    }

    @Bean
    public LdapUserSearch ldapUserSearch() {
        final String searchBase = env.getRequiredProperty("user-search-base");
        final String searchFilter = env.getRequiredProperty("user-search-filter");

        return new FilterBasedLdapUserSearch(searchBase, searchFilter, contextSource());
    }

    @Bean
    public AuthenticationProvider ldapAuthProvider() {
        BindAuthenticator authenticator = new BindAuthenticator(contextSource());
        authenticator.setUserSearch(ldapUserSearch());

        return new LdapAuthenticationProvider(authenticator, customAuthPopulator());
    }

    @Bean
    public CustomLdapAuthoritiesPopulator customAuthPopulator() {
        return new CustomLdapAuthoritiesPopulator();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .authenticationProvider(ldapAuthProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling()
                .accessDeniedPage("/error/403")
            .and()

            .authorizeRequests()
                .antMatchers("/login**").anonymous()
                .antMatchers("/*").permitAll()
                .antMatchers("/error**").permitAll()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()

            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
            .and()

            .logout()
                .logoutUrl("/j_spring_security_logout")
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
            .and()

            .csrf().disable();
    }

}
