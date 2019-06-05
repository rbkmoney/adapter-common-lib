package com.rbkmoney.adapter.common.component;

import com.rbkmoney.woody.api.trace.ContextUtils;
import com.rbkmoney.woody.api.trace.context.TraceContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.web.client.MetricsRestTemplateCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleRestTemplate {

    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, int networkTimeout) {
        int executionTimeout =
                ContextUtils.getExecutionTimeout(TraceContext.getCurrentTraceData().getServiceSpan(), networkTimeout);
        RestTemplate restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(executionTimeout))
                .setReadTimeout(Duration.ofMillis(executionTimeout))
                .build();

        Jaxb2RootElementHttpMessageConverter jaxbConverter = new Jaxb2RootElementHttpMessageConverter();
        jaxbConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML));
        jaxbConverter.setDefaultCharset(Charset.forName("UTF-8"));

        AllEncompassingFormHttpMessageConverter encompassingConverter = new AllEncompassingFormHttpMessageConverter();
        encompassingConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN/**, MediaType.TEXT_HTML*/));

        List<HttpMessageConverter<?>> messageConverterList = new ArrayList<>();
        messageConverterList.add(jaxbConverter);
        messageConverterList.add(encompassingConverter);
        messageConverterList.add(stringHttpMessageConverter);
        restTemplate.setMessageConverters(messageConverterList);

        return restTemplate;
    }

    public RestTemplateBuilder restTemplateBuilder(HttpComponentsClientHttpRequestFactory requestFactory,
                                                   MetricsRestTemplateCustomizer metricsRestTemplateCustomizer) {
        return new RestTemplateBuilder()
                .requestFactory(() -> requestFactory)
                .additionalCustomizers(metricsRestTemplateCustomizer);
    }

    public HttpComponentsClientHttpRequestFactory requestFactory(CloseableHttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return requestFactory;
    }

    public CloseableHttpClient httpClient() {
        return HttpClients.custom()
                .disableAutomaticRetries()
                .build();
    }

}