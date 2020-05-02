package com.kitsune.escobar.narcotic.impl.lsd.effect.impl;

import com.kitsune.escobar.Escobar;
import com.kitsune.escobar.narcotic.impl.lsd.effect.TripEffect;
import com.kitsune.escobar.util.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class TripSoundEffect extends TripEffect {

    public TripSoundEffect() {
        super("trip_generic_sound");
    }

    @Override
    public void applyEffect(Player player) {

        Sound sound = Sound.values()[(int) Math.floor(Sound.values().length * Math.random())];
        player.playSound(player.getLocation().clone().add(MathUtils.randomInt(-5, 5), MathUtils.randomInt(-5, 5), MathUtils.randomInt(-5, 5)), sound, MathUtils.randomFloat(0.1f, 2.0f), MathUtils.randomFloat(0.1f, 2.0f));

        Bukkit.getScheduler().scheduleSyncDelayedTask(Escobar.getInstance(), () -> {
            player.stopSound(sound);
        }, 30 * 20);
    }
}
