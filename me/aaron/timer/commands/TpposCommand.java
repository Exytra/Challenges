package me.aaron.timer.commands;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Config;
import me.aaron.timer.utils.Settings;
import me.aaron.timer.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class TpposCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cKein Konsolenbefehl");
            return false;
        }
        Player p = (Player) sender;

        if (!p.hasPermission("challenges.tppos")) {
            p.sendMessage(Main.getPrefix("Position-Teleport", "Du hast §ckeine Berechtigung §7diesen Befehl auszuführen!"));
            return false;
        }

        if (args.length != 1) {
            p.sendMessage(Main.getPrefix("Position-Teleport", "§9/tppos <Positionsname>"));
            return false;
        }

        if (!Config.contains("position." + args[0])) {
            p.sendMessage(Main.getPrefix("Position-Teleport", "Diese Position §cgibt es nicht§7."));
            return false;
        }

        Inventory inv = Bukkit.createInventory(null, 27, "Zu §9" + args[0] + " §rteleportieren?");
        Utils.fillWithGlass(inv);
        inv.setItem(11, Settings.createItemStack(Material.LIME_STAINED_GLASS_PANE, "§aTeleportieren", "Du wirst zur Position §9" + args[0] + " §7teleportiert!"));
        inv.setItem(15, Settings.createItemStack(Material.RED_STAINED_GLASS_PANE, "§cNicht teleportieren", "Du wirst nicht zur Position §9" + args[0] + " §7teleportiert!"));
        p.openInventory(inv);
        return true;
    }
}
