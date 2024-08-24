package org.perscholas.BankOfAntMerica.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {
    @Autowired
   YourInteceptor yourInteceptor;


    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(yourInteceptor);
    }
}

