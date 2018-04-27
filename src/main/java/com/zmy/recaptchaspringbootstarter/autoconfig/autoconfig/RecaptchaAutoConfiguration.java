package com.zmy.recaptchaspringbootstarter.autoconfig.autoconfig;


import com.zmy.recaptchaspringbootstarter.autoconfig.service.RecaptchaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@EnableConfigurationProperties(RecaptchaProperties.class)
public class RecaptchaAutoConfiguration {
    @Autowired
    private RecaptchaProperties properties;

    @Bean
    public RecaptchaTemplate recaptchaTemplate() {
        return new RecaptchaTemplate(properties);
    }
}
