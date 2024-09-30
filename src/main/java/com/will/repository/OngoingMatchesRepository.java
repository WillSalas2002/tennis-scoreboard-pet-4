package com.will.repository;

import com.will.dto.MatchScoreModel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesRepository {

    private static final Map<String, MatchScoreModel> STORAGE = new HashMap<>();

    private OngoingMatchesRepository() {
    }

    public static void addMatch(UUID uuid, MatchScoreModel matchScoreModel) {
        STORAGE.put(uuid.toString(), matchScoreModel);
    }

    public static MatchScoreModel findMatch(String uuidStr) {
        return STORAGE.get(uuidStr);
    }

    public static void removeMatch(UUID uuid) {
        STORAGE.remove(uuid.toString());
    }
}
