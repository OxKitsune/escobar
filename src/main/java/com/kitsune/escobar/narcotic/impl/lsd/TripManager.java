package com.kitsune.escobar.narcotic.impl.lsd;

import com.google.common.collect.ImmutableList;
import com.kitsune.escobar.Escobar;
import com.kitsune.escobar.narcotic.impl.lsd.listener.TripListener;
import com.kitsune.escobar.util.Logger;
import com.kitsune.escobar.util.MathUtils;
import com.kitsune.escobar.util.ReflectionUtils;
import javafx.geometry.Pos;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_15_R1.EntityFallingBlock;
import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.IBlockData;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntity;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_15_R1.PacketPlayOutSpawnEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_15_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftFallingBlock;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import sun.rmi.runtime.Log;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TripManager {

    /* Singleton instance */
    private static TripManager instance;

    /* List of cauldrons filled with lysergic acid deithylamide */
    private List<Block> lsdCauldrons;

    /* List of tripping player */
    private Map<Player, Integer> trippingPlayers;

    public TripManager () {
        this.lsdCauldrons = new ArrayList<>();
        this.trippingPlayers = new HashMap<>();

        new TripListener(this);

        registerRecipes();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Escobar.getInstance(), () -> {

            lsdCauldrons.forEach(block -> {
                block.getWorld().spawnParticle(Particle.SPELL_WITCH, block.getLocation().clone().add(0.5, 0.25, 0.5), 5, 0.2f, 0.2f, 0.2f, 0.15f);
            });

        }, 30, 5);

        List<Float> skyColours = ImmutableList.of(
                0f,
                3.0f,
                4.0f
        );

        new BukkitRunnable() {
            @Override
            public void run() {

                int speedCount = 0;
                int slowCount = 0;

                for (Player player : trippingPlayers.keySet()) {


                    if(MathUtils.randomInt(0, 100) <= trippingPlayers.get(player)){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20, 3));
                    }

                    if(MathUtils.randomInt(0, 100) < 5 * trippingPlayers.get(player)){
                        changeSkyColour(player, skyColours.get((int) Math.floor(Math.random() * skyColours.size())));
                    }

                    if(speedCount == 3){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (int) Math.floor(25 * (1.2 * trippingPlayers.get(player))), 2));
                    }

                    if(slowCount == 6){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (int) Math.floor(25 * (1.2 * trippingPlayers.get(player))), 4));

                    }

                    if(MathUtils.randomInt(0, 100) <= 10 * trippingPlayers.get(player)){
                        int amount = MathUtils.randomInt(2, 6) * trippingPlayers.get(player);

                        List<Material> possibleBlocks = Arrays.stream(Material.values()).filter(Material::isBlock).collect(Collectors.toList());

                        for(int i = 0; i < amount; i++){

                            Block block = player.getWorld().getBlockAt(player.getLocation().getBlockX() + MathUtils.randomInt(-30, 30), player.getLocation().getBlockY() + MathUtils.randomInt(-3, 3), player.getLocation().getBlockZ() + MathUtils.randomInt(-30, 30));

                            new FloatingBlock(block.getLocation().clone().add(0, 1, 0), possibleBlocks.get((int) Math.floor(Math.random() * possibleBlocks.size())).createBlockData(), player).startFloating();

                        }
                    }

                    speedCount++;
                    slowCount++;

                    if(speedCount > 3){
                        speedCount = 0;
                    }

                    if(slowCount > 6){
                        slowCount = 0;
                    }
                }
            }
        }.runTaskTimer(Escobar.getInstance(), 30, 10);

        new BukkitRunnable() {

            @Override
            public void run() {

                for (Player player : trippingPlayers.keySet()) {
                    if (player.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                        player.removePotionEffect(PotionEffectType.BLINDNESS);
                    }
                    else {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10 * trippingPlayers.get(player), 3));
                    }
                }

            }
        }.runTaskTimer(Escobar.getInstance(), 30, 4);

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
     * Changes the sky-box colour for the player.
     *
     * @param player   the player
     * @param fadeTime the time it takes to fade
     * @param colour   the colour of the sky-box
     */
    public void changeSkyColour (Player player, float fadeTime, float colour) {
        sendPacket(player, Skybox.FADE_TIME, fadeTime);
        sendPacket(player, Skybox.FADE_VALUE, colour);
    }

    /**
     * Changes the sky-box colour for the player.
     *
     * @param player the player
     * @param colour the colour of the sky-box
     */
    public void changeSkyColour (Player player, float colour){
        sendPacket(player, Skybox.FADE_VALUE, colour);
    }

    private Object getConnection(Player player) throws SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        Class<?> ocbPlayer = ReflectionUtils.getOCBClass("entity.CraftPlayer");
        Method getHandle = ReflectionUtils.getMethod(ocbPlayer, "getHandle");
        Object nmsPlayer = getHandle.invoke(player);
        Field conField = nmsPlayer.getClass().getField("playerConnection");
        return conField.get(nmsPlayer);
    }

    /**
     * Sends a packet that changes the skybox for the player.
     *
     * @param player    the target player
     * @param skybox    a constant declared in {@link Skybox}
     * @param number    the value that represents the colour
     *
     * @return          {@code true} if the packet has been sent successfully, or else {@code false}
     */
    private void sendPacket(Player player, Skybox skybox, float number) {
        try {
            Class<?> packetClass = ReflectionUtils.getNMSClass("PacketPlayOutGameStateChange");
            Constructor<?> packetConstructor = packetClass.getConstructor(int.class, float.class);
            Object packet = packetConstructor.newInstance(skybox.getValue(), number);
            Method sendPacket = ReflectionUtils.getNMSClass("PlayerConnection").getMethod("sendPacket", ReflectionUtils.getNMSClass("Packet"));
            sendPacket.invoke(this.getConnection(player), packet);
        } catch (Exception e) {
            Logger.error("Packet error: " + e);
            e.printStackTrace();
        }
    }

    public void removeTrippingPlayer(Player player) {

        trippingPlayers.remove(player);
        player.removePotionEffect(PotionEffectType.CONFUSION);
        changeSkyColour(player, 0);

    }

    public enum Skybox {
        /**
         * This value represents the value that the skybox will fade to.
         */
        FADE_VALUE(7),
        /**
         * This value represents the tame it takes to fade for the skybox.
         */
        FADE_TIME(8);

        private final int value;

        Skybox(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
