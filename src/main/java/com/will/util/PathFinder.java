package com.will.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathFinder {
    private static final String EXTENSION = "%s.jsp";

    public static String find(String path) {
        return String.format(EXTENSION, path);
    }
}
