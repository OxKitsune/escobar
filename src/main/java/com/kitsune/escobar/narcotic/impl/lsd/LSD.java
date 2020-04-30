package com.kitsune.escobar.narcotic.impl.lsd;

import com.google.common.collect.ImmutableList;
import com.kitsune.escobar.Escobar;
import com.kitsune.escobar.narcotic.Narcotic;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class LSD extends Narcotic {

    public LSD () {
        super("lsd");
    }

    @Override
    public String getDisplayName() {
        return ChatColor.DARK_PURPLE + "LSD";
    }

    @Override
    public List<String> getDescription() {
        return ImmutableList.of(ChatColor.GRAY + "Looks like ordinary paper");
    }

    @Override
    public Material getMaterial() {
        return Material.PAPER;
    }

    @Override
    public ItemStack getItemStack(int amount) {
        ItemStack itemStack =  super.getItemStack(amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);

        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        itemStack.setItemMeta(itemMeta);


        return itemStack;
    }

    @Override
    public void applyEffect(Player player) {
        TripManager.getInstance().addTrippingPlayer(player);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Escobar.getInstance(), () -> {
            TripManager.getInstance().removeTrippingPlayer(player);

        }, 60 * 20);

        // Decrease amount
        player.getEquipment().getItemInMainHand().setAmount(player.getEquipment().getItemInMainHand().getAmount()-1);

        // Play "Lick" sound
        player.getLocation().getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_GENERIC_DRINK, 2.0f, 1.2f);
    }

    @Override
    public void onRegister() {
        // Add crafting recipe to server
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(Escobar.getInstance(), getId()), getItemStack(1));
        shapedRecipe.shape("RMG", "NPN", "GMR");

        shapedRecipe.setIngredient('R', Material.REDSTONE);
        shapedRecipe.setIngredient('M', Material.GLISTERING_MELON_SLICE);
        shapedRecipe.setIngredient('G', Material.GLOWSTONE_DUST);
        shapedRecipe.setIngredient('P', Material.PAPER);
        shapedRecipe.setIngredient('N', Material.GRASS);

        Bukkit.getServer().addRecipe(shapedRecipe);
    }
}
