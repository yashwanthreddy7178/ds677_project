package com.silionie.server.login;

import com.silionie.server.TestBase;
import org.junit.Test;
import org.springframework.http.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginControllerTest extends TestBase {

    @Test
    public void testLoginWithRightCredentials(){
        // GIVEN
        String request = "{ \n" +
                "\t\"username\":\"ferryscanner\",\n" +
                "\t\"password\":\"P@ssword\"\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<AuthenticationResponse> exchange = restTemplate.exchange(
                getUri() + "/login",
                HttpMethod.POST,
                httpEntity,
                AuthenticationResponse.class);

        // THEN
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getBody().getStatus());

    }

    @Test
    public void testLoginNotFoundUser() {
        // GIVEN
        String request = "{ \n" +
                "\t\"username\":\"esilioni\",\n" +
                "\t\"password\":\"P@ssword\"\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<AuthenticationResponse> exchange = restTemplate.exchange(
                getUri() + "/login",
                HttpMethod.POST,
                httpEntity,
                AuthenticationResponse.class);

        // THEN
        assertEquals(HttpStatus.NOT_FOUND, exchange.getBody().getStatus());
        assertEquals("Username:esilioni not found.", exchange.getBody().getUserMessage());

    }

    @Test
    public void testUnauthorisedUser() {
        // GIVEN
        String request = "{ \n" +
                "\t\"username\":\"ferryscanner\",\n" +
                "\t\"password\":\"P@ssword1\"\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<AuthenticationResponse> exchange = restTemplate.exchange(
                getUri() + "/login",
                HttpMethod.POST,
                httpEntity,
                AuthenticationResponse.class);

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getBody().getStatus());

    }
}
