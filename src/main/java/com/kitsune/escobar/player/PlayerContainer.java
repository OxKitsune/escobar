package com.kitsune.escobar.player;

import com.kitsune.escobar.EscobarPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class PlayerContainer {

    /* Singleton instance */
    private static PlayerContainer instance;

    /* The map that contains player */
    private Map<UUID, EscobarPlayer> playerContainer;

    public PlayerContainer () {
        this.playerContainer = new HashMap<>();
    }

    /**
     * Get the {@link EscobarPlayer} associated with this player.
     *
     * @param player - the player
     *
     * @return - an {@link Optional} containing the result, this {@link Optional} will be empty if the player doesn't exist
     */
    public Optional<EscobarPlayer> getPlayer (UUID player) {
        if(playerContainer.containsKey(player)){
            return Optional.of(playerContainer.get(player));
        }

        return Optional.empty();
    }

    public void addPlayer (EscobarPlayer escobarPlayer){
        playerContainer.computeIfAbsent(escobarPlayer.getUniqueId(), uuid -> escobarPlayer);
    }

    /**
     * Get the singleton instance
     *
     * @return - the singleton instance
     */
    public static PlayerContainer getInstance () {

        /* Check if the singleton instance already exists */
        if(instance == null){
            instance = new PlayerContainer();
        }

        return instance;
    }
}
