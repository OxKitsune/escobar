package com.kitsune.escobar;

import com.kitsune.escobar.narcotic.NarcoticManager;
import com.kitsune.escobar.narcotic.impl.Cocaine;
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
