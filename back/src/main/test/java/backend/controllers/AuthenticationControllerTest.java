package backend.controllers;

import backend.requests.AuthenticationRequest;
import backend.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith({MockitoExtension.class})
class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    AuthenticationRequest authenticationRequest;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        authenticationRequest = AuthenticationRequest.builder()
                .email("user@gmail.com")
                .password("123")
                .build();
    }

    @Test
    void authenticateValidRequestReturnsOkResponse() {


    }

    @Test
    void logout_ValidRequest_CallsSecurityContextLogoutHandler() {

    }

    @Test
    void register_ValidRequest_ReturnsOkResponse() {

    }
}