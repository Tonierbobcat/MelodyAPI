/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi.melodygui;


import com.loficostudios.melodyapi.annotations.Property;
import com.loficostudios.melodyapi.melodygui.events.GuiIconClickEvent;
import com.loficostudios.melodyapi.melodygui.events.GuiOpenEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiManager implements Listener {

    @Property
    private static GuiManager instance;

    public static GuiManager instance() {
        return instance;
    }

    private final Map<UUID, MelodyGui> playerData = new HashMap<>();

    public GuiManager() {
        if (instance != null)
            throw new IllegalArgumentException("Instantiating GUIManager instance twice!");
        instance = this;
    }

    public MelodyGui getGui(@NotNull Player player) {
        return this.playerData.get(player.getUniqueId());
    }

    public void setGui(@NotNull Player player, @NotNull MelodyGui gui) {
        this.playerData.put(player.getUniqueId(), gui);
    }

    @EventHandler
    protected void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (!(e.getInventory().getHolder() instanceof MelodyGui)) return;

        e.setCancelled(true);

        MelodyGui gui = getGui(player);

        GuiIcon icon = gui.getIcon(e.getRawSlot());

        if (icon != null && icon.getAction() != null) {
            icon.getAction().accept(e);
            Bukkit.getPluginManager().callEvent(new GuiIconClickEvent(player, gui, icon));
        }
    }

    @EventHandler
    protected void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();

        if (!(e.getInventory().getHolder() instanceof MelodyGui)) return;

//        var e
//
//        Bukkit.getPluginManager().callEvent(new GuiIconClickEvent(player, gui, icon));

        playerData.remove(player.getUniqueId());
    }

    @EventHandler
    protected void onOpen(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();

        if (!(e.getInventory().getHolder() instanceof MelodyGui)) return;

//        var event = new GuiOpenEvent();
//
//        Bukkit.getPluginManager().callEvent();

//        playerData.remove(player.getUniqueId());
    }
}