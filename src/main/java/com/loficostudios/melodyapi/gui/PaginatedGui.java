package com.loficostudios.melodyapi.gui;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.List;

public abstract class PaginatedGui extends MelodyGui {

    public static <T> Collection<T> paginate(List<T> objects, int page, int itemsPerPage) {
        int start = page * itemsPerPage;
        int end = Math.min(start + itemsPerPage, objects.size());

        if (start >= objects.size()) {
            start = objects.size();
        }

        return objects.stream().toList().subList(start, end);
    }

    public PaginatedGui(JavaPlugin plugin, int size) {
        super(plugin, size);
    }

    public PaginatedGui(JavaPlugin plugin, int size, String title) {
        super(plugin, size, title);
    }

    abstract void nextPage(Player player);
    abstract void previousPage(Player player);
}
