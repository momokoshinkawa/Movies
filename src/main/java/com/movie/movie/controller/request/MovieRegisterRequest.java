package com.movie.movie.controller.request;

public class MovieRegisterRequest {
    private String title;
    private Integer releaseYear;

    public MovieRegisterRequest(String title, Integer releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }
}
