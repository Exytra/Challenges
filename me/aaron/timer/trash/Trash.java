package me.aaron.timer.trash;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Trash {
    public static void inv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "Trash");
        inv.clear();
        p.openInventory(inv);
    }
}
