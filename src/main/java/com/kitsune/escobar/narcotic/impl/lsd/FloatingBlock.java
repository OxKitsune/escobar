package com.kitsune.escobar.narcotic.impl.lsd;

import com.kitsune.escobar.Escobar;
import com.kitsune.escobar.util.Logger;
import com.kitsune.escobar.util.MathUtils;
import net.minecraft.server.v1_15_R1.EntityFallingBlock;
import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.IBlockData;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_15_R1.PacketPlayOutSpawnEntity;
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


    public void startFloating () {

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



            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(entityPacket);
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(entityMetadataPacket);


            new BukkitRunnable() {
                int count = 0;

                @Override
                public void run() {

                    entityFallingBlock.getBukkitEntity().teleport(location.add(0, 0.25f, 0));
                    PacketPlayOutEntityTeleport packetTeleport = new PacketPlayOutEntityTeleport(entityFallingBlock);
                    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packetTeleport);

                    if(count == 4){

                        new BukkitRunnable() {

                            int count = 0;

                            @Override
                            public void run() {

                                entityFallingBlock.getBukkitEntity().teleport(location.add(MathUtils.randomDouble(-2.5d, 2.5d), 0.25f, MathUtils.randomDouble(-2.5d, 2.5d)));
                                PacketPlayOutEntityTeleport packetTeleport = new PacketPlayOutEntityTeleport(entityFallingBlock);
                                ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packetTeleport);

                                if(count >= 20){

                                    ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(entityFallingBlock.getId()));

                                    this.cancel();
                                }

                                count++;
                            }
                        }.runTaskTimer(Escobar.getInstance(), MathUtils.randomInt(1, 10), 5);

                        this.cancel();
                    }

                    count++;
                }
            }.runTaskTimer(Escobar.getInstance(), 0, 1);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }



    }

}
