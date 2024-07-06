/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi.utils;

import com.loficostudios.melodyapi.MelodyAPI;
import org.bukkit.Material;

public class Common {
    public static String formatMaterialName(String materialName) {

        //String materialName = material.name();

        // Split the material name by underscores
        String[] words = materialName.split("_");

        // Capitalize each word
        for (int i = 0; i < words.length; i++) {
            words[i] = capitalize(words[i]);
        }

        // Join the words with a space
        return String.join(" ", words);
    }

    public static void broadcast(String message) {
        MelodyAPI.getInstance().getServer().broadcastMessage(message);
    }

    private static String capitalize(String word) {
        // Capitalize the first letter of the word
        if (!word.isEmpty()) {
            return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
        } else {
            return word; // Return empty or single-character strings as-is
        }
    }
}
