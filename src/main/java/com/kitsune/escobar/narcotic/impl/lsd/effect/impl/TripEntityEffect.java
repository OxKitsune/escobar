package com.kitsune.escobar.narcotic.impl.lsd.effect.impl;

import com.kitsune.escobar.Escobar;
import com.kitsune.escobar.narcotic.impl.lsd.TripManager;
import com.kitsune.escobar.narcotic.impl.lsd.effect.TripEffect;
import com.kitsune.escobar.util.MathUtils;
import net.minecraft.server.v1_15_R1.EntitySkeleton;
import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.EntityZombie;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_15_R1.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_15_R1.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_15_R1.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TripEntityEffect extends TripEffect {

    public TripEntityEffect() {
        super("trip_entity");
    }

    @Override
    public void applyEffect(Player player) {

        for(int i = 0; i < 2 * TripManager.getInstance().getTripIntensity(player); i++){

            if(MathUtils.randomInt( 0, 100) < 50){
                spawnZombie(player);
            }
            else {
                spawnSkeleton(player);
            }

        }
    }

    private void spawnZombie (Player player){
        Location targetLocation =  player.getLocation().clone().add(MathUtils.randomDouble(-5, 5), MathUtils.randomDouble(-1, 6), MathUtils.randomDouble(-5, 5));

        EntityZombie entityZombie = new EntityZombie(((CraftWorld)targetLocation.getWorld()).getHandle());
        entityZombie.setPosition(targetLocation.getX(), targetLocation.getY(), targetLocation.getZ());

        entityZombie.setBaby(MathUtils.randomInt(0, 100) < 20);
        entityZombie.setHeadRotation(MathUtils.randomFloat(0, 180));

        PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving(entityZombie);

        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(spawnPacket);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityMetadata(entityZombie.getId(), entityZombie.getDataWatcher(), true));

        new BukkitRunnable(){

            int count = 0;

            @Override
            public void run() {

                double dx = entityZombie.getPositionVector().getX() - player.getEyeLocation().getX();
                double dy = entityZombie.getPositionVector().getY() - player.getEyeLocation().getY();
                double dz = entityZombie.getPositionVector().getZ() - player.getEyeLocation().getZ();

                double r = Math.sqrt(dx * dx + dy * dy + dz * dz);

                float yaw = (float) ((-Math.atan2(dx, dz)) / Math.PI * 180.0f);
                if(yaw < 0){
                    yaw = 360;
                }

                float pitch = (float) ((-Math.asin(dy/r)) / Math.PI * 180.0f);


                entityZombie.setHeadRotation(yaw);
                entityZombie.setPositionRotation(entityZombie.getPositionVector().getX(), entityZombie.getPositionVector().getY(), entityZombie.getPositionVector().getZ(), pitch, yaw);

                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityTeleport(entityZombie));

                if(count >= 20){
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(entityZombie.getId()));
                    this.cancel();
                }

                count++;

            }
        }.runTaskTimer(Escobar.getInstance(), 2, 4);
    }

    private void spawnSkeleton (Player player){
        Location targetLocation =  player.getLocation().clone().add(MathUtils.randomDouble(-5, 5), MathUtils.randomDouble(-1, 6), MathUtils.randomDouble(-5, 5));

        EntitySkeleton entitySkeleton = new EntitySkeleton(EntityTypes.SKELETON, ((CraftWorld)targetLocation.getWorld()).getHandle());
        entitySkeleton.setPosition(targetLocation.getX(), targetLocation.getY(), targetLocation.getZ());

        entitySkeleton.setHeadRotation(MathUtils.randomFloat(0, 180));

        PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving(entitySkeleton);

        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(spawnPacket);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityMetadata(entitySkeleton.getId(), entitySkeleton.getDataWatcher(), true));

        new BukkitRunnable(){

            int count = 0;

            @Override
            public void run() {

                double dx = entitySkeleton.getPositionVector().getX() - player.getEyeLocation().getX();
                double dy = entitySkeleton.getPositionVector().getY() - player.getEyeLocation().getY();
                double dz = entitySkeleton.getPositionVector().getZ() - player.getEyeLocation().getZ();

                double r = Math.sqrt(dx * dx + dy * dy + dz * dz);

                float yaw = (float) ((-Math.atan2(dx, dz)) / Math.PI * 180.0f);
                if(yaw < 0){
                    yaw = 360;
                }

                float pitch = (float) ((-Math.asin(dy/r)) / Math.PI * 180.0f);


                entitySkeleton.setHeadRotation(yaw);
                entitySkeleton.setPositionRotation(entitySkeleton.getPositionVector().getX(), entitySkeleton.getPositionVector().getY(), entitySkeleton.getPositionVector().getZ(), pitch, yaw);

                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityTeleport(entitySkeleton));

                if(count >= 20){
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(entitySkeleton.getId()));
                    this.cancel();
                }

                count++;

            }
        }.runTaskTimer(Escobar.getInstance(), 2, 4);
    }
}
