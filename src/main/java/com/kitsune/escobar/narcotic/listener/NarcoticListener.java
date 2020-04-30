package com.kitsune.escobar.narcotic.listener;

import com.kitsune.escobar.Escobar;
import com.kitsune.escobar.narcotic.Narcotic;
import com.kitsune.escobar.narcotic.NarcoticManager;
import com.kitsune.escobar.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class NarcoticListener implements Listener {

    private final NarcoticManager narcoticManager;

    public NarcoticListener (NarcoticManager narcoticManager) {
        this.narcoticManager = narcoticManager;

        Bukkit.getServer().getPluginManager().registerEvents(this, Escobar.getInstance());
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerInteractEvent (PlayerInteractEvent e){


        // Ignore offhand cuz who uses that lmao
        if(e.getHand() == EquipmentSlot.OFF_HAND) return;

        // Only listen to right click events
        if(e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        // Make sure an item is involved in this event
        if(e.getItem() == null || !e.getItem().hasItemMeta()) return;

        // Apply narcotic effect
        narcoticManager.getNarcotics()
                .stream()
                .filter(n -> n.getItemStack(1).isSimilar(e.getItem()))
                .findFirst()
                .ifPresent(narcotic -> narcotic.applyEffect(e.getPlayer()));

        // Cancel event
        e.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockPlaceEvent (BlockBreakEvent e){

        ItemStack item = e.getPlayer().getEquipment().getItemInMainHand();

        // Make sure an item is involved in this event
        if(item == null || item.getType() == Material.AIR|| !item.hasItemMeta()) return;

        // Apply narcotic effect
        boolean isNarcotic = narcoticManager.getNarcotics()
                .stream()
                .anyMatch(narcotic -> narcotic.getItemStack(1).isSimilar(item));

        if(isNarcotic){
            e.setCancelled(true);
        }

    }

}
