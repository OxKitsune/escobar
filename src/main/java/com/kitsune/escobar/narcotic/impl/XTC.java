package com.kitsune.escobar.narcotic.impl;

import com.google.common.collect.ImmutableList;
import com.kitsune.escobar.narcotic.Narcotic;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class XTC extends Narcotic {

    private final Material type;
    private final String description;
    private final double strength;

    public XTC (String id, double strength, Material type, String description){
        super(id);

        this.strength = strength;
        this.type = type;
        this.description = description;
    }

    @Override
    public String getDisplayName() {
        return ChatColor.DARK_PURPLE + "XTC Pill";
    }

    @Override
    public List<String> getDescription() {
        return ImmutableList.of(
                ChatColor.GRAY + "Top Quality Dutch XTC",
                description
        );
    }

    @Override
    public Material getMaterial() {
        return type;
    }

    @Override
    public void applyEffect(Player player) {

        int duration = (int) Math.floor(100 * 20 * strength);
        int effectStrength =  1 + (int) Math.round(strength);

        // Apply effects
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, effectStrength));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, duration, effectStrength));
        player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, duration, effectStrength));

        // Decrease amount
        player.getEquipment().getItemInMainHand().setAmount(player.getEquipment().getItemInMainHand().getAmount()-1);

        // Play "Lick" sound
        player.getLocation().getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_GENERIC_EAT, 2.0f, 1.2f);

    }

    @Override
    public void onRegister() {

        // Add crafting recipe to server
        ShapedRecipe shapedRecipe = new ShapedRecipe(getItemStack(1));
        shapedRecipe.shape("ADA", "GTG", "ADA");

        shapedRecipe.setIngredient('A', Material.AIR);
        shapedRecipe.setIngredient('D', Material.GLOWSTONE_DUST);
        shapedRecipe.setIngredient('G', Material.GUNPOWDER);
        shapedRecipe.setIngredient('T', type);

        Bukkit.getServer().addRecipe(shapedRecipe);

    }
}
