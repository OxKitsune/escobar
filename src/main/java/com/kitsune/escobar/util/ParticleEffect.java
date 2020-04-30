package com.kitsune.escobar.util;

import com.google.common.collect.ImmutableList;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public enum ParticleEffect {

    /**
     * Particle effect that's displayed by exploding creepers and tnt:
     * <ul>
     *     <li>Looks like a white/gray cloud.</li>
     *     <li>The speed value influences the speed at which the particles fly off.</li>
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    EXPLOSION_NORMAL(Particle.EXPLOSION_NORMAL, false, true, false, false),

    /**
     * A particle effect which is displayed by exploding ghast fireballs and wither skulls:
     * <ul>
     *      <li>It looks like a gray ball which is fading away
     *      <li>The speed value slightly influences the size of this particle effect
     * </ul>
     */
    EXPLOSION_LARGE(Particle.EXPLOSION_LARGE, false, false, false, false),

    /**
     * A particle effect which is displayed by exploding tnt and creepers:
     * <ul>
     *      <li>It looks like a crowd of gray balls which are fading away
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    EXPLOSION_HUGE(Particle.EXPLOSION_HUGE, false, false, false, false),

    /**
     * A particle effect which is displayed by launching fireworks:
     * <ul>
     *      <li>It looks like a white star which is sparkling
     *      <li>The speed value influences the velocity at which the particle flies off
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    FIREWORKS_SPARK(Particle.FIREWORKS_SPARK, false, true, false, false),

    /**
     * A particle effect which is displayed by swimming entities and arrows in water:
     * <ul>
     *      <li>It looks like a bubble
     *      <li>The speed value influences the velocity at which the particle flies off
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    WATER_BUBBLE(Particle.WATER_BUBBLE, false, true, false, false),

    /**
     * A particle effect which is displayed by swimming entities and shaking wolves:
     * <ul>
     *      <li>It looks like a blue drop
     *      <li>The speed value has no influence on this particle effect
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    WATER_SPLASH(Particle.WATER_SPLASH, false, true, false, false),

    /**
     * A particle effect which is displayed on water when fishing:
     * <ul>
     *      <li>It looks like a blue droplet
     *      <li>The speed value influences the velocity at which the particle flies off
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    WATER_WAKE(Particle.WATER_WAKE, false, true, false, false),

    /**
     * A particle effect which is displayed by water:
     * <ul>
     *      <li>It looks like a tiny blue square
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    SUSPENDED(Particle.SUSPENDED, false, false, false, false),

    /**
     * A particle effect which is displayed by air when close to bedrock and the in the void:
     * <ul>
     *      <li>It looks like a tiny gray square
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    SUSPENDED_DEPTH(Particle.SUSPENDED_DEPTH, false, false, false, false),

    /**
     * A particle effect which is displayed when landing a critical hit and by arrows:
     * <ul>
     *      <li>It looks like a light brown cross
     *      <li>The speed value influences the velocity at which the particle flies off
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    CRIT(Particle.CRIT, false, true, false, false),

    /**
     * A particle effect which is displayed when landing a hit with an enchanted weapon:
     * <ul>
     *      <li>It looks like a cyan star
     *      <li>The speed value influences the velocity at which the particle flies off
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    CRIT_MAGIC(Particle.CRIT_MAGIC, false, true, false, false),

    /**
     * A particle effect which is displayed by primed tnt, torches, droppers, dispensers, end portals, brewing stands and monster spawners:
     * <ul>
     *      <li>It looks like a little gray cloud
     *      <li>The speed value influences the velocity at which the particle flies off
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    SMOKE_NORMAL(Particle.SMOKE_NORMAL, false, true, false, false),

    /**
     * A particle effect which is displayed by fire, minecarts with furnace and blazes:
     * <ul>
     *      <li>It looks like a large gray cloud
     *      <li>The speed value influences the velocity at which the particle flies off
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    SMOKE_LARGE(Particle.SMOKE_LARGE, false, true, false, false),

    /**
     * A particle effect which is displayed when splash potions or bottles o' enchanting hit something:
     * <ul>
     *      <li>It looks like a white swirl
     *      <li>The speed value causes the particle to only move upwards when set to 0
     *      <li>Only the motion on the y-axis can be controlled, the motion on the x- and z-axis are multiplied by 0.1 when setting the values to 0
     * </ul>
     */
    SPELL(Particle.SPELL, false, false, false, false),

    /**
     * A particle effect which is displayed when instant splash potions hit something:
     * <ul>
     *      <li>It looks like a white cross
     *      <li>The speed value causes the particle to only move upwards when set to 0
     *      <li>Only the motion on the y-axis can be controlled, the motion on the x- and z-axis are multiplied by 0.1 when setting the values to 0
     * </ul>
     */
    SPELL_INSTANT(Particle.SPELL_INSTANT, false, false, false, false),

    /**
     * A particle effect which is displayed by entities with active potion effects:
     * <ul>
     *      <li>It looks like a colored swirl
     *      <li>The speed value causes the particle to be colored black when set to 0
     *      <li>The particle color gets lighter when increasing the speed and darker when decreasing the speed
     *      <li>You can set the colour by using </li>
     * </ul>
     */
    SPELL_MOB(Particle.SPELL_MOB, true, false, false, false),

    /**
     * A particle effect which is displayed by entities with active potion effects applied through a beacon:
     * <ul>
     *      <li>It looks like a transparent colored swirl
     *      <li>The speed value causes the particle to be always colored black when set to 0
     *      <li>The particle color gets lighter when increasing the speed and darker when decreasing the speed
     * </ul>
     */
    SPELL_MOB_AMBIENT(Particle.SPELL_MOB_AMBIENT, false, false, false, false),

    /**
     * A particle effect which is displayed by witches:
     * <ul>
     *      <li>It looks like a purple cross
     *      <li>The speed value causes the particle to only move upwards when set to 0
     *      <li>Only the motion on the y-axis can be controlled, the motion on the x- and z-axis are multiplied by 0.1 when setting the values to 0
     * </ul>
     */
    SPELL_WITCH(Particle.SPELL_WITCH, false, false, false, false),

    /**
     * A particle effect which is displayed by blocks beneath a water source:
     * <ul>
     *      <li>It looks like a blue drip
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    DRIP_WATER(Particle.DRIP_WATER, false, false, false, false),

    /**
     * A particle effect which is displayed by blocks beneath a lava source:
     * <ul>
     *      <li>It looks like an orange drip
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    DRIP_LAVA(Particle.DRIP_LAVA, false, false, false, false),

    /**
     * A particle effect which is displayed when attacking a villager in a village:
     * <ul>
     *      <li>It looks like a cracked gray heart
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    VILLAGER_ANGRY(Particle.VILLAGER_ANGRY, false, false, false, false),

    /**
     * A particle effect which is displayed when using bone meal and trading with a villager in a village:
     * <ul>
     *      <li>It looks like a green star
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    VILLAGER_HAPPY(Particle.VILLAGER_HAPPY, false, false, false, false),

    /**
     * A particle effect which is displayed by mycelium:
     * <ul>
     *      <li>It looks like a tiny gray square
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    TOWN_AURA(Particle.TOWN_AURA, false, false, false, false),

    /**
     * A particle effect which is displayed by note blocks:
     * <ul>
     *      <li>It looks like a colored note
     * </ul>
     */
    NOTE(Particle.NOTE, true, false, false, false),

    /**
     * A particle effect which is displayed by nether portals, endermen, ender pearls, eyes of ender, ender chests and dragon eggs:
     * <ul>
     *      <li>It looks like a purple cloud
     *      <li>The speed value influences the spread of this particle effect
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    PORTAL(Particle.PORTAL, false, true, false, false),

    /**
     * A particle effect which is displayed by enchantment tables which are nearby bookshelves:
     * <ul>
     *      <li>It looks like a cryptic white letter
     *      <li>The speed value influences the spread of this particle effect
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    ENCHANTMENT_TABLE(Particle.ENCHANTMENT_TABLE, false, true, false, false),

    /**
     * A particle effect which is displayed by torches, active furnaces, magma cubes and monster spawners:
     * <ul>
     *      <li>It looks like a tiny flame
     *      <li>The speed value influences the velocity at which the particle flies off
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    FLAME(Particle.FLAME, false, true, false, false),

    /**
     * A particle effect which is displayed by lava:
     * <ul>
     *      <li>It looks like a spark
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    LAVA(Particle.LAVA, false, false, false, false),

    /**
     * A particle effect which is displayed when a mob dies:
     * <ul>
     *      <li>It looks like a large white cloud
     *      <li>The speed value influences the velocity at which the particle flies off
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    CLOUD(Particle.CLOUD, false, true, false, false),

    /**
     * A particle effect which is displayed by redstone ore, powered redstone, redstone torches and redstone repeaters:
     * <ul>
     *      <li>It looks like a tiny colored cloud
     *      <li>The speed value causes the particle to be colored red when set to 0
     * </ul>
     */
    REDSTONE(Particle.REDSTONE, true, false, false, false),

    /**
     * A particle effect which is currently unused:
     * <ul>
     *      <li>It looks like a tiny white cloud
     *      <li>The speed value influences the velocity at which the particle flies off
     * </ul>
     */
    SNOWBALL(Particle.SNOWBALL, false, false, false, false),

    /**
     * A particle effect which is currently unused:
     * <ul>
     *      <li>It looks like a tiny white cloud
     *      <li>The speed value influences the velocity at which the particle flies off
     * </ul>
     */
    SNOW_SHOVEL(Particle.SNOW_SHOVEL, false, false, false, false),

    /**
     * A particle effect which is displayed by slimes:
     * <ul>
     *      <li>It looks like a tiny part of the slimeball icon
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    SLIME(Particle.SLIME, false, false, false, false),

    /**
     * A particle effect which is displayed when breeding and taming animals:
     * <ul>
     *      <li>It looks like a red heart
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    HEART(Particle.HEART, false, false, false, false),

    /**
     * A particle effect which is displayed by barriers:
     * <ul>
     *      <li>It looks like a red box with a slash through it
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    BARRIER(Particle.BARRIER, false, false, false, false),

    /**
     * A particle effect which is displayed when breaking a tool or eggs hit a block:
     * <ul>
     *      <li>It looks like a little piece with an item texture
     * </ul>
     */
    ITEM_CRACK(Particle.ITEM_CRACK, false, false, false, true),

    /**
     * A particle effect which is displayed when breaking blocks or sprinting:
     * <ul>
     *      <li>It looks like a little piece with a block texture
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    BLOCK_CRACK(Particle.BLOCK_CRACK, false, false, true, false),

    /**
     * A particle effect which is displayed when falling:
     * <ul>
     *      <li>It looks like a little piece with a block texture
     * </ul>
     */
    BLOCK_DUST(Particle.BLOCK_DUST, false, false, true, false),

    /**
     * A particle effect which is displayed when rain hits the ground:
     * <ul>
     *      <li>It looks like a blue droplet
     *      <li>The speed value has no influence on this particle effect
     * </ul>
     */
    WATER_DROP(Particle.WATER_DROP, false, false, false, false),

    /**
     * A particle effect which is displayed by elder guardians:
     * <ul>
     *      <li>It looks like the shape of the elder guardian
     *      <li>The speed value has no influence on this particle effect
     *      <li>The offset values have no influence on this particle effect
     * </ul>
     */
    MOB_APPEARANCE(Particle.MOB_APPEARANCE, false, false, false, false),

    /**
     * A particle effect which is displayed by the Ender dragon as a special attack:
     * <ul>
     *     <li>It looks like a small purple cloud</li>
     *     <li>The speed value influences the speed at which the particles fly off</li>
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    DRAGON_BREATH(Particle.DRAGON_BREATH, false, true, false, false),

    /**
     * A particle effect which is displayed by the end rod block:
     * <ul>
     *     <li>It looks like a small white diamond</li>
     *     <li>The speed value influences the speed at which the particles fly off</li>
     * </ul>
     */
    END_ROD(Particle.END_ROD, false, true, false, false),


    /**
     * A particle effect which is displayed when you attack with a full damage indicator bar:
     * <ul>
     *     <li>It looks like a small dark red heart</li>
     *     <li>The speed value influences the speed at which the particles fly off</li>
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    DAMAGE_INDICATOR(Particle.DAMAGE_INDICATOR, false, true, false, false),

    /**
     * A particle effect which is displayed when a player hits multiple entities at the same time:
     * <ul>
     *     <li>It looks like a slashing animation</li>
     *     <li>The speed value influences the size of the particles</li>
     * </ul>
     */
    SWEEP_ATTACK(Particle.SWEEP_ATTACK, false, false, false, false),

    /**
     * A particle effect which is displayed at the bottom of several blocks in game:
     * <ul>
     *     <li>It looks like a small cloud</li>
     *     <li>The speed value doesn't have any effect on this particle</li>
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    FALLING_DUST(Particle.FALLING_DUST, false, true, true, false),

    /**
     * A particle effect which is displayed when a player triggers a totem of undying:
     * <ul>
     *     <li>It looks like small green to yellow diamonds</li>
     *     <li>The speed value influences the speed at which the particles fly off</li>
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    TOTEM(Particle.TOTEM, false, true, false, false),

    /**
     * A particle effect which is displayed when a llama spits:
     * <ul>
     *     <li>It looks like a small white cloud</li>
     *     <li>The speed value influences the speed at which the particles fly off</li>
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    SPIT(Particle.SPIT, false, true, false, false),


    /**
     * A particle effect which is displayed when a squid spits ink:
     * <ul>
     *     <li>It looks like a small black cloud</li>
     *     <li>The speed value influences the speed at which the particles fly off</li>
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    SQUID_INK(Particle.SQUID_INK, false, true, false, false),

    /**
     * A particle effect which is displayed when the bubbles produced by an underwater magma block reach the surface:
     * <ul>
     *     <li>It looks like a bubble that's bursting</li>
     *     <li>The speed value influences the speed at which the particles fly off</li>
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    BUBBLE_POP(Particle.BUBBLE_POP, false, true, false, false),


    /**
     * A particle effect which is displayed above underwater magma blocks that pull entities down:
     * <ul>
     *     <li>It looks like a small bubble></li>
     *     <li>The speed value doesn't influence this particle at all</li>
     * </ul>
     */
    CURRENT_DOWN(Particle.CURRENT_DOWN, false, false, false, false),


    /**
     * A particle effect which is displayed above underwater soulsand blocks that pull entities down:
     * <ul>
     *     <li>It looks like a small bubble></li>
     *     <li>The speed value doesn't influence this particle at all</li>
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    BUBBLE_COLUMN_UP(Particle.BUBBLE_COLUMN_UP, false, true, false, false),

    /**
     * A particle effect which is displayed around the heart of sea:
     * <ul>
     *     <li>It looks like a small blue ball with a red dot in the center that moves to the center location</li>
     *     <li>The speed value influences the range the particles start at</li>
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    NAUTILUS(Particle.NAUTILUS, false, true, false, false),

    /**
     * A particle effect which is displayed around dolphins when they swim:
     * <ul>
     *     <li>It looks like a small blue droplet</li>
     *     <li>The speed value doesn't influence this particle at all</li>
     * </ul>
     */
    DOLPHIN(Particle.DOLPHIN, false, false, false, false),

    /**
     * A particle effect which is displayed when a baby panda sneezes:
     * <ul>
     *     <li>It looks like a medium sized translucent green cloud</li>
     *     <li>The speed value influences the speed at which the particles fly off</li>
     * </ul>
     */
    SNEEZE(Particle.SNEEZE, false, false, false, false),

    /**
     * A particle effect which is displayed by a campfire:
     * <ul>
     *     <li>It looks like THICC gray smoke</li>
     *     <li>The speed value influences the speed at which the particles fly off</li>
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    CAMPFIRE_COSY_SMOKE(Particle.CAMPFIRE_COSY_SMOKE, false, true, false, false),

    /**
     * A particle effect which is displayed by a campfire when it's placed on a haybale:
     * <ul>
     *     <li>It looks like THICC gray smoke that's moving in a direction</li>
     *     <li>The speed value influences the speed at which the particles fly off</li>
     *     <li>This is a directional particle effect</li>
     * </ul>
     */
    CAMPFIRE_SIGNAL_SMOKE(Particle.CAMPFIRE_SIGNAL_SMOKE, false, true, false, false),

    /**
     * A particle effect which is displayed when a composter is composting:
     * <ul>
     *     <li>It looks like a small green star.</li>
     *     <li>The speed value doesn't influence this particle at all</li>
     * </ul>
     */
    COMPOSTER(Particle.COMPOSTER, false, false, false, false),

    /**
     * A particle effect which is displayed when a firework rocket explodes:
     * <ul>
     *     <li>It looks like a big white circle that quickly disappears</li>
     *     <li>The speed value doesn't influence this particle at all</li>
     * </ul>
     */
    FLASH(Particle.FLASH, false, false, false, false),

    /**
     * A particle effect which is displayed when lava is on top of a block:
     * <ul>
     *     <li>It looks like a small orange droplet that falls down after a short time</li>
     *     <li>The speed value doesn't influence this particle at all</li>
     * </ul>
     */
    FALLING_LAVA(Particle.FALLING_LAVA, false, false, false, false),


    /**
     * A particle effect which is displayed when a {@link #FALLING_LAVA} particle hits a block
     * Note: This only happens when the particle is spawned naturally
     * <ul>
     *     <li>It looks like a small orange droplet hitting the floor</li>
     *     <li>The speed value doesnt influence this particle at all</li>
     * </ul>
     */
    LANDING_LAVA(Particle.LANDING_LAVA, false, false, false, false),

    /**
     * A particle effect which is displayed when water is on top of a block:
     * <ul>
     *     <li>It looks like a small blue droplet</li>
     *     <li>The speed value doesn't influence this particle at all</li>
     * </ul>
     */
    FALLING_WATER(Particle.FALLING_WATER, false, false, false, false),

    /**
     * A particle effect which is displayed at the bottom of a beehive when there's honey in it:
     * <ul>
     *     <li>It looks like a small yellow droplet that falls down after LONG time</li>
     *     <li>The speed value doesn't influence this particle at all</li>
     * </ul>
     */
    DRIPPING_HONEY(Particle.DRIPPING_HONEY, false, false, false, false),

    /**
     * A particle effect which is displayed when {@link #DRIPPING_HONEY} is falling:
     * <ul>
     *     <li>It looks like a small yellow droplet that falls down</li>
     *     <li>The speed value doesn't influence this particle at all</li>
     * </ul>
     */
    FALLING_HONEY(Particle.FALLING_HONEY, false, false, false, false),

    /**
     * A particle effect which is displayed when {@link #FALLING_HONEY} hits a block:
     * <ul>
     *     <li>It looks like a small yellow droplet that hits a block</li>
     *     <li>The speed value doesn't influence this particle at all</li>
     * </ul>
     */
    LANDING_HONEY(Particle.LANDING_HONEY, false, false, false, false),

    /**
     * A particle effect which is displayed by a bee that has collected nectar and is flying around:
     * <ul>
     *     <li>It looks like a small white ball that's falling</li>
     *     <li>The speed value doesn't influence this particle at all</li>
     * </ul>
     */
    FALLING_NECTAR(Particle.FALLING_NECTAR, false, false, false, false);


    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param xOffset        the x offset for the particle effect
     * @param yOffset        the y offset for the particle effect
     * @param zOffset        the z offset for the particle effect
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     */
    public void display (@NotNull Location targetLocation, double xOffset, double yOffset, double zOffset, double speed, int count){
        targetLocation.getWorld().spawnParticle(this.getParticleType(), targetLocation, count, xOffset, yOffset, zOffset, speed);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     */
    public void display (@NotNull Location targetLocation, double speed, int count){
        display(targetLocation, 0, 0, 0, speed, count);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param count          the amount of particles to display
     */
    public void display (@NotNull Location targetLocation, int count){
        display(targetLocation, 0, count);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     */
    public void display (@NotNull Location targetLocation){
        display(targetLocation, 1);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param xOffset        the x offset for the particle effect
     * @param yOffset        the y offset for the particle effect
     * @param zOffset        the z offset for the particle effect
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param players        the players to display the particle effect to
     */
    public void display (@NotNull Location targetLocation, double xOffset, double yOffset, double zOffset, double speed, int count, @NotNull Collection<? extends Player> players){
        players.forEach(player ->{
            player.spawnParticle(this.getParticleType(), targetLocation, count, xOffset, yOffset, zOffset, speed);
        });
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param players        the players to display the particle effect to
     */
    public void display (@NotNull Location targetLocation, double speed, int count, @NotNull Collection<? extends Player> players){
        display(targetLocation, 0, 0, 0, speed, count, players);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param count          the amount of particles to display
     * @param players        the players to display the particle effect to
     */
    public void display (@NotNull Location targetLocation, int count, @NotNull Collection<? extends Player> players){
        display(targetLocation, 0, count, players);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param players        the players to display the particle effect to
     */
    public void display (@NotNull Location targetLocation, @NotNull Collection<? extends Player> players){
        display(targetLocation, 1, players);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param xOffset        the x offset for the particle effect
     * @param yOffset        the y offset for the particle effect
     * @param zOffset        the z offset for the particle effect
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param player         the player to display the particle effect to
     */
    public void display (@NotNull Location targetLocation, double xOffset, double yOffset, double zOffset, double speed, int count, @NotNull Player player){
        display(targetLocation, xOffset, yOffset, zOffset, speed, count, ImmutableList.of(player));
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param player        the player to display the particle effect to
     */
    public void display (@NotNull Location targetLocation, double speed, int count, @NotNull Player player){
        display(targetLocation, 0, 0, 0, speed, count, player);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param count          the amount of particles to display
     * @param player         the player to display the particle effect to
     */
    public void display (@NotNull Location targetLocation, int count, @NotNull Player player){
        display(targetLocation, 0, count, player);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param player         the player to display the particle effect to
     */
    public void display (@NotNull Location targetLocation, @NotNull Player player){
        display(targetLocation, 1, player);
    }


    /**
     * Displays the particle effect moving in the specified direction.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param direction      the direction the particle effect should move in
     * @param speed          the speed value for the particle effect
     */
    public void display(@NotNull Location targetLocation, @NotNull Vector direction, double speed) {
        if (!isDirectional())
            throw new ParticlePropertyError(this.particleType.name() + " is not a directional particle type!");
        direction.normalize();
        display(targetLocation, direction.getX(), direction.getY(), direction.getZ(), speed, 0);
    }

    /**
     * Displays the particle effect moving in the specified direction.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param direction      the direction the particle effect should move in
     * @param speed          the speed value for the particle effect
     * @param players        the players to display the particle effect to
     */
    public void display(@NotNull Location targetLocation, @NotNull Vector direction, double speed, @NotNull Collection<? extends Player> players) {
        if (!isDirectional())
            throw new ParticlePropertyError(this.particleType.name() + " is not a directional particle type!");
        direction.normalize();
        display(targetLocation, direction.getX(), direction.getY(), direction.getZ(), speed, 0, players);
    }

    /**
     * Displays the particle effect moving in the specified direction.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param direction      the direction the particle effect should move in
     * @param speed          the speed value for the particle effect
     * @param player         the player to display the particle effect to
     */
    public void display(@NotNull Location targetLocation, @NotNull Vector direction, double speed, @NotNull Player player) {
        if (!isDirectional())
            throw new ParticlePropertyError(this.particleType.name() + " is not a directional particle type!");
        direction.normalize();
        display(targetLocation, direction.getX(), direction.getY(), direction.getZ(), speed, 0, player);
    }

    /**
     * Displays the particle effect with the specified colour.
     * <p>The extra parameter applies to {@link #REDSTONE} and {@link #NOTE}.</p>
     * <ul>
     *     <li>For {@link #REDSTONE} this value influences the size of the redstone cloud</li>
     *     <li>For {@link #NOTE} this value actually DEFINES the colour, since this particle only supports 24 predetermined
     *     colours. This should be a value from 1-24 to correspond with the predetermined colours. E.g. 6 is red</li>
     * </ul>
     * @param targetLocation the location where the particle effect should be displayed
     * @param colour         the colour of the particle effect
     * @param extra          the extra parameter for the particle effect, this is explained above
     */
    public void display (@NotNull Location targetLocation, @NotNull Color colour, float extra){
        if(!isColourable()) throw new ParticlePropertyError(this.particleType.name() + " is not a colourable particle type!");
        if(this.particleType == Particle.REDSTONE){
            targetLocation.getWorld().spawnParticle(Particle.REDSTONE, targetLocation, 1, new Particle.DustOptions(colour, extra));
        }
        else if(this.particleType == Particle.SPELL_MOB){
            display(targetLocation, colour.getRed()/255.0, colour.getGreen()/255.0, colour.getBlue()/255.0, 1, 0);
        }
        else if(this.particleType == Particle.NOTE) {
            display(targetLocation, (extra/24 <= 1 ? extra/24 : 1), 0, 0, 1, 0);
        }
    }

    /**
     * Displays the particle effect with the specified colour.
     * <p>The extra parameter applies to {@link #REDSTONE} and {@link #NOTE}.</p>
     * <ul>
     *     <li>For {@link #REDSTONE} this value influences the size of the redstone cloud</li>
     *     <li>For {@link #NOTE} this value actually DEFINES the colour, since this particle only supports 24 predetermined
     *     colours. This should be a value from 1-24 to correspond with the predetermined colours. E.g. 6 is red</li>
     * </ul>
     * @param targetLocation the location where the particle effect should be displayed
     * @param colour         the colour of the particle effect
     * @param extra          the extra parameter for the particle effect, this is explained above
     * @param players        the players to display the particle effect to
     */
    public void display (@NotNull Location targetLocation, @NotNull Color colour, float extra, @NotNull Collection<? extends Player> players){
        if(!isColourable()) throw new ParticlePropertyError(this.particleType.name() + " is not a colourable particle type!");
        if(this.particleType == Particle.REDSTONE){
            players.forEach(player -> {
                player.spawnParticle(Particle.REDSTONE, targetLocation, 1, new Particle.DustOptions(colour, extra));
            });
        }
        else if(this.particleType == Particle.SPELL_MOB){
            display(targetLocation, colour.getRed()/255.0, colour.getGreen()/255.0, colour.getBlue()/255.0, 1, 0, players);
        }
        else if(this.particleType == Particle.NOTE) {
            display(targetLocation, (extra/24 <= 1 ? extra/24 : 1), 0, 0, 1, 0, players);
        }
    }

    /**
     * Displays the particle effect with the specified colour.
     * <p>The extra parameter applies to {@link #REDSTONE} and {@link #NOTE}.</p>
     * <ul>
     *     <li>For {@link #REDSTONE} this value influences the size of the redstone cloud</li>
     *     <li>For {@link #NOTE} this value actually DEFINES the colour, since this particle only supports 24 predetermined
     *     colours. This should be a value from 1-24 to correspond with the predetermined colours. E.g. 6 is red</li>
     * </ul>
     * @param targetLocation the location where the particle effect should be displayed
     * @param colour         the colour of the particle effect
     * @param extra          the extra parameter for the particle effect, this is explained above
     * @param player         the player to display the particle effect to
     */
    public void display (@NotNull Location targetLocation, @NotNull Color colour, float extra, @NotNull Player player){
        if(!isColourable()) throw new ParticlePropertyError(this.particleType.name() + " is not a colourable particle type!");
        if(this.particleType == Particle.REDSTONE){
            display(targetLocation, colour, extra, ImmutableList.of(player));
        }
        else if(this.particleType == Particle.SPELL_MOB){
            display(targetLocation, colour.getRed()/255.0, colour.getGreen()/255.0, colour.getBlue()/255.0, 1, 0, player);
        }
        else if(this.particleType == Particle.NOTE) {
            display(targetLocation, (extra/24 <= 1 ? extra/24 : 1), 0, 0, 1, 0, player);
        }
    }

    /**
     * Displays the particle effect with the specified colour.
     * <p>
     *     Note: This won't work for {@link #NOTE} since that particle type's colour
     *     is based on the extra parameter.
     * </p>
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param colour         the colour of the particle effect
     *
     * @see #display(Location, Color, float)
     */
    public void display (@NotNull Location targetLocation, @NotNull Color colour){
        display(targetLocation, colour, 1);
    }

    /**
     * Displays the particle effect with the specified colour.
     * <p>
     *     Note: This won't work for {@link #NOTE} since that particle type's colour
     *     is based on the extra parameter.
     * </p>
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param colour         the colour of the particle effect
     * @param players        the players to display this particle effect to
     *
     * @see #display(Location, Color, float)
     */
    public void display (@NotNull Location targetLocation, @NotNull Color colour, @NotNull Collection<? extends Player> players){
        display(targetLocation, colour, 1, players);
    }

    /**
     * Displays the particle effect with the specified colour.
     * <p>
     *     Note: This won't work for {@link #NOTE} since that particle type's colour
     *     is based on the extra parameter.
     * </p>
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param colour         the colour of the particle effect
     * @param player         the player to display this particle effect to
     *
     * @see #display(Location, Color, float)
     */
    public void display (@NotNull Location targetLocation, @NotNull Color colour, @NotNull Player player){
        display(targetLocation, colour, 1, player);
    }

    /**
     * Displays the particle effect with the specified item data.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param xOffset        the x offset for the particle effect
     * @param yOffset        the y offset for the particle effect
     * @param zOffset        the z offset for the particle effect
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param item           the {@link ItemStack} this effect should be based on
     */
    public void display (@NotNull Location targetLocation, double xOffset, double yOffset, double zOffset, double speed, int count, ItemStack item){
        if(!isItemEffect()) throw new ParticlePropertyError(this.particleType.name() + " is not a particle effect type that requires item data!");
        targetLocation.getWorld().spawnParticle(this.getParticleType(), targetLocation, count, xOffset, yOffset, zOffset, speed, item);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param item           the {@link ItemStack} this effect should be based on
     */
    public void display (@NotNull Location targetLocation, double speed, int count, ItemStack item){
        display(targetLocation, 0, 0, 0, speed, count, item);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param count          the amount of particles to display
     * @param item           the {@link ItemStack} this effect should be based on
     */
    public void display (@NotNull Location targetLocation, int count, ItemStack item){
        display(targetLocation, 0, count, item);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param item           the {@link ItemStack} this effect should be based on
     */
    public void display (@NotNull Location targetLocation, ItemStack item){
        display(targetLocation, 1, item);
    }

    /**
     * Displays the particle effect with the specified item data.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param xOffset        the x offset for the particle effect
     * @param yOffset        the y offset for the particle effect
     * @param zOffset        the z offset for the particle effect
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param item           the {@link ItemStack} this effect should be based on
     * @param players        the players to display this effect to
     */
    public void display (@NotNull Location targetLocation, double xOffset, double yOffset, double zOffset, double speed, int count, ItemStack item, @NotNull Collection<? extends Player> players){
        if(!isItemEffect()) throw new ParticlePropertyError(this.particleType.name() + " is not a particle effect type that requires item data!");
        players.forEach(player -> {
            player.spawnParticle(this.getParticleType(), targetLocation, count, xOffset, yOffset, zOffset, speed, item);
        });
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param item           the {@link ItemStack} this effect should be based on
     * @param players        the players to display this effect to
     */
    public void display (@NotNull Location targetLocation, double speed, int count, ItemStack item, @NotNull Collection<? extends Player> players){
        display(targetLocation, 0, 0, 0, speed, count, item, players);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param count          the amount of particles to display
     * @param item           the {@link ItemStack} this effect should be based on
     * @param players        the players to display this effect to
     */
    public void display (@NotNull Location targetLocation, int count, ItemStack item, @NotNull Collection<? extends Player> players){
        display(targetLocation, 0, count, item, players);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param item           the {@link ItemStack} this effect should be based on
     * @param players        the players to display this effect to
     */
    public void display (@NotNull Location targetLocation, ItemStack item, @NotNull Collection<? extends Player> players){
        display(targetLocation, 1, item, players);
    }

    /**
     * Displays the particle effect with the specified item data.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param xOffset        the x offset for the particle effect
     * @param yOffset        the y offset for the particle effect
     * @param zOffset        the z offset for the particle effect
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param item           the {@link ItemStack} this effect should be based on
     * @param player         the player to display this effect to
     */
    public void display (@NotNull Location targetLocation, double xOffset, double yOffset, double zOffset, double speed, int count, ItemStack item, @NotNull Player player){
        if(!isItemEffect()) throw new ParticlePropertyError(this.particleType.name() + " is not a particle effect type that requires item data!");
        display(targetLocation, xOffset, yOffset, zOffset, speed, count, ImmutableList.of(player));
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param item           the {@link ItemStack} this effect should be based on
     * @param player         the player to display this effect to
     */
    public void display (@NotNull Location targetLocation, double speed, int count, ItemStack item, @NotNull Player player){
        display(targetLocation, 0, 0, 0, speed, count, item, player);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param count          the amount of particles to display
     * @param item           the {@link ItemStack} this effect should be based on
     * @param player         the player to display this effect to
     */
    public void display (@NotNull Location targetLocation, int count, ItemStack item, @NotNull Player player){
        display(targetLocation, 0, count, item, player);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param item           the {@link ItemStack} this effect should be based on
     * @param player         the player to display this effect to
     */
    public void display (@NotNull Location targetLocation, ItemStack item, @NotNull Player player){
        display(targetLocation, 1, item, player);
    }

    /**
     * Displays the particle effect moving in the specified direction.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param direction      the direction the particle effect should move in
     * @param speed          the speed value for the particle effect
     * @param itemStack      the {@link BlockData} this effect should be based on
     */
    public void display(@NotNull Location targetLocation, @NotNull Vector direction, double speed, ItemStack itemStack) {
        if (!isDirectional())
            throw new ParticlePropertyError(this.particleType.name() + " is not a directional particle type!");
        if(!isBlockEffect())
            throw new ParticlePropertyError(this.particleType.name() + " is not a particle effect that requires block data!");

        direction.normalize();
        display(targetLocation, direction.getX(), direction.getY(), direction.getZ(), speed, 0, itemStack);
    }

    /**
     * Displays the particle effect moving in the specified direction.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param direction      the direction the particle effect should move in
     * @param speed          the speed value for the particle effect
     * @param itemStack      the {@link ItemStack} this effect should be based on
     * @param players        the players to display the particle effect to
     */
    public void display(@NotNull Location targetLocation, @NotNull Vector direction, double speed, ItemStack itemStack, @NotNull Collection<? extends Player> players) {
        if (!isDirectional())
            throw new ParticlePropertyError(this.particleType.name() + " is not a directional particle type!");
        if(!isItemEffect())
            throw new ParticlePropertyError(this.particleType.name() + " is not a particle effect that requires item data!");

        direction.normalize();
        display(targetLocation, direction.getX(), direction.getY(), direction.getZ(), speed, 0, itemStack, players);
    }

    /**
     * Displays the particle effect moving in the specified direction.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param direction      the direction the particle effect should move in
     * @param speed          the speed value for the particle effect
     * @param itemStack      the {@link ItemStack} this effect should be based on
     * @param player         the player to display the particle effect to
     */
    public void display(@NotNull Location targetLocation, @NotNull Vector direction, double speed, ItemStack itemStack, @NotNull Player player) {
        if (!isDirectional())
            throw new ParticlePropertyError(this.particleType.name() + " is not a directional particle type!");

        direction.normalize();
        display(targetLocation, direction.getX(), direction.getY(), direction.getZ(), speed, 0, itemStack, ImmutableList.of(player));
    }

    /**
     * Displays the particle effect with the specified block data.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param xOffset        the x offset for the particle effect
     * @param yOffset        the y offset for the particle effect
     * @param zOffset        the z offset for the particle effect
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param blockData      the {@link BlockData} this effect should be based on
     */
    public void display (@NotNull Location targetLocation, double xOffset, double yOffset, double zOffset, double speed, int count, BlockData blockData){
        if(!isBlockEffect()) throw new ParticlePropertyError(this.particleType.name() + " is not a particle effect type that requires block data!");
        targetLocation.getWorld().spawnParticle(this.getParticleType(), targetLocation, count, xOffset, yOffset, zOffset, speed, blockData);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param blockData      the {@link BlockData} this effect should be based on
     */
    public void display (@NotNull Location targetLocation, double speed, int count, BlockData blockData){
        display(targetLocation, 0, 0, 0, speed, count, blockData);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param count          the amount of particles to display
     * @param blockData      the {@link BlockData} this effect should be based on
     */
    public void display (@NotNull Location targetLocation, int count, BlockData blockData){
        display(targetLocation, 0, count, blockData);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param blockData      the {@link BlockData} this effect should be based on
     */
    public void display (@NotNull Location targetLocation, BlockData blockData){
        display(targetLocation, 1, blockData);
    }

    /**
     * Displays the particle effect with the specified block data.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param xOffset        the x offset for the particle effect
     * @param yOffset        the y offset for the particle effect
     * @param zOffset        the z offset for the particle effect
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param blockData           the {@link ItemStack} this effect should be based on
     * @param players        the players to display this effect to
     */
    public void display (@NotNull Location targetLocation, double xOffset, double yOffset, double zOffset, double speed, int count, BlockData blockData, @NotNull Collection<? extends Player> players){
        if(!isBlockEffect()) throw new ParticlePropertyError(this.particleType.name() + " is not a particle effect type that requires block data!");
        players.forEach(player -> {
            player.spawnParticle(this.getParticleType(), targetLocation, count, xOffset, yOffset, zOffset, speed, blockData);
        });
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param blockData      the {@link BlockData} this effect should be based on
     * @param players        the players to display this effect to
     */
    public void display (@NotNull Location targetLocation, double speed, int count, BlockData blockData, @NotNull Collection<? extends Player> players){
        display(targetLocation, 0, 0, 0, speed, count, blockData, players);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param count          the amount of particles to display
     * @param blockData      the {@link BlockData} this effect should be based on
     * @param players        the players to display this effect to
     */
    public void display (@NotNull Location targetLocation, int count, BlockData blockData, @NotNull Collection<? extends Player> players){
        display(targetLocation, 0, count, blockData, players);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param blockData      the {@link BlockData} this effect should be based on
     * @param players        the players to display this effect to
     */
    public void display (@NotNull Location targetLocation, BlockData blockData, @NotNull Collection<? extends Player> players){
        display(targetLocation, 1, blockData, players);
    }

    /**
     * Displays the particle effect with the specified block data.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param xOffset        the x offset for the particle effect
     * @param yOffset        the y offset for the particle effect
     * @param zOffset        the z offset for the particle effect
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param blockData      the {@link BlockData} this effect should be based on
     * @param player         the player to display this effect to
     */
    public void display (@NotNull Location targetLocation, double xOffset, double yOffset, double zOffset, double speed, int count, BlockData blockData, @NotNull Player player){
        if(!isBlockEffect()) throw new ParticlePropertyError(this.particleType.name() + " is not a particle effect type that requires block data!");
        display(targetLocation, xOffset, yOffset, zOffset, speed, count, blockData, ImmutableList.of(player));
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param speed          the speed value for the particle effect
     * @param count          the amount of particles to display
     * @param blockData      the {@link BlockData} this effect should be based on
     * @param player         the player to display this effect to
     */
    public void display (@NotNull Location targetLocation, double speed, int count, BlockData blockData, @NotNull Player player){
        display(targetLocation, 0, 0, 0, speed, count, blockData, player);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param count          the amount of particles to display
     * @param blockData      the {@link BlockData} this effect should be based on
     * @param player         the player to display this effect to
     */
    public void display (@NotNull Location targetLocation, int count, BlockData blockData, @NotNull Player player){
        display(targetLocation, 0, count, blockData, player);
    }

    /**
     * Displays the particle effect.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param blockData      the {@link BlockData} this effect should be based on
     * @param player         the player to display this effect to
     */
    public void display (@NotNull Location targetLocation, BlockData blockData, @NotNull Player player){
        display(targetLocation, 1, blockData, player);
    }

    /**
     * Displays the particle effect moving in the specified direction.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param direction      the direction the particle effect should move in
     * @param speed          the speed value for the particle effect
     * @param blockData      the {@link BlockData} this effect should be based on
     */
    public void display(@NotNull Location targetLocation, @NotNull Vector direction, double speed, BlockData blockData) {
        if (!isDirectional())
            throw new ParticlePropertyError(this.particleType.name() + " is not a directional particle type!");
        if(!isBlockEffect())
            throw new ParticlePropertyError(this.particleType.name() + " is not a particle effect that requires block data!");

        direction.normalize();
        display(targetLocation, direction.getX(), direction.getY(), direction.getZ(), speed, 0, blockData);
    }

    /**
     * Displays the particle effect moving in the specified direction.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param direction      the direction the particle effect should move in
     * @param speed          the speed value for the particle effect
     * @param blockData      the {@link BlockData} this effect should be based on
     * @param players        the players to display the particle effect to
     */
    public void display(@NotNull Location targetLocation, @NotNull Vector direction, double speed, BlockData blockData, @NotNull Collection<? extends Player> players) {
        if (!isDirectional())
            throw new ParticlePropertyError(this.particleType.name() + " is not a directional particle type!");
        if(!isBlockEffect())
            throw new ParticlePropertyError(this.particleType.name() + " is not a particle effect that requires block data!");

        direction.normalize();
        display(targetLocation, direction.getX(), direction.getY(), direction.getZ(), speed, 0, blockData, players);

    }

    /**
     * Displays the particle effect moving in the specified direction.
     *
     * @param targetLocation the location where the particle effect should be displayed
     * @param direction      the direction the particle effect should move in
     * @param speed          the speed value for the particle effect
     * @param blockData      the {@link BlockData} this effect should be based on
     * @param player         the player to display the particle effect to
     */
    public void display(@NotNull Location targetLocation, @NotNull Vector direction, double speed, BlockData blockData, @NotNull Player player) {
        if (!isDirectional())
            throw new ParticlePropertyError(this.particleType.name() + " is not a directional particle type!");
        direction.normalize();
        display(targetLocation, direction.getX(), direction.getY(), direction.getZ(), speed, 0, blockData, ImmutableList.of(player));
    }


    private final Particle particleType;
    private final boolean colourable;
    private final boolean directional;
    private final boolean blockEffect;
    private final boolean itemEffect;


    ParticleEffect(Particle particleType, boolean colourable, boolean directional, boolean blockEffect, boolean itemEffect) {
        this.particleType = particleType;
        this.colourable = colourable;
        this.directional = directional;
        this.blockEffect = blockEffect;
        this.itemEffect = itemEffect;
    }

    public Particle getParticleType() {
        return particleType;
    }

    public boolean isColourable() {
        return colourable;
    }

    public boolean isDirectional() {
        return directional;
    }

    public boolean isBlockEffect() {
        return blockEffect;
    }

    public boolean isItemEffect() {
        return itemEffect;
    }

    public static class ParticlePropertyError extends Error {
        public ParticlePropertyError (String message){
            super(message);
        }
    }
}
