package com.kitsune.escobar.narcotic;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class Narcotic {

    /* Id of this narcotic */
    private String id;

    /**
     * Construct a new narcotic.
     *
     * @param id - the id of this narcotic
     */
    public Narcotic(String id) {
        this.id = id;
    }

    /**
     * Get the display name of this narcotic.
     *
     * @return - the display name
     */
    public abstract String getDisplayName ();

    /**
     * Get the description of this narctotic.
     *
     * @return - the description
     */
    public abstract List<String> getDescription ();

    /**
     * Get the material that this narcotic uses as icon.
     *
     * @return - the icon material
     */
    public abstract Material getMaterial ();

    /**
     * Apply this narcotics effect to the specified player.
     *
     * @param player - the player to apply the effect to
     */
    public abstract void applyEffect (Player player);

    /**
     * Called whenever the narcotic is registered.
     */
    public abstract void onRegister ();

    /**
     * Get an itemstack that represents this narcotic.
     *
     * @param amount - the amount of items in the itemstack
     * @return
     */
    public ItemStack getItemStack (int amount){

        if(amount > 64) throw new IllegalArgumentException("ItemStack size cannot be larger than 64!");

        ItemStack itemStack = new ItemStack(getMaterial(), amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(getDisplayName());
        itemMeta.setLore(getDescription());

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    /**
     * Get the id of this narcotic.
     *
     * @return - the id of this narcotic.
     */
    public String getId() {
        return id;
    }
}
