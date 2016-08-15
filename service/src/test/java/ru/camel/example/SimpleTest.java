package ru.camel.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
@Service
@Scope("singleton")
public class SimpleTest {
    public static String URL = "http://localhost:%d/identity";
    @Value("${server.port}")
    Integer port;
    @Autowired
    ClientHttpRequestFactory requestFactory;



    public ResponseEntity find(String uid, IdentityResourceType type) {
        String url = getURL() + String.format("/%s/%s", type.name(), uid);
        ResponseEntity findResponseEntity = getRestTemplate().exchange(url,
                HttpMethod.GET, getEmptyHttpEntity(), new ParameterizedTypeReference<String>() {
                });
        return findResponseEntity;
    }

    public ResponseEntity enableIdentity(String uid, IdentityResourceType type) {
        IdentityRequest entity = new IdentityRequest();
        entity.setStatus(Identity.Status.enabled);
        String url = getURL() + String.format("/%s/%s", type.name(), uid);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<IdentityRequest> updateEntity = new HttpEntity<>(entity, headers);
        ResponseEntity updateResponseEntity = getRestTemplate().exchange(url,
                HttpMethod.PATCH, updateEntity, new ParameterizedTypeReference<String>() {
                });
        return updateResponseEntity;
    }


    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new TestRestTemplate();
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

}
