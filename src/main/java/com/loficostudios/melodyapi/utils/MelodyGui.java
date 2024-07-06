/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */

package com.loficostudios.melodyapi.utils;

import com.loficostudios.melodyapi.MelodyAPI;
import com.loficostudios.melodyapi.icon.GuiIcon;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class MelodyGui implements InventoryHolder {

    private final MelodyAPI plugin = MelodyAPI.getInstance();

    @Getter
    @Setter
    private Inventory gui;

    @Getter
    private int size;

    @Getter
    private String title; //Melody GUI

    @Getter
    private Map<Integer, GuiIcon> icons = new HashMap<>();

    public MelodyGui() {
        this.size = 9;
        this.title = "&#C608FBM&#C322FBe&#C03CFBl&#BE56FCo&#BB70FCd&#B88BFCy &#B3BFFDG&#B0D9FDU&#ADF3FDI &lThis is a bold message";

        create();
    }

    private void create() {
        this.gui = plugin.getServer().createInventory(this, this.size, SimpleColor.deserialize(this.title));
    }

    public final void fill(@NotNull GuiIcon icon, int start, int end, Boolean replaceExisting) {
        for(int i = start; i < end; ++i) {

            if (!replaceExisting && this.icons.containsKey(i)) {
                continue; // Skip this iteration if replaceExisting is false and key exists
            }

            this.icons.put(i, icon);
            this.gui.setItem(i, icon.getIcon());
        }
    }

    public void open(@NotNull Player player) {
        create();

        //create(this.size, this.title);
        MelodyAPI.getInstance().getGuiManager().setGui(player, this);
        this.icons.forEach((slot, icon) -> {
            this.gui.setItem(slot, icon.getIcon());
        });

        player.openInventory(this.gui);
    }

    public void close(@NotNull Player player) {
        player.closeInventory();
    }

    public void refreshGui(@NotNull Player player, @NotNull  MelodyGui gui) {
        this.gui.clear();
        //gui = new
        gui.open(player);

    }

    public void addIcon(@NotNull Integer slot, @NotNull GuiIcon icon) {
        this.icons.put(slot, icon);
        this.gui.setItem(slot, icon.getIcon());
    }

    public GuiIcon getIcon(@NotNull Integer slot) {
        return this.icons.get(slot);
    }

    protected void setTitle(@NotNull String title) {
        this.title = title;
    }

    protected void setSize(@NotNull Integer size) {
        if (size < 9) {
            this.size = 9;
        } else if (size < 18) {
            this.size = 18;
        } else if (size < 27) {
            this.size = 27;
        } else if (size < 36) {
            this.size = 36;
        } else if (size < 45) {
            this.size = 45;
        } else if (size < 54) {
            this.size = 54;
        } else {
            this.size = 54;
        }
    }

    public void setSlot(@NotNull Integer slotID, @NotNull ItemStack item) {
        this.gui.setItem(slotID, item);
    }
}



