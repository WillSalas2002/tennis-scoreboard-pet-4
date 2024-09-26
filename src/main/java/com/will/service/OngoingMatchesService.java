package com.will.service;

import com.will.dto.MatchScoreModel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    private static final Map<String, MatchScoreModel> INSTANCE = new HashMap<>();

    private OngoingMatchesService() {
    }

    public static void addMatch(UUID uuid, MatchScoreModel matchScoreModel) {
        INSTANCE.put(uuid.toString(), matchScoreModel);
    }

    public static MatchScoreModel getMatch(String uuidStr) {
        return INSTANCE.get(uuidStr);
    }

    public static void removeMatch(UUID uuid) {
        INSTANCE.remove(uuid.toString());
    }
}
