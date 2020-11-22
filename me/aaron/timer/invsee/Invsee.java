package me.aaron.timer.invsee;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class Invsee {
    public void invsee(Player p, Player s) {
        Inventory inv = p.getInventory();
        s.openInventory(inv);
    }
}
