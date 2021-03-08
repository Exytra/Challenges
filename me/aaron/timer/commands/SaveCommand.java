package me.aaron.timer.commands;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SaveCommand implements CommandExecutor {
    Config config = new Config();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("challenges.saveconfig")) {
            Config.saveConfig();
            sender.sendMessage(Main.getPrefix("Config", "Die Config wurde erfolgreich gespeichert."));
        } else {
            sender.sendMessage(Main.getPrefix("Save Config", "Du hast hierfür §ckeine Berechtigung"));
        }
        return false;
    }
}
