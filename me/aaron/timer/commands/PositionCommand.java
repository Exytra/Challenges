package me.aaron.timer.commands;

import me.aaron.timer.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.aaron.timer.pos.Position;

public class PositionCommand implements CommandExecutor {
    Position pos = new Position();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cKein Konsolenbefehl");
            return false;
        }
        Player p = (Player) sender;

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("share")) {
                pos.share(p);
            } /*else if (args[0].equalsIgnoreCase("list")) {

            }*/ else {
                pos.pos(args[0], p);
            }

        } else if (args.length == 0) {
            /*if (!pos.positions.toString().equalsIgnoreCase("")) {
                p.sendMessage(Main.getPrefix("Position", "Verfügbare Positionen: §e" + pos.positions));
            } else {
                p.sendMessage(Main.getPrefix("Position", "Es sind §ckeine Positionen §7gespeichert."));
                p.sendMessage(pos.positions + "");
            }*/
            return false;
        } else {
            p.sendMessage(Main.getPrefix("Position", "Dein Name darf §ckein Leerzeichen §7enthalten."));
        }

        return false;
    }
}
