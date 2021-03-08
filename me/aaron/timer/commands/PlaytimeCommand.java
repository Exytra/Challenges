package me.aaron.timer.commands;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Timer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaytimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix("Spielzeit", "Die gesamte Spielzeit ist " + Timer.ConvertTimerTime(Timer.totalPlaytime, "§6§l")));
            return true;
        }
        Player p = (Player) sender;
        p.sendMessage(Main.getPrefix("Spielzeit", "Die gesamte Spielzeit ist " + Timer.ConvertTimerTime(Timer.totalPlaytime, "§6§l") + "§7. Deine Persönliche Spielzeit ist " + Timer.ConvertTimerTime(Timer.playtime.get(p), "§6§l") + "§7."));
        return false;
    }
}
