/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @Link https://github.com/Tonierbobcat/MelodyAPI
 * @version 0.1.3
 */



package com.loficostudios.melodyapi.gui;

import com.loficostudios.melodyapi.MelodyAPI;
import com.loficostudios.melodyapi.gui.events.GuiCloseEvent;
import com.loficostudios.melodyapi.gui.events.GuiOpenEvent;
import com.loficostudios.melodyapi.gui.guiicon.GuiIcon;
import com.loficostudios.melodyapi.gui.interfaces.IGui;
import com.loficostudios.melodyapi.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class MelodyGui implements IGui {
    protected static final String DEFAULT_MENU_TITLE = "&#C608FBM&#C322FBe&#C03CFBl&#BE56FCo&#BB70FCd&#B88BFCy &#B3BFFDG&#B0D9FDU&#ADF3FDI";

    private Inventory inventory;
    private final Map<Integer, GuiIcon> displayedIcons = new HashMap<>();

    private String title;
    private final int size;

    public MelodyGui(int size, String title) {
        this.size = IGui.validateSize(size);
        this.title = title;
        this.inventory = Bukkit.createInventory(this,
                this.size,
                !StringUtils.isNullOrEmpty(title) ? title : DEFAULT_MENU_TITLE);
    }

    public MelodyGui(int size) {
        this(size, null);
    }

    public void fill(@NotNull GuiIcon icon, int start, int end, Boolean replaceExisting) {
        for(int i = start; i < end; ++i) {

            if (!replaceExisting && this.displayedIcons.containsKey(i)) {
                continue; // Skip this iteration if replaceExisting is false and key exists
            }

            setSlot(i, icon);
        }
    }

    protected void clear() {
        if (displayedIcons.isEmpty()) return;

        displayedIcons.forEach((index, icon) -> {
            this.getInventory().setItem(index, new ItemStack(Material.AIR));
        });

        displayedIcons.clear();
    }

    public boolean open(@NotNull Player player) {
        var event = new GuiOpenEvent(player, this);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled())
            return false;
        MelodyAPI.inst().getGuiManager().setGui(player, this);
        player.openInventory(this.inventory);
        return true;
    }

    public boolean close(@NotNull Player player) {
        var event = new GuiCloseEvent(player, this);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled())
            return false;
        player.closeInventory();
        return true;
    }

    public void refresh() {
    }

    public Collection<GuiIcon> getDisplayedIcons() {
        return new ArrayList<>(this.displayedIcons.values());
    }

    public GuiIcon getIcon(int slot) {
        return this.displayedIcons.get(slot);
    }

    @Override
    public boolean setSlot(int slot, ItemStack item) {
        setSlot(slot, new GuiIcon(item, item.getType().name().toLowerCase() + System.currentTimeMillis()));
        return true;
    }

    @Override
    public boolean setSlot(int slot, @NotNull GuiIcon icon) {
        this.displayedIcons.put(slot, icon);
        this.inventory.setItem(slot, icon.getItem());
        return true;
    }

    public int getSize() {
        return this.size;
    }

    public @NotNull String getTitle() {
        return this.title;
    }

    public void setTitle(@NotNull String text) {
        var contents = inventory.getContents();
        var viewers = new ArrayList<>(inventory.getViewers());
        for (HumanEntity viewer : viewers) {
            viewer.closeInventory();
        }
        inventory = Bukkit.createInventory(this, size, text);
        for (HumanEntity viewer : viewers) {
            viewer.openInventory(this.inventory);
        }
        inventory.setContents(contents);
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }


}



