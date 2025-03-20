To ensure that the request to /api/start is accessible only via HTTPS, you need to configure your Spring Boot application to enforce HTTPS. Here are the steps to achieve this:  
1. Generate a Self-Signed Certificate (if you don't have one):  
```bash
keytool -genkeypair -alias servercert -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore myserverkeystore.p12 -validity 3650
```
2. Configure Spring Boot to Use HTTPS: Add the following properties to your application.yml file:  
```yaml
spring:
  ssl:
    bundle:
      jks:
        web-server:
          key:
            alias: "servercert"
          keystore:
            location: "classpath:/keystore/myserverkeystore.p12"
            password: "myserverkeystorepw"
            type: "PKCS12"
server:
  ssl:
    bundle: web-server
    client-auth: none
  port: 8443
```