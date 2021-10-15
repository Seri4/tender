package ru.notSleepAllDay.tender.client.config;

import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

public class DefaultClientConfiguration {

    @Value("${defaultClient.retryer.period}")
    public Integer retryerPeriod;

    @Value("${defaultClient.retryer.maxPeriod}")
    public Integer retryerMaxPeriod;

    @Value("${defaultClient.retryer.attempts}")
    public Integer retryerAttempts;

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public ErrorDecoder errorDecoder() {
        return new DefaultClientErrorDecoder();
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(retryerPeriod, retryerMaxPeriod, retryerAttempts);
    }

    @Bean
    RequestInterceptor feignRequestInterceptor() {
        return null;
    }
}
