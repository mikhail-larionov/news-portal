package com.dataart.newsportal.service;

import java.util.Optional;

public enum Headings {
    NEWS("news"),
    SPORT("sport"),
    ANIMALS("animals"),
    TECHNOLOGIES("technologies"),
    DIFFERENT("different");
    private final String heading;

    Headings(String heading) {
        this.heading = heading;
    }

    public static Optional<Headings> getHeadingByName(String name) {
        if (name == null) {
            return Optional.empty();
        }

        for (var value : Headings.values())
            if (value.heading.equals(name)) {
                return Optional.of(value);
            }

        return Optional.empty();
    }

}
