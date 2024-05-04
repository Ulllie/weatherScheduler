package org.ulllie.weather.interceptors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
public class OpenWeatherInterceptorKey implements ClientHttpRequestInterceptor {

    @Value("${open-weather.api-key}")
    private String API_KEY;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        URI originalUri  = request.getURI();

        StringBuilder uriBuilder = new StringBuilder(originalUri.toString());

        if (originalUri.getQuery() == null) {
            uriBuilder.append('?');
        } else {
            uriBuilder.append('&');
        }

        uriBuilder.append("appid=").append(API_KEY);

        HttpRequest modifiedRequest = new HttpRequestWrapper(request) {
            @Override
            public URI getURI() {
                return URI.create(uriBuilder.toString());
            }
        };

        return execution.execute(modifiedRequest, body);
    }
}
