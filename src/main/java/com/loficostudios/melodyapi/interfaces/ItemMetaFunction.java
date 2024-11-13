package com.loficostudios.melodyapi.interfaces;

import org.bukkit.inventory.meta.ItemMeta;

@FunctionalInterface
public interface ItemMetaFunction {
    void apply(ItemMeta meta);
}
