package com.loficostudios.melodyapi.utils.interfaces;

import org.bukkit.inventory.meta.ItemMeta;

@FunctionalInterface
public interface ItemMetaFunction {
    void apply(ItemMeta meta);
}
