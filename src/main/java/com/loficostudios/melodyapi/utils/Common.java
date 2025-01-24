/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi.utils;

import com.loficostudios.melodyapi.MelodyAPI;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class Common {
    public static String formatMaterial(@NotNull Material material) {
        String rawName = material.toString();

        String[] words = rawName.split("_");

        for (int i = 0; i < words.length; i++) {
            String raw = words[i];

            if (!raw.isEmpty()) {
                char firstLetter = raw.charAt(0);
                words[i] =  Character.toUpperCase(firstLetter) + raw.substring(1).toLowerCase();
            }
        }

        return String.join(" ", words);
    }


    public static void broadcast(String message) {
        MelodyAPI.getInstance().getServer().broadcastMessage(message);
    }
}
