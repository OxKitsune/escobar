package com.kitsune.escobar.narcotic.impl.lsd.effect.impl;

import com.kitsune.escobar.narcotic.impl.lsd.TripManager;
import com.kitsune.escobar.narcotic.impl.lsd.effect.TripEffect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TripSlowEffect extends TripEffect {


    public TripSlowEffect() {
        super("trip_slow");
    }

    @Override
    public void applyEffect(Player player) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 15, 3 * TripManager.getInstance().getTripIntensity(player)));

    }
}
