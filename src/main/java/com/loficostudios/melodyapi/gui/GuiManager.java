/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @Link https://github.com/Tonierbobcat/MelodyAPI
 * @version 0.1.3
 */

package com.loficostudios.melodyapi.gui;


import com.loficostudios.melodyapi.gui.events.GuiIconClickEvent;
import com.loficostudios.melodyapi.gui.interfaces.IGui;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiManager implements Listener {

    private static GuiManager instance;

    @Getter
    private final JavaPlugin plugin;

    public static GuiManager instance() {
        return instance;
    }

    private final Map<UUID, IGui> openedMenus = new HashMap<>();

    public GuiManager(JavaPlugin plugin) {
        if (instance != null)
            throw new IllegalArgumentException("Instantiating GUIManager instance twice!");
        instance = this;
        this.plugin = plugin;
    }

    public IGui getGui(@NotNull Player player) {
        return this.openedMenus.get(player.getUniqueId());
    }

    public void setGui(@NotNull Player player, @Nullable IGui gui) {
        UUID uuid = player.getUniqueId();
        if (gui == null && openedMenus.containsKey(uuid))
            this.openedMenus.remove(uuid);
        this.openedMenus.put(uuid, gui);
    }

    @EventHandler
    protected void onClick(InventoryClickEvent e) {
        if (e.isCancelled()) {
            return;
        }

        Player player = (Player) e.getWhoClicked();

        if (!(e.getInventory().getHolder() instanceof IGui)) return;

        IGui gui = getGui(player);

        var slot = e.getRawSlot();

        if (gui instanceof MutableGui) {
//            Debug.log("gui is mutable");
            if (((MutableGui) gui).getMutableSlots().contains(slot)) {
//                Debug.log("clicked on item is mutable");
                return;
            }
            e.setCancelled(true);
//            Debug.log("clicked on item is not mutable");

            var icon = gui.getIcon(slot);

            if (icon != null && icon.getAction() != null) {
                var event = new GuiIconClickEvent(player, gui, icon);
                Bukkit.getPluginManager().callEvent(event);
                if (!event.isCancelled()) {
                    icon.getAction().accept(e);
                }
            }

            return;
        }

        e.setCancelled(true);
        
        var icon = gui.getIcon(slot);

        if (icon != null && icon.getAction() != null) {
            var event = new GuiIconClickEvent(player, gui, icon);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                icon.getAction().accept(e);
            }
        }
    }
}