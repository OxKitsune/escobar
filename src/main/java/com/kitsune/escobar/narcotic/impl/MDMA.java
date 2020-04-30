package com.kitsune.escobar.narcotic.impl;

import com.google.common.collect.ImmutableList;
import com.kitsune.escobar.Escobar;
import com.kitsune.escobar.narcotic.Narcotic;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class MDMA extends Narcotic {

    public MDMA() {
        super("mdma");
    }

    @Override
    public String getDisplayName() {
        return ChatColor.LIGHT_PURPLE + "MDMA";
    }

    @Override
    public List<String> getDescription() {
        return ImmutableList.of(
                ChatColor.GRAY + "Big crystal chunks"
        );
    }

    @Override
    public Material getMaterial() {
        return Material.QUARTZ;
    }

    @Override
    public void applyEffect(Player player) {

        // Apply effects
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40 * 20, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 40 * 20, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 40 * 20, 2));

        // Decrease amount
        player.getEquipment().getItemInMainHand().setAmount(player.getEquipment().getItemInMainHand().getAmount()-1);

        // Play "Lick" sound
        player.getLocation().getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_GENERIC_DRINK, 2.0f, 1.2f);
    }

    @Override
    public void onRegister() {

        // Add crafting recipe to server
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(Escobar.getInstance(), getId()), getItemStack(1));
        shapedRecipe.shape("ADA", "GQG", "ADA");

        shapedRecipe.setIngredient('A', Material.AIR);
        shapedRecipe.setIngredient('D', Material.GLOWSTONE_DUST);
        shapedRecipe.setIngredient('G', Material.GUNPOWDER);
        shapedRecipe.setIngredient('Q', Material.QUARTZ);

        Bukkit.getServer().addRecipe(shapedRecipe);
    }
}
