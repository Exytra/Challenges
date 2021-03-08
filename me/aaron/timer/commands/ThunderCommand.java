package me.aaron.timer.commands;

import me.aaron.timer.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.aaron.timer.utils.Utils.sendWrongArgs;

public class ThunderCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cKein Konsolenbefehl");
            return false;
        }
        Player p = (Player) sender;
        if (p.hasPermission("challenges.weather")) {
            if (args.length == 0) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather thunder");
            } else {
                sendWrongArgs(p);
            }
        } else {
            p.sendMessage(Main.getPrefix("Wetter", "Du hast hierfür §ckeine Berechtigung"));
        }

        return false;
    }
}
