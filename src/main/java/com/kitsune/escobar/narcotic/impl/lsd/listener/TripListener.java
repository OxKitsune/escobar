package com.kitsune.escobar.narcotic.impl.lsd.listener;

import com.kitsune.escobar.Escobar;
import com.kitsune.escobar.narcotic.NarcoticManager;
import com.kitsune.escobar.narcotic.impl.lsd.LSD;
import com.kitsune.escobar.narcotic.impl.lsd.TripManager;
import com.kitsune.escobar.util.Logger;
import com.kitsune.escobar.util.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class TripListener implements Listener {

    private final TripManager tripManager;

    public TripListener (TripManager tripManager) {

        this.tripManager = tripManager;

        Bukkit.getServer().getPluginManager().registerEvents(this, Escobar.getInstance());
    }


    @EventHandler (priority = EventPriority.HIGHEST)
    public void onPlayerInteractEvent (PlayerInteractEvent e){

        // Ignore actions that arent right click
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;


        if(e.getPlayer().getEquipment().getItemInMainHand() != null && e.getPlayer().getEquipment().getItemInMainHand().getType() != Material.AIR && e.getPlayer().getEquipment().getItemInMainHand().isSimilar(tripManager.getLiquidLSD())){

            // Cancel event
            e.setCancelled(true);

            Block block = e.getClickedBlock();
            if(block.getType() == Material.CAULDRON){
                Levelled levelled = (Levelled) block.getBlockData();

                if(levelled.getLevel() == 0){
                    tripManager.fillCauldron(block);

                    e.getPlayer().getEquipment().getItemInMainHand().setAmount(e.getPlayer().getEquipment().getItemInMainHand().getAmount()-1);
                    e.getPlayer().updateInventory();
                    e.getPlayer().getWorld().playSound(block.getLocation(), Sound.ITEM_BUCKET_EMPTY, 1.0f, 0.9f);

                    // Player particle effects
                    for(int i = 0; i < 15; i++){
                        ParticleEffect.SPELL_MOB.display(block.getLocation().clone().add(0.5, 0.25f, 0.5), Color.FUCHSIA);
                    }

                }

            }
        }

    }

    @EventHandler
    public void onBlockBreakEvent (BlockBreakEvent e) {

        if (e.getBlock().getType() != Material.CAULDRON) return;

        if(tripManager.getLSDCauldronLevel (e.getBlock()) != -1){
            tripManager.removeLSDCauldron(e.getBlock());
        }
    }


    @EventHandler
    public void onItemDropEvent (PlayerDropItemEvent e){

        ItemStack item = e.getItemDrop().getItemStack();

        if(item.isSimilar(TripManager.getInstance().getAbsorbantPaper(1)) && item.getAmount() == 1){

            // Is absorbent paper

            e.getItemDrop().setPickupDelay(15);

            Bukkit.getScheduler().scheduleSyncDelayedTask(Escobar.getInstance(), () -> {

                Block block = e.getItemDrop().getLocation().getBlock();
                if(block.getType() == Material.CAULDRON){

                    if(TripManager.getInstance().getLSDCauldronLevel(block) >= 1){
                       Escobar.getInstance().getNarcoticManager().getNarcotics().stream().filter(narcotic -> narcotic.getId().equalsIgnoreCase("lsd")).findFirst().ifPresent(lsd -> {
                           e.getItemDrop().setItemStack(lsd.getItemStack(1));
                        });

                        TripManager.getInstance().decreaseCauldronLevel(block);

                        e.getItemDrop().getLocation().getWorld().playSound(e.getItemDrop().getLocation(), Sound.ENTITY_VILLAGER_WORK_CLERIC, 1.0f, 0.75f);

                        e.getItemDrop().getLocation().getWorld().spawnParticle(Particle.SPELL_MOB, e.getItemDrop().getLocation(), 1000);

                    }

                }

            }, 10);
        }

    }

    public TripManager getTripManager() {
        return tripManager;
    }
}
