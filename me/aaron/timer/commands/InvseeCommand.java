package me.aaron.timer.commands;

import me.aaron.timer.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InvseeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cKein Konsolenbefehl");
            return false;
        }
        Player s = (Player) sender;
        if (s.hasPermission("challenges.invsee")) {
            if (args.length == 1) {
                try {
                    Player p = Bukkit.getPlayerExact(args[0]);
                    Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER, "InvSee: §c§l" + p.getName());
                    for (int i = 0; i < p.getInventory().getSize(); i ++) {
                        inv.setItem(i, p.getInventory().getItem(i));
                    }
                    s.openInventory(inv);
                } catch (Exception e) {
                    s.sendMessage(Main.getPrefix("InvSee", "Der Spieler §9" + args[0] + " §7konnte §cnicht gefunden §7werden."));
                }
            } else {
                s.sendMessage(Main.getPrefix("InvSee", "/invsee §9<Player Name>"));
            }
        } else {
            s.sendMessage(Main.getPrefix("InvSee", "Du hast hierfür §ckeine Berechtigung."));
        }


        return false;
    }
}
