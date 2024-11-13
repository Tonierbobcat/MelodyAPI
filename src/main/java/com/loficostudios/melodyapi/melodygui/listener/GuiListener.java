/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi.melodygui.listener;

import com.loficostudios.melodyapi.melodygui.GuiIcon;
import com.loficostudios.melodyapi.melodygui.GuiManager;
import com.loficostudios.melodyapi.utils.MelodyGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;


public class GuiListener implements Listener {


    private final GuiManager guiManager;

    public GuiListener(GuiManager guiManager) {
        this.guiManager = guiManager;
    }

    @EventHandler
    protected void inventoryHandler(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!(event.getInventory().getHolder() instanceof MelodyGui)) return;

        event.setCancelled(true);

        MelodyGui gui = guiManager.getGui(player);

        GuiIcon icon = gui.getIcon(event.getRawSlot());

        if (icon != null) {
            if (icon.getAction() != null) {
                icon.getAction().accept(event);
            }
        }

    }
}
