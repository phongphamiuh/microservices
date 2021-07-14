//package com.hotel.project.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//
//@Configuration
//public class ResourceServerConfig
//        extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and().oauth2ResourceServer(
//                c -> c.opaqueToken(
//                        o -> {
//                            o.introspectionUri("http://localhost:8080/oauth/check_token");
//                            o.introspectionClientCredentials("test", "temp");
//                        })
//        );
//    }
//}
//
