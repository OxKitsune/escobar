package com.kitsune.escobar.narcotic.impl.lsd.effect.impl;

import com.kitsune.escobar.narcotic.impl.lsd.effect.TripEffect;
import com.kitsune.escobar.util.MathUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class TripParanoiaSoundEffect extends TripEffect {

    private final Sound[] PARANOIA_SOUNDS = new Sound[] {

            Sound.ENTITY_ENDERMAN_STARE,
            Sound.BLOCK_PORTAL_AMBIENT,
            Sound.ENTITY_CREEPER_PRIMED,
            Sound.ENTITY_ARROW_SHOOT,
            Sound.ENTITY_GHAST_SCREAM,
            Sound.ENTITY_ARROW_HIT_PLAYER,

    };

    public TripParanoiaSoundEffect() {
        super("trip_generic_sound");
    }

    @Override
    public void applyEffect(Player player) {

        player.playSound(player.getLocation().clone().add(MathUtils.randomInt(-5, 5), MathUtils.randomInt(-5, 5), MathUtils.randomInt(-5, 5)), PARANOIA_SOUNDS[(int) Math.floor(PARANOIA_SOUNDS.length * Math.random())], MathUtils.randomFloat(0.1f, 0.5f), MathUtils.randomFloat(0.1f, 2.0f));

    }
}
