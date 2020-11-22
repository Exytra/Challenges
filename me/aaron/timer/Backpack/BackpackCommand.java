package me.aaron.timer.Backpack;

import me.aaron.timer.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.IOException;

public class BackpackCommand implements CommandExecutor {
    private Inventory inventory = Bukkit.createInventory(null, 27, "Backpack");

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cKein Konsolenbefehl");
            return false;
        }
        Player p = (Player) sender;

        p.openInventory(inventory);

        return false;
    }
}
