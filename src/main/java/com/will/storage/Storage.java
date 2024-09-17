package com.will.storage;

import com.will.entity.MatchScore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Storage {
    private static final Map<String, MatchScore> STORAGE = new HashMap<>();

    private Storage() {
    }

    public static void addMatch(UUID uuid, MatchScore matchScore) {
        STORAGE.put(uuid.toString(), matchScore);
    }

    public static MatchScore getMatch(String uuidStr) {
        return STORAGE.get(uuidStr);
    }

    public static void removeMatch(UUID uuid) {
        STORAGE.remove(uuid.toString());
    }
}