package com.crossbowffs.quotelock.api;

import com.crossbowffs.quotelock.model.VnaasCharacter;

public class VnaasNovelQueryParams extends QueryParams {
    public VnaasNovelQueryParams setCharacter(long characterId) {
        setParam("character_id", characterId);
        return this;
    }

    public VnaasNovelQueryParams setCharacter(VnaasCharacter character) {
        return setCharacter(character.getId());
    }

    public VnaasNovelQueryParams setName(String name) {
        setParam("name", name);
        return this;
    }
}
