/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi.utils;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.settings.Settings;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.ApiStatus;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Objects;

@Deprecated
@ApiStatus.ScheduledForRemoval(inVersion = "1.0")
public class SimpleDocument {

    public static YamlDocument create(Plugin plugin, String fileName) {

        InputStream inputStream;
        YamlDocument configFile = null;

        try {

            inputStream = Objects.requireNonNull(plugin.getResource(fileName), "Couldn't load " + fileName);

            File dataFolder = plugin.getDataFolder();

            File configFileInDataFolder = new File(dataFolder, fileName);
            configFile = YamlDocument.create(
                    configFileInDataFolder,
                    inputStream,
                    GeneralSettings.builder().setUseDefaults(false).build(),
                    LoaderSettings.DEFAULT, DumperSettings.DEFAULT, UpdaterSettings.DEFAULT
            );


        } catch (IOException | NullPointerException exception) {
            plugin.getLogger().severe(exception.getMessage());
        }

        return configFile;
    }

    public static YamlDocument create(Plugin plugin, String fileName, String fileLocation) {

        InputStream inputStream;
        YamlDocument configFile = null;

        try {

            inputStream = Objects.requireNonNull(plugin.getResource(fileLocation + "/" + fileName), "Couldn't load " + fileName);

            File dataFolder = plugin.getDataFolder();


            File configFileInDataFolder = new File(dataFolder, fileName);
            configFile = YamlDocument.create(
                    configFileInDataFolder,
                    inputStream,
                    GeneralSettings.builder().setUseDefaults(false).build(),
                    LoaderSettings.DEFAULT, DumperSettings.DEFAULT, UpdaterSettings.DEFAULT
            );

        } catch (IOException | NullPointerException exception) {
            plugin.getLogger().severe(exception.getMessage());
        }

        return configFile;
    }

    public static YamlDocument create(Plugin plugin, String fileName, String fileLocation, String targetFolder) {

        InputStream inputStream;
        YamlDocument configFile = null;

        try {

            inputStream = Objects.requireNonNull(plugin.getResource(fileLocation + "/" + fileName), "Couldn't load " + fileName);

            File dataFolder = plugin.getDataFolder();

            File targetDir = new File(dataFolder, targetFolder);
            if (!targetDir.exists()) {
                if (!targetDir.mkdirs()) {
                    throw new IOException("Failed to create directory: " + targetDir.getPath());
                }
            }
            File configFileInTarget = new File(targetDir, fileName);
            configFile = YamlDocument.create(
                    configFileInTarget,
                    inputStream,
                    GeneralSettings.builder().setUseDefaults(false).build(),
                    LoaderSettings.DEFAULT, DumperSettings.DEFAULT, UpdaterSettings.DEFAULT
            );

        } catch (IOException | NullPointerException exception) {
            plugin.getLogger().severe(exception.getMessage());
        }
        return configFile;
    }

}
