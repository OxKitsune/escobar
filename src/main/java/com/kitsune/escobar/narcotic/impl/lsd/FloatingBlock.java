package com.kitsune.escobar.narcotic.impl.lsd;

import com.kitsune.escobar.Escobar;
import com.kitsune.escobar.util.Logger;
import com.kitsune.escobar.util.MathUtils;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_15_R1.EntityFallingBlock;
import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.IBlockData;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_15_R1.PacketPlayOutSpawnEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_15_R1.CraftParticle;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_15_R1.util.CraftMagicNumbers;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import java.lang.reflect.Field;

public class FloatingBlock {

    private final Location location;
    private final BlockData block;

    private final Player player;

    private EntityFallingBlock entityFallingBlock;

    public FloatingBlock (Location location, BlockData block, Player player){
        this.location = location;
        this.block = block;
        this.player = player;

    }


    /**
     * Creates the floating block for the player
     */
    public void createFloatingBlock () {
        IBlockData blockData = CraftMagicNumbers.getBlock(block.getMaterial()).getBlockData();
        entityFallingBlock = new EntityFallingBlock(EntityTypes.FALLING_BLOCK, ((CraftWorld)location.getWorld()).getHandle());

        entityFallingBlock.setPosition(location.getX(), location.getY(), location.getZ());


        entityFallingBlock.setNoGravity(true);

        try {
            Field blockField = entityFallingBlock.getClass().getDeclaredField("block");
            blockField.setAccessible(true);

            blockField.set(entityFallingBlock, blockData);


            PacketPlayOutSpawnEntity entityPacket = new PacketPlayOutSpawnEntity(entityFallingBlock, net.minecraft.server.v1_15_R1.Block.getCombinedId(blockData));
            PacketPlayOutEntityMetadata entityMetadataPacket = new PacketPlayOutEntityMetadata(entityFallingBlock.getId(), entityFallingBlock.getDataWatcher(), true);


            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(entityPacket);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(entityMetadataPacket);
        }
        catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the glow of the block.
     *
     * @param glow - the glow
     */
    public void setGlow (boolean glow) {
        entityFallingBlock.getBukkitEntity().setGlowing(glow);

        PacketPlayOutEntityMetadata entityMetadataPacket = new PacketPlayOutEntityMetadata(entityFallingBlock.getId(), entityFallingBlock.getDataWatcher(), true);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(entityMetadataPacket);
    }

    /**
     * Teleport the floating block.
     *
     * @param location - the target location
     */
    public void teleport (Location location){

        entityFallingBlock.setPosition(location.getX(), location.getY(), location.getZ());
        PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(entityFallingBlock);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

    }

    public void destroy() {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(entityFallingBlock.getId()));
    }
}
