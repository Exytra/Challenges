package me.aaron.timer.commands;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
                    Inventory raw = Bukkit.createInventory(null, 54, "InvSee: §c§l" + p.getName());
                    for (int i = 0; i < 18; i++) {
                        raw.setItem(i, Settings.createItemStack(Material.GRAY_STAINED_GLASS_PANE, " ", " "));
                    }
                    raw.setItem(1, p.getInventory().getHelmet() == null ? Settings.createItemStack(Material.BARRIER, "§cHelm", "Dieser Spieler trägt keinen Helm") : p.getInventory().getHelmet());
                    raw.setItem(2, p.getInventory().getChestplate() == null ? Settings.createItemStack(Material.BARRIER, "§cChestplate", "Dieser Spieler trägt keine Chestplate") : p.getInventory().getChestplate());
                    raw.setItem(3, p.getInventory().getLeggings() == null ? Settings.createItemStack(Material.BARRIER, "§cHose", "Dieser Spieler trägt keine Hose") : p.getInventory().getLeggings());
                    raw.setItem(4, p.getInventory().getBoots() == null ? Settings.createItemStack(Material.BARRIER, "§cBoots", "Dieser Spieler trägt keine Boots") : p.getInventory().getBoots());
                    raw.setItem(6, p.getInventory().getItemInOffHand().getType() == Material.AIR ? Settings.createItemStack(Material.BARRIER, "§cOffhand", "Dieser Spieler hat nichts in seiner Offhand") : p.getInventory().getItemInOffHand());

                    for (int i = 0; i < 9; i++) {
                        raw.setItem(i + 45, p.getInventory().getContents()[i]);
                    }
                    for (int i = 9; i < 36; i++) {
                        raw.setItem(i + 9, p.getInventory().getContents()[i]);
                    }
                    s.openInventory(raw);
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
