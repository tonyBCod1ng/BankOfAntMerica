package org.perscholas.BankOfAntMerica.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SpringConf {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //http.csrf(csrf -> csrf.disable());
        http.csrf(csrf -> Customizer.withDefaults());

        //         this section says allow all pages EXCEPT the ones that are in the AntPathRequestMatcher
//         anything in AntPathRequestMatcher will require the user to be authenticated
        http.authorizeRequests().requestMatchers(new AntPathRequestMatcher("/admin/**"), new AntPathRequestMatcher("/users/account/"), new AntPathRequestMatcher("users/dashboard"), new AntPathRequestMatcher("/")).authenticated().anyRequest().permitAll();

//         the loginPage parameter is the actual URL of the login page
//         the loginProcessingUrl is the URL that the form will submit to
        http.formLogin(formLogin ->
                formLogin.loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login/loginSubmit")
                        .successForwardUrl(
                        "/auth/success"));
// this is the URL that will log a user out
        http.logout(formLogout -> formLogout
                .invalidateHttpSession(true)
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/"));
        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean(name = "passwordEncoder")
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
