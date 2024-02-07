package com.ecomerceApi.Priscila;

import com.ecomerceApi.Priscila.model.ERole;
import com.ecomerceApi.Priscila.request_responseModels.AuthenticationResponse;
import com.ecomerceApi.Priscila.request_responseModels.LoginRequest;
import com.ecomerceApi.Priscila.request_responseModels.UserRegistrationRequest;
import net.bytebuddy.utility.RandomString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.net.URI;
import java.net.URISyntaxException;

@SuppressWarnings("DataFlowIssue")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriscilaApplicationTests {

    @Autowired
    private TestRestTemplate rest;

    @LocalServerPort
    int port;

    @Test
    void testAdminAccess() throws URISyntaxException {
        UserRegistrationRequest login = new UserRegistrationRequest(
                "name " + RandomString.make(12),
                RandomString.make(12),
                RandomString.make(12),
                ERole.ADMIN);

        ResponseEntity<Void> loginResponse = rest.postForEntity(
                new URI("http://localhost:" + port + "/user/register"),
                login,
                Void.class);

        Assertions.assertThat(loginResponse.getStatusCode()).matches(HttpStatusCode::is2xxSuccessful);

        ResponseEntity<AuthenticationResponse> authenticationResponse = rest.postForEntity(
                new URI("http://localhost:" + port + "/login"),
                new LoginRequest(login.getEmail(), login.getPassword()),
                AuthenticationResponse.class);

        Assertions.assertThat(authenticationResponse.getStatusCode()).matches(HttpStatusCode::is2xxSuccessful);

        AuthenticationResponse body = authenticationResponse.getBody();
        String accessToken = body.getAccessToken();
        Assertions.assertThat(accessToken).isNotBlank();

        ResponseEntity<Void> adminResponse = rest.getForEntity(
                new URI("http://localhost:" + port + "/ap1/v1/admin"),
                Void.class
        );

        Assertions.assertThat(adminResponse.getStatusCode()).matches(httpStatusCode -> httpStatusCode.value() == 403);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(accessToken);

        adminResponse = rest.exchange(
                new RequestEntity<Void>(
                        httpHeaders,
                        HttpMethod.GET,
                        new URI("http://localhost:" + port + "/ap1/v1/admin")
                ),
                Void.class
        );

        Assertions.assertThat(adminResponse.getStatusCode()).matches(HttpStatusCode::is2xxSuccessful);

    }

    @Test
    void testCustomerAccess() throws URISyntaxException {
        UserRegistrationRequest login = new UserRegistrationRequest(
                "name " + RandomString.make(12),
                RandomString.make(12),
                RandomString.make(12),
                ERole.CUSTOMER);

        ResponseEntity<Void> loginResponse = rest.postForEntity(
                new URI("http://localhost:" + port + "/user/register"),
                login,
                Void.class);

        Assertions.assertThat(loginResponse.getStatusCode()).matches(HttpStatusCode::is2xxSuccessful);

        ResponseEntity<AuthenticationResponse> authenticationResponse = rest.postForEntity(
                new URI("http://localhost:" + port + "/login"),
                new LoginRequest(login.getEmail(), login.getPassword()),
                AuthenticationResponse.class);

        Assertions.assertThat(authenticationResponse.getStatusCode()).matches(HttpStatusCode::is2xxSuccessful);

        AuthenticationResponse responseBody = authenticationResponse.getBody();

        String accessToken;
        accessToken = responseBody.getAccessToken();
        Assertions.assertThat(accessToken).isNotBlank();


        ResponseEntity<Void> adminResponse = rest.getForEntity(
                new URI("http://localhost:" + port + "/ap1/v1/admin"),
                Void.class
        );

        Assertions.assertThat(adminResponse.getStatusCode()).

                matches(httpStatusCode -> httpStatusCode.value() == 403);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(accessToken);

        adminResponse = rest.exchange(
                new RequestEntity<Void>(
                        httpHeaders,
                        HttpMethod.GET,
                        new

                                URI("http://localhost:" + port + "/ap1/v1/admin")
                ),
                Void.class
        );

        Assertions.assertThat(adminResponse.getStatusCode()).

                matches(httpStatusCode -> httpStatusCode.value() == 403);

    }

}



