package com.kitsune.escobar.narcotic.impl.lsd.effect;

import org.bukkit.entity.Player;

public abstract class TripEffect {

    private final String id;

    public TripEffect (String id) {
        this.id = id;
    }

    public abstract void applyEffect (Player player);

    public String getId() {
        return id;
    }
}
