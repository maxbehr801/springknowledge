# REST CLIENT
* REST Client
* Request / Response Logging
* REST Endpoint
* Blocking and Reactive Calls
* Testslices

# RestClient vs. WebClient
## RestClient
Der [RestClient](src/main/java/info/maxbehr/spring/restclient/consumer/NotesClient.java) wird mit Hilfe der
[RestConsumerConfig](src/main/java/info/maxbehr/spring/restclient/consumer/RestConsumerConfig.java) konfiguriert.
Dabei wird im Builder auch ein Interceptor hinzugefügt, der für das Request- und Responselogging verwendet wird.
```java
return builder
        .baseUrl(BASE_URL)
        .requestInterceptor(requestInterceptor)
        .build();
```

### Responselogging
Der Response ist ein Stream, der daher nur einmal ausgelesen werden kann. Der Response muss also "kopiert" werden (siehe [Interceptor](src/main/java/info/maxbehr/spring/restclient/consumer/ClientLoggerRequestInterceptor.java)).
```java
private ClientHttpResponse logResponse(HttpRequest request, ClientHttpResponse response) throws IOException {
    log.info("Response status: {}", response.getStatusCode());
    logHeaders(response.getHeaders());

    byte[] responseBody = response.getBody().readAllBytes();
    if (responseBody.length > 0) {
        log.info("Request body: {}", new String(responseBody, StandardCharsets.UTF_8));
    }

    return new BufferingClientHttpResponseWrapper(response, responseBody);
}
```