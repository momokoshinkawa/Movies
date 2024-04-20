package com.movie.movie.controller.request;

public class MovieUpdateRequest {
    private String title;
    private Integer releaseYear;

    public MovieUpdateRequest(String title, Integer releaseYear) {
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
