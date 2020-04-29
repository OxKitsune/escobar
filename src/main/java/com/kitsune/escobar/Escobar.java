package com.kitsune.escobar;

import com.kitsune.escobar.command.HatCommand;
import com.kitsune.escobar.narcotic.NarcoticManager;
import com.kitsune.escobar.narcotic.impl.Cocaine;
import com.kitsune.escobar.narcotic.impl.MDMA;
import com.kitsune.escobar.narcotic.impl.XTC;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class Escobar extends JavaPlugin {

    /* Plugin instance */
    private static Escobar instance;

    private NarcoticManager narcoticManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        this.narcoticManager = new NarcoticManager();

        // Register narcotics
        narcoticManager.registerNarcotic(new Cocaine());
        narcoticManager.registerNarcotic(new MDMA());

        // Register different xtc types
        narcoticManager.registerNarcotic(new XTC("honey_xtc", 1.5d, Material.HONEYCOMB, ChatColor.YELLOW + "Honey flavour"));
        narcoticManager.registerNarcotic(new XTC("apple_xtc", 2.5d, Material.APPLE, ChatColor.RED + "Apple flavour"));
        narcoticManager.registerNarcotic(new XTC("berry_xtc", 1, Material.SWEET_BERRIES, ChatColor.RED + "Berry flavour"));

        getCommand("hat").setExecutor(new HatCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Get the instance of this plugin.
     *
     * @return - the instance of this plugin
     */
    public static Escobar getInstance() {
        return instance;
    }

    public NarcoticManager getNarcoticManager() {
        return narcoticManager;
    }
}
