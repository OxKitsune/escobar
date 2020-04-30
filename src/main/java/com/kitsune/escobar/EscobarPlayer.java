package com.kitsune.escobar;

import java.util.Map;
import java.util.UUID;

/**
 * Class used to store data related to the escobar plugin
 */
public class EscobarPlayer {

    /* Id of the player */
    private final UUID uniqueId;

    /* Map that contains the amount of times the player used a narcotic */
    private Map<String, Integer> narcoticUsage;

    /**
     * Construct a new Escobar Player
     *
     * @param uniqueId - the id of the player
     */
    public EscobarPlayer(UUID uniqueId){
        this.uniqueId = uniqueId;
    }

    /**
     * Get the unique id of this player
     *
     * @return - the unique id of this player
     */
    public UUID getUniqueId() {
        return uniqueId;
    }
}
