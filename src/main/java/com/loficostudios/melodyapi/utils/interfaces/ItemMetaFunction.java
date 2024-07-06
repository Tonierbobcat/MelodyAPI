/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi.utils.interfaces;

import org.bukkit.inventory.meta.ItemMeta;

@FunctionalInterface
public interface ItemMetaFunction {
    void apply(ItemMeta meta);
}
