package com.kitsune.escobar.narcotic.impl.lsd.effect.impl;

import com.kitsune.escobar.narcotic.impl.lsd.TripManager;
import com.kitsune.escobar.narcotic.impl.lsd.effect.TripEffect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TripNightVisionEffect extends TripEffect {


    public TripNightVisionEffect() {
        super("trip_slow");
    }

    @Override
    public void applyEffect(Player player) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 15, TripManager.getInstance().getTripIntensity(player)));

    }
}
