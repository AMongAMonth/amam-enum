package com.amam.amamenum.config;

import com.amam.amamenum.controller.ControllerAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplateConfig {

    @Bean
    @ConditionalOnMissingBean
    public ControllerAdvice controllerAdvice() {
        return new ControllerAdvice();
    }
}
