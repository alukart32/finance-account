package com.r2d2.financeaccount;

import com.r2d2.financeaccount.utils.security.core.PrincipalDetailsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class FinanceaccountApplication {

    @Bean
    public UserDetailsService userDetailsService() {
        return new PrincipalDetailsService();
    }

    public static void main(String[] args) {
        SpringApplication.run(FinanceaccountApplication.class, args);
    }

}
