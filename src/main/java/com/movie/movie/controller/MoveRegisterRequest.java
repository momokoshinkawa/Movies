package com.movie.movie.controller;

public class MoveRegisterRequest {
    private String title;
    private Integer releaseYear;

    public MoveRegisterRequest(String title, Integer releaseYear) {
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
