/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi.file.impl;

import com.loficostudios.melodyapi.file.IFlatFile;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class YamlFile implements IFlatFile {

    private FileConfiguration config;

    public FileConfiguration getConfig() {
        return config;
    }

    public File getFile() {
        return file;
    }

    private File file;

    private final String fileName;

    public YamlFile(final String fileName, final JavaPlugin plugin) {
        this.fileName = fileName;
        create(plugin);
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public boolean getBoolean(@NotNull String path) {
        return config.getBoolean(path);
    }

    @Override
    public int getInt(@NotNull String path) {
        return config.getInt(path);
    }

    @Override
    public double getDouble(@NotNull String path) {
        return config.getDouble(path);
    }

    @Override
    public long getLong(@NotNull String path) {
        return config.getLong(path);
    }

    @Override
    public void set(@NotNull String path, @NotNull Object newValue) {
        config.set(path, newValue);
    }

    @Override
    public void create(final JavaPlugin plugin) {
        this.file = new File(plugin.getDataFolder(), fileName);
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            if(plugin.getResource(fileName) == null) {
                try {
                    file.createNewFile();
                } catch (IOException ignore) {
                }
            }else {
                plugin.saveResource(fileName, false);
            }
        }

        this.config = YamlConfiguration.loadConfiguration(file);

    }

    @Override
    public void save() {
        try {
            this.config.save(file);
            Bukkit.getLogger().info("[MelodyAPI] File saved successfully");
        } catch (IOException ignore) {
            Bukkit.getLogger().log(Level.SEVERE, "[MelodyAPI] Could not save file!");
        }
    }

    @Override
    public Object getBase() {
        return config;
    }
}
