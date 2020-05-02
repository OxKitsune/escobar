package com.kitsune.escobar.narcotic.impl.lsd.effect.impl;

import com.kitsune.escobar.narcotic.impl.lsd.TripManager;
import com.kitsune.escobar.narcotic.impl.lsd.effect.TripEffect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TripSpeedEffect extends TripEffect {


    public TripSpeedEffect() {
        super("trip_speed");
    }

    @Override
    public void applyEffect(Player player) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 25, TripManager.getInstance().getTripIntensity(player)));

    }
}
