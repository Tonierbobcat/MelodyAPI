package com.loficostudios.melodyapi.utils.item.interfaces;

import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.ApiStatus;

@Deprecated
@ApiStatus.ScheduledForRemoval(inVersion = "1.0")
@FunctionalInterface
public interface ItemMetaFunction {
    void apply(ItemMeta meta);
}
