package com.kitsune.escobar.narcotic.impl.lsd.effect.impl;

import com.google.common.collect.ImmutableList;
import com.kitsune.escobar.narcotic.impl.lsd.effect.TripEffect;
import com.kitsune.escobar.util.Logger;
import com.kitsune.escobar.util.ReflectionUtils;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TripSkyEffect extends TripEffect {


    private final List<Float> SKY_COLOURS = ImmutableList.of(2.0f, 3.0f);

    public TripSkyEffect() {
        super("trip_sky");
    }

    @Override
    public void applyEffect(Player player) {

        changeSkyColour(player, SKY_COLOURS.get((int) Math.floor(Math.random() * SKY_COLOURS.size())));

    }

    public static void resetSkyColour (Player player){
        changeSkyColour(player, 0.0f);
    }

    /**
     * Changes the sky-box colour for the player.
     *
     * @param player   the player
     * @param fadeTime the time it takes to fade
     * @param colour   the colour of the sky-box
     */
    private static void changeSkyColour (Player player, float fadeTime, float colour) {
        sendPacket(player, Skybox.FADE_TIME, fadeTime);
        sendPacket(player, Skybox.FADE_VALUE, colour);
    }

    /**
     * Changes the sky-box colour for the player.
     *
     * @param player the player
     * @param colour the colour of the sky-box
     */
    private static void changeSkyColour (Player player, float colour){
        sendPacket(player, Skybox.FADE_VALUE, colour);
    }

    private static Object getConnection(Player player) throws SecurityException,
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
    private static void sendPacket(Player player, Skybox skybox, float number) {
        try {
            Class<?> packetClass = ReflectionUtils.getNMSClass("PacketPlayOutGameStateChange");
            Constructor<?> packetConstructor = packetClass.getConstructor(int.class, float.class);
            Object packet = packetConstructor.newInstance(skybox.getValue(), number);
            Method sendPacket = ReflectionUtils.getNMSClass("PlayerConnection").getMethod("sendPacket", ReflectionUtils.getNMSClass("Packet"));
            sendPacket.invoke(getConnection(player), packet);
        } catch (Exception e) {
            Logger.error("Packet error: " + e);
            e.printStackTrace();
        }
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
