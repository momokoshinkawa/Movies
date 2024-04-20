package com.movie.movie.controller.response;

public class MovieDeleteResponse {
    private String message;

    public MovieDeleteResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
