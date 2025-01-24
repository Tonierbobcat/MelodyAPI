/**
 * @Author Tonierbobcat
 * @Github https://github.com/Tonierbobcat
 * @version MelodyApi
 */



package com.loficostudios.melodyapi.melodygui;

import com.loficostudios.melodyapi.utils.StringUtils;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class MelodyGui implements InventoryHolder {
    protected static final String DEFAULT_MENU_TITLE = "&#C608FBM&#C322FBe&#C03CFBl&#BE56FCo&#BB70FCd&#B88BFCy &#B3BFFDG&#B0D9FDU&#ADF3FDI";

    private final JavaPlugin plugin;

    @NotNull
    @Getter
    private final Inventory gui;

    @Getter
    private Map<Integer, GuiIcon> icons = new HashMap<>();

    @Getter
    private final int size;

    @Getter
    private String title;

    public MelodyGui(JavaPlugin plugin, int size, String title) {
        this.plugin = plugin;
        this.size = validateSize(size);
        this.title = title;
        this.gui = plugin.getServer().createInventory(this,
                this.size,
                !StringUtils.isNullOrEmpty(title) ? title : DEFAULT_MENU_TITLE);
    }

    public MelodyGui(JavaPlugin plugin, int size) {
        this(plugin, size, null);
    }

    public final void fill(@NotNull GuiIcon icon, int start, int end, Boolean replaceExisting) {
        for(int i = start; i < end; ++i) {

            if (!replaceExisting && this.icons.containsKey(i)) {
                continue; // Skip this iteration if replaceExisting is false and key exists
            }

            setSlot(i, icon);
        }
    }

    public void open(@NotNull Player player) {
        GuiManager.instance().setGui(player, this);
        player.openInventory(this.gui);
    }

    protected void clear() {
        if (icons.isEmpty()) return;

        icons.forEach((index, icon) -> {
            this.getInventory().setItem(index, new ItemStack(Material.AIR));
        });

        icons.clear();
    }

    public void close(@NotNull Player player) {
        player.closeInventory();
    }

    public void setSlot(@NotNull Integer slot, @NotNull GuiIcon icon) {
        this.icons.put(slot, icon);
        this.gui.setItem(slot, icon.getItem());
    }

    public GuiIcon getIcon(@NotNull Integer slot) {
        return this.icons.get(slot);
    }

    private int validateSize(int size) {
        final Set<Integer> allowedInventorySize = new HashSet<>(Set.of(
                9, 18, 27, 36, 45, 54
        ));

        return allowedInventorySize.stream()
                .filter(allowedSize -> allowedSize >= size)
                .min(Integer::compareTo)
                .orElse(9);
    }
}



