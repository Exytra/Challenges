package me.aaron.timer.TabCompletes;

import me.aaron.timer.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class PositionTabCompleter implements TabCompleter {
    //public ArrayList<String> savedposs = new ArrayList<>();
    List<String> positions = new ArrayList<>();


    Config config = new Config();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            addList("share");
            addList("list");
            addList("remove");

            return positions;
        }

        return null;
    }

    public void addList(String name) {
        positions.add(name);
    }
}
