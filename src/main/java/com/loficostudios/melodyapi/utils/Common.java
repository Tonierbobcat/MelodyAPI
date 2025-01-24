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



    public static void broadcast(String message) {
        MelodyAPI.getInstance().getServer().broadcastMessage(message);
    }
}
