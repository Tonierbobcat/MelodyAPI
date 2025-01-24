package com.loficostudios.melodyapi;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;

import java.util.logging.Level;

@Deprecated
@ApiStatus.ScheduledForRemoval(inVersion = "1.0")
public abstract class MelodyPlugin<T extends MelodyPlugin<T>> extends JavaPlugin {

//    @Getter
//    private final ConfigManager<T> configManager = new ConfigManager<>();

    public MelodyPlugin() {
    }

//    private static MelodyPlugin<?> instance;
//
//    @SuppressWarnings("unchecked")
//    public static <T extends MelodyPlugin<T>> T getInstance() {
//        return (T) instance;
//    }
//
//    /**
//     *
//     * @param plugin is set in plugin constructor
//     */
//    protected void setInstance(T plugin) {
//        instance = plugin;
//        MelodyAPI.addPlugin(plugin);
//        this.getServer().getLogger().log(Level.INFO, "[" + getName() + "] set instance");
//    }
//
//    @Override
//    public void onLoad() {
//        if (instance == null) {
//            this.getServer().getLogger().log(Level.SEVERE, "[" + getName() + "] instance is not set disabling...");
//            Bukkit.getPluginManager().disablePlugin(this);
//        }
//    }
}
