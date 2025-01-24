/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi.melodygui;

import com.loficostudios.melodyapi.MelodyAPI;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class GuiIcon {

    public enum IconType {
        MUTABLE,
        IMMUTABLE
    }

    private final MelodyAPI plugin = MelodyAPI.getInstance();

    @Getter
    private final ItemStack item;

    @Getter
    private final String id;

    //@Setter
    @Getter
    private Consumer<InventoryClickEvent> action;

    public GuiIcon(@NotNull Consumer<Player> onClick, @NotNull ItemStack item, @Nullable String id) {
        clearKeys(item, id);

        this.item = item;
        this.id = id;

        this.action = (clickEvent) -> onClick.accept((Player)clickEvent.getWhoClicked());
    }

    public GuiIcon(@NotNull ItemStack item, @Nullable String id) {
        if (id != null) clearKeys(item, id);

        this.item = item;

        this.id = id;
    }

    public boolean getClicked(String id, InventoryClickEvent event, List<InventoryAction> actions) {
        if (event.getCurrentItem() == null) return false;

        ItemStack clickedButton = event.getCurrentItem();

        if (clickedButton.getItemMeta() == null) return false;

        String clickedButtonID = clickedButton.getItemMeta().getPersistentDataContainer().get(getKey(plugin), PersistentDataType.STRING);


        for (InventoryAction action : actions) {
            if (!action.equals(event.getAction())) {
                return false;
            }
        }

        return id.equals(clickedButtonID);
    }

    public boolean getClicked(String id, InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return false;

        ItemStack clickedButton = event.getCurrentItem();

        if (clickedButton.getItemMeta() == null) return false;

        String clickedButtonID = clickedButton.getItemMeta().getPersistentDataContainer().get(getKey(plugin), PersistentDataType.STRING);

        return id.equals(clickedButtonID);
    }

    private void clearKeys(ItemStack item, String buttonId) {
        if (item.getItemMeta() != null) {
            ItemMeta itemMeta = item.getItemMeta();
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();

            for (NamespacedKey key : container.getKeys()) {
                container.remove(key);
            }

            itemMeta.getPersistentDataContainer().set(getKey(plugin),
                    PersistentDataType.STRING, buttonId);

            item.setItemMeta(itemMeta);
        }
    }

    private NamespacedKey getKey(Plugin plugin) {
        return new NamespacedKey(plugin, "gui");
    }
}