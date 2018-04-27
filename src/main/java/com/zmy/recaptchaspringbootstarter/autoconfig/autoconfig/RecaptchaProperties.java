package com.zmy.recaptchaspringbootstarter.autoconfig.autoconfig;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("recaptcha")
public class RecaptchaProperties {

    private String secretKey;

    private String remoteUrl;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }
}
