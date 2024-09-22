package com.will.service;

import com.will.dto.MatchScore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    private static final Map<String, MatchScore> INSTANCE = new HashMap<>();

    private OngoingMatchesService() {
    }

    public static void addMatch(UUID uuid, MatchScore matchScore) {
        INSTANCE.put(uuid.toString(), matchScore);
    }

    public static MatchScore getMatch(String uuidStr) {
        return INSTANCE.get(uuidStr);
    }

    public static void removeMatch(UUID uuid) {
        INSTANCE.remove(uuid.toString());
    }
}
