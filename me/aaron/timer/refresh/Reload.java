package me.aaron.timer.refresh;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Config;
import me.aaron.timer.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && Permissions.hasPermission((Player) sender, Permissions.Rank.ADMIN)) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                pl.sendMessage("§8[§6Reload§8] §cEs könnte nun laggen.");
            }
            if (Config.saveConfig()) {
                Bukkit.getServer().reload();
            }
        } else if (!(sender instanceof Player)) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                pl.sendMessage("§8[§6Reload§8] §cEs könnte nun laggen.");
            }
            if (Config.saveConfig()) {
                Bukkit.getServer().reload();
            }
        }
        else {
            sender.sendMessage(Main.getPrefix("Reload", "Du hast hierfür §ckeine Berechtigung"));
        }
        return false;
    }
}
