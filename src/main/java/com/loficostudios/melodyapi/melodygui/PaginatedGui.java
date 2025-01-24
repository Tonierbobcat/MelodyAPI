package com.loficostudios.melodyapi.melodygui;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class PaginatedGui extends MelodyGui {
    public PaginatedGui(JavaPlugin plugin, int size) {
        super(plugin, size);
    }

    public PaginatedGui(JavaPlugin plugin, int size, String title) {
        super(plugin, size, title);
    }
}
