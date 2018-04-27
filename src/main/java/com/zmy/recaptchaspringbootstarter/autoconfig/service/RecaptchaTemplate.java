package com.zmy.recaptchaspringbootstarter.autoconfig.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmy.recaptchaspringbootstarter.autoconfig.autoconfig.RecaptchaProperties;
import com.zmy.recaptchaspringbootstarter.autoconfig.entity.RecaptchaResponse;
import com.zmy.recaptchaspringbootstarter.autoconfig.exception.RecaptchaException;
import com.zmy.recaptchaspringbootstarter.autoconfig.util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

public class RecaptchaTemplate {
    private final RecaptchaProperties properties;

    public RecaptchaTemplate(RecaptchaProperties properties) {
        this.properties = properties;
    }

    public RecaptchaResponse validate(String response, String remoteIp) {
        Map<String, String> requestMap = new HashMap<>(3);
        requestMap.put("secret", properties.getSecretKey());
        requestMap.put("response", response);
        requestMap.put("remoteip", remoteIp);
        RecaptchaResponse rres = null;
        try {
            String res = HttpClientUtil.post(properties.getRemoteUrl(), requestMap);
            ObjectMapper om = new ObjectMapper();
            rres = om.readValue(res, RecaptchaResponse.class);
        } catch (Exception e) {
            throw new RecaptchaException("google recaptcha work failed");
        }
        return rres;
    }

    public RecaptchaResponse validate(String response) {
        return validate(response, "");
    }
}
