package com.kitsune.escobar.narcotic.impl.lsd;

import com.google.common.collect.ImmutableList;
import com.kitsune.escobar.Escobar;
import com.kitsune.escobar.narcotic.impl.lsd.effect.TripEffect;
import com.kitsune.escobar.narcotic.impl.lsd.effect.impl.TripDarknessEffect;
import com.kitsune.escobar.narcotic.impl.lsd.effect.impl.TripEntityEffect;
import com.kitsune.escobar.narcotic.impl.lsd.effect.impl.TripFloatingBlockEffect;
import com.kitsune.escobar.narcotic.impl.lsd.effect.impl.TripNightVisionEffect;
import com.kitsune.escobar.narcotic.impl.lsd.effect.impl.TripParanoiaSoundEffect;
import com.kitsune.escobar.narcotic.impl.lsd.effect.impl.TripSkyEffect;
import com.kitsune.escobar.narcotic.impl.lsd.effect.impl.TripSlowEffect;
import com.kitsune.escobar.narcotic.impl.lsd.effect.impl.TripSoundEffect;
import com.kitsune.escobar.narcotic.impl.lsd.effect.impl.TripSpeedEffect;
import com.kitsune.escobar.narcotic.impl.lsd.listener.TripListener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripManager {

    /* Singleton instance */
    private static TripManager instance;

    /* List of cauldrons filled with lysergic acid deithylamide */
    private List<Block> lsdCauldrons;

    /* List of tripping player */
    private Map<Player, Integer> trippingPlayers;

    /* List of trip effects */
    private List<TripEffect> tripEffects;

    public TripManager () {
        this.lsdCauldrons = new ArrayList<>();
        this.trippingPlayers = new HashMap<>();
        this.tripEffects = new ArrayList<>();

        new TripListener(this);

        registerRecipes();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Escobar.getInstance(), () -> {

            lsdCauldrons.forEach(block -> {
                block.getWorld().spawnParticle(Particle.SPELL_WITCH, block.getLocation().clone().add(0.5, 0.25, 0.5), 5, 0.2f, 0.2f, 0.2f, 0.15f);
            });

        }, 30, 5);

        tripEffects.add(new TripSpeedEffect());
        tripEffects.add(new TripSlowEffect());
        tripEffects.add(new TripNightVisionEffect());
        tripEffects.add(new TripParanoiaSoundEffect());
        tripEffects.add(new TripFloatingBlockEffect());
        tripEffects.add(new TripEntityEffect());

        // Make darkness effect more common
        tripEffects.add(new TripDarknessEffect());
        tripEffects.add(new TripDarknessEffect());
        tripEffects.add(new TripDarknessEffect());


        // Add trip sound twice to make it more common
        tripEffects.add(new TripSoundEffect());
        tripEffects.add(new TripSoundEffect());

        // Make sky effect VERY common
        tripEffects.add(new TripSkyEffect());
        tripEffects.add(new TripSkyEffect());
        tripEffects.add(new TripSkyEffect());
        tripEffects.add(new TripSkyEffect());

        // Apply trip effects
        Bukkit.getScheduler().runTaskTimer(Escobar.getInstance(), () -> {

            trippingPlayers.keySet().forEach(player -> {
                tripEffects.get((int) Math.floor(Math.random() * tripEffects.size())).applyEffect(player);
            });

        }, 30, 20);


    }

    private void registerRecipes () {

        ShapedRecipe liquidLsdRecipe = new ShapedRecipe(new NamespacedKey(Escobar.getInstance(), "liquid_lsd"), getLiquidLSD());

        liquidLsdRecipe.shape("BMB", "FRF", "NWN");

        liquidLsdRecipe.setIngredient('B', Material.BROWN_MUSHROOM);
        liquidLsdRecipe.setIngredient('M', Material.GLISTERING_MELON_SLICE);
        liquidLsdRecipe.setIngredient('F', Material.FERMENTED_SPIDER_EYE);
        liquidLsdRecipe.setIngredient('R', Material.RED_MUSHROOM);
        liquidLsdRecipe.setIngredient('N', Material.NETHER_WART);
        liquidLsdRecipe.setIngredient('W', Material.WATER_BUCKET);

        Bukkit.getServer().addRecipe(liquidLsdRecipe);

        ShapedRecipe absorbentPaper = new ShapedRecipe(new NamespacedKey(Escobar.getInstance(), "absorbent_paper"), getAbsorbantPaper(1));

        absorbentPaper.shape("RGR", "GPG", "RGR");

        absorbentPaper.setIngredient('R', Material.REDSTONE);
        absorbentPaper.setIngredient('G', Material.GLOWSTONE_DUST);
        absorbentPaper.setIngredient('P', Material.PAPER);

        Bukkit.getServer().addRecipe(absorbentPaper);

    }


    /**
     * Add tripping player
     *
     * @param player - the player to add
     */
    public void addTrippingPlayer (Player player) {

        trippingPlayers.computeIfAbsent(player, p -> {

            p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,  60 * 20, 5));
            new BukkitRunnable() {
                int count = 0;

                @Override
                public void run() {

                    trippingPlayers.put(p, trippingPlayers.get(p) + 1);

                    count++;
                    if(count >= 5){
                        this.cancel();
                    }
                }
            }.runTaskTimer(Escobar.getInstance(), 10 * 20, 10 * 20);

            return 1;
        });
    }

    public ItemStack getLiquidLSD (){
        ItemStack itemStack = new ItemStack(Material.WATER_BUCKET);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Lysergic Acid Diethylamide");
        itemMeta.setLore(ImmutableList.of(
                ChatColor.GRAY + "Not recommended for consumption..."
        ));

        itemMeta.addEnchant(Enchantment.FROST_WALKER, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public ItemStack getAbsorbantPaper(int amount) {
        ItemStack itemStack = new ItemStack(Material.PAPER, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.AQUA + "Absorbent Paper");
        itemMeta.setLore(ImmutableList.of(
                ChatColor.GRAY + "Absorbes liquids..."
        ));

        itemMeta.addEnchant(Enchantment.FROST_WALKER, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    /**
     * Fill a cauldron with lysergic acid diethylamide.
     *
     * @param block - the target cauldron
     */
    public void fillCauldron (Block block){
        if(block.getType() != Material.CAULDRON) throw new IllegalArgumentException("Target block is not a CAULDRON!");


        Levelled cauldron = (Levelled) block.getBlockData();
        cauldron.setLevel(cauldron.getMaximumLevel());
        block.setBlockData(cauldron);

        lsdCauldrons.add(block);
    }

    /**
     * Get the singleton instance.
     *
     * @return - the singleton instance
     */
    public static TripManager getInstance() {

        if(instance == null){
            instance = new TripManager();
        }

        return instance;

    }

    /**
     * Get the level of LSD in the cauldron
     *
     * @param block - the cauldron
     *
     * @return - the level of LSD in the cauldron, will return -1 if its not an LSD cauldron
     */
    public int getLSDCauldronLevel(Block block) {

        if(lsdCauldrons.contains(block)){
            Levelled cauldron = (Levelled) block.getBlockData();
            return cauldron.getLevel();
        }

        return -1;
    }

    public void removeLSDCauldron(Block block) {
        lsdCauldrons.remove(block);
    }

    public void decreaseCauldronLevel(Block block) {

        if(getLSDCauldronLevel(block) >= 1){
            Levelled cauldron = (Levelled) block.getBlockData();
            cauldron.setLevel(cauldron.getLevel()-1);

            if(cauldron.getLevel() == 0){
                removeLSDCauldron(block);
            }

            block.setBlockData(cauldron);
        }

    }

    /**
     * Removes the trip from the player if they're tripping.
     *
     * @param player - the player
     */
    public void removeTrippingPlayer(Player player) {

        trippingPlayers.remove(player);
        player.removePotionEffect(PotionEffectType.CONFUSION);
        TripSkyEffect.resetSkyColour(player);
    }

    /**
     * Get the trip intensity for the player.
     *
     * @param player - the player
     *
     * @return - the trip intensity if the player is tripping or else {@code 0}
     */
    public int getTripIntensity (Player player){
        return trippingPlayers.getOrDefault(player, 0);
    }
}
