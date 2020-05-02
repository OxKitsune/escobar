package com.kitsune.escobar.narcotic.impl.lsd.effect.impl;

import com.kitsune.escobar.Escobar;
import com.kitsune.escobar.narcotic.impl.lsd.TripManager;
import com.kitsune.escobar.narcotic.impl.lsd.effect.TripEffect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class TripDarknessEffect extends TripEffect {


    public TripDarknessEffect() {
        super("trip_blind");
    }

    @Override
    public void applyEffect(Player player) {

        new BukkitRunnable() {


            int count = 0;

            @Override
            public void run() {
                if (player.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                }
                else {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10 * TripManager.getInstance().getTripIntensity(player), 3));
                }

                if(count >= 15){
                    this.cancel();
                }

                count++;
            }
        }.runTaskTimer(Escobar.getInstance(), 0, 4);

    }
}
