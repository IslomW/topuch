package com.sharipov.topuch.common.exception;

import com.sharipov.topuch.common.response.ErrorResponse;
import com.sharipov.topuch.domain.exception.PostNotFound;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void returnsStructuredApiError() {
        UUID postId = UUID.fromString("01991ee8-9a70-7000-8000-000000000001");
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/posts/" + postId);

        ResponseEntity<ErrorResponse> response = handler.handleApiException(
                new PostNotFound(postId),
                request
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().status()).isEqualTo(404);
        assertThat(response.getBody().code()).isEqualTo("POST_NOT_FOUND");
        assertThat(response.getBody().path()).isEqualTo("/api/posts/" + postId);
        assertThat(response.getBody().errors()).isEmpty();
        assertThat(response.getBody().timestamp()).isNotNull();
    }

    @Test
    void hidesUnexpectedExceptionDetails() {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/posts");

        ResponseEntity<ErrorResponse> response = handler.handleGeneral(
                new RuntimeException("database password must not leak"),
                request
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().code()).isEqualTo("INTERNAL_SERVER_ERROR");
        assertThat(response.getBody().message()).isEqualTo("An unexpected error occurred");
    }
}
