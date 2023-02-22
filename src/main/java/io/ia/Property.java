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
    sunny,
    jumping,
    sad,
    happy;
    public static List<String> getNames() {
        return Stream.of(Property.values()).map(Property::name).toList();
    }
}
