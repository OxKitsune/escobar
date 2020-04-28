package com.kitsune.escobar.narcotic.listener;

import com.kitsune.escobar.Escobar;
import com.kitsune.escobar.narcotic.Narcotic;
import com.kitsune.escobar.narcotic.NarcoticManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class NarcoticListener implements Listener {

    private final NarcoticManager narcoticManager;

    public NarcoticListener (NarcoticManager narcoticManager) {
        this.narcoticManager = narcoticManager;

        Bukkit.getServer().getPluginManager().registerEvents(this, Escobar.getInstance());
    }

    @EventHandler (priority = EventPriority.HIGH, ignoreCancelled = true)
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

    }

}
