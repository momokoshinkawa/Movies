package com.movie.movie.entity;

public class YearRangeValidator {
    private Integer fromYear;
    private Integer toYear;

    public YearRangeValidator(Integer fromYear, Integer toYear) {
        this.fromYear = fromYear;
        this.toYear = toYear;
    }

    public Integer getFromYear() {
        return fromYear;
    }

    public Integer getToYear() {
        return toYear;
    }
}
