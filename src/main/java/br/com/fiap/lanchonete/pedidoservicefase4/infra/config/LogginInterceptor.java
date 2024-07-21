package br.com.fiap.lanchonete.pedidoservicefase4.infra.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class LogginInterceptor implements ClientHttpRequestInterceptor{

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        // TODO Auto-generated method stub

        logRequest(request, body);



        ClientHttpResponse clientHttpResponse = execution.execute(request, body);


        BufferedClientHttpResponseWrapper wrappedResponse = new BufferedClientHttpResponseWrapper(clientHttpResponse);
        logResponse(wrappedResponse);

        return wrappedResponse;



    }

    private void logResponse(BufferedClientHttpResponseWrapper wrappedResponse) throws IOException {
        // TODO Auto-generated method stub
        System.out.println("===========================response begin================================================");
        System.out.println("Status code  : " + wrappedResponse.getStatusCode());
        System.out.println("Status text  : " + wrappedResponse.getStatusText());
        System.out.println("Headers      : " + wrappedResponse.getHeaders());
        String responseBody = StreamUtils.copyToString(wrappedResponse.getBody(), StandardCharsets.UTF_8);
        System.out.println("Body         : " + responseBody);
    }

    private void logRequest(HttpRequest request, byte[] body) {
        System.out.println("===========================request begin================================================");
        System.out.println("URI         : " + request.getURI());
        System.out.println("Method      : " + request.getMethod());
        System.out.println("Headers     : " + request.getHeaders());
        System.out.println("Request body: " + new String(body, StandardCharsets.UTF_8));
        System.out.println("==========================request end================================================");
    }



}
