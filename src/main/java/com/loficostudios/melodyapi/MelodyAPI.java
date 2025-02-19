/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi;

import com.loficostudios.melodyapi.gui.GuiManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class MelodyAPI extends JavaPlugin {

    public static MelodyAPI instance;

    private GuiManager guiManager;

    public MelodyAPI() {
        if (instance == null)
            instance = this;
    }

    public static MelodyAPI inst() {
        return instance;
    }

    @Override
    public void onEnable() {
        this.guiManager = new GuiManager();
        Bukkit.getPluginManager().registerEvents(
        guiManager,
        this);
    }

    @Override
    public void onDisable() {
    }

    public GuiManager getGuiManager() {
        return this.guiManager;
    }
}
