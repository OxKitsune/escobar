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

public class Cocaine extends Narcotic {

    /**
     * Construct a new cocaine narcotic
     */
    public Cocaine() {
        super("cocaine");
    }

    @Override
    public void onRegister() {

        // Add crafting recipe to server
        ShapedRecipe shapedRecipe = new ShapedRecipe(getItemStack(1));
        shapedRecipe.shape("ADA", "GSG", "ADA");

        shapedRecipe.setIngredient('A', Material.AIR);
        shapedRecipe.setIngredient('D', Material.GLOWSTONE_DUST);
        shapedRecipe.setIngredient('G', Material.GUNPOWDER);
        shapedRecipe.setIngredient('S', Material.SUGAR);

        Bukkit.getServer().addRecipe(shapedRecipe);
    }

    @Override
    public String getDisplayName() {
        return ChatColor.BOLD + "Cocaine";
    }

    @Override
    public List<String> getDescription() {
        return ImmutableList.of(
                ChatColor.GRAY + "Smells like gasoline..."
        );
    }

    @Override
    public Material getMaterial() {
        return Material.SUGAR;
    }

    @Override
    public void applyEffect(Player player) {

        // Duration is in ticks, so multiply by 20
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40 * 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40 * 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40 * 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40 * 20, 1));

        // Decrease amount
        player.getEquipment().getItemInMainHand().setAmount(player.getEquipment().getItemInMainHand().getAmount()-1);

        // Play "Sniff" sound
        player.playSound(player.getLocation(), Sound.BLOCK_BAMBOO_SAPLING_HIT, 1.0f, 1.4f);

    }
}
