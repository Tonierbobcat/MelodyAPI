/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi.listeners;

import com.loficostudios.melodyapi.MelodyAPI;
import com.loficostudios.melodyapi.icon.GuiIcon;
import com.loficostudios.melodyapi.utils.MelodyGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class GuiListener implements Listener {

    @EventHandler
    protected void inventoryHandler(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!(event.getInventory().getHolder() instanceof MelodyGui)) return;

        event.setCancelled(true);

        MelodyGui gui = MelodyAPI.getInstance().getGuiManager().getGui(player);

        GuiIcon icon = gui.getIcon(event.getRawSlot());

        if (icon != null) {
            if (icon.getAction() != null) {
                icon.getAction().accept(event);
            }
        }

    }
}
