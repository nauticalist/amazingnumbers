package io.ia;

import java.util.List;
import java.util.stream.Stream;

public enum Property {
    even,
    odd,
    buzz,
    duck,
    palindromic,
    gapful,
    spy,
    square,
    sunny;

    public static List<String> getNames() {
        return Stream.of(Property.values()).map(Property::name).toList();
    }
}
