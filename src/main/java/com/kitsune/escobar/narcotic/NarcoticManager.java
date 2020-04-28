package com.kitsune.escobar.narcotic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NarcoticManager {

    /* Map containing all narcotic types */
    private Map<String, Narcotic> narcotics;

    public NarcoticManager () {
        this.narcotics = new HashMap<>();
    }

    /**
     * Register the narcotic.
     *
     * @param narcotic - the narcotic
     */
    public void registerNarcotic (Narcotic narcotic){
        narcotics.computeIfAbsent(narcotic.getId(), id -> narcotic);
    }

    public Collection<Narcotic> getNarcotics() {
        return narcotics.values();
    }
}
