package com.kitsune.escobar.narcotic.impl.lsd.effect.impl;

import com.kitsune.escobar.Escobar;
import com.kitsune.escobar.narcotic.impl.lsd.FloatingBlock;
import com.kitsune.escobar.narcotic.impl.lsd.TripManager;
import com.kitsune.escobar.narcotic.impl.lsd.effect.TripEffect;
import com.kitsune.escobar.util.MathUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TripFloatingBlockEffect extends TripEffect {

    private final List<Material> POSSIBLE_MATERIALS = Arrays.stream(Material.values()).filter(Material::isBlock).collect(Collectors.toList());

    public TripFloatingBlockEffect () {
        super("trip_floating_block");
    }

    @Override
    public void applyEffect(Player player) {

        for(int i = 0; i < 4 * TripManager.getInstance().getTripIntensity(player); i++){
            Block targetBlock = player.getLocation().clone().add(MathUtils.randomDouble(-10, 10), MathUtils.randomDouble(-5, 5), MathUtils.randomDouble(-10, 10)).getBlock();
            BlockData blockData = targetBlock != null && targetBlock.getType() != Material.AIR ? targetBlock.getBlockData() : POSSIBLE_MATERIALS.get((int) Math.floor(Math.random() * POSSIBLE_MATERIALS.size())).createBlockData();

            FloatingBlock floatingBlock = new FloatingBlock(targetBlock.getLocation(), blockData, player);

            floatingBlock.createFloatingBlock();

            new BukkitRunnable(){

                int count = 0;
                final int maxDuration = MathUtils.randomInt(15, 60);
                final Location location = targetBlock.getLocation();

                @Override
                public void run() {
                    location.add(0, 0.25, 0);
                    floatingBlock.teleport(location);

                    if(count > maxDuration){
                        floatingBlock.destroy();
                        this.cancel();
                    }
                    count++;
                }
            }.runTaskTimer(Escobar.getInstance(), 0, 1);
        }
    }
}
