package me.aaron.timer.pos;

import me.aaron.timer.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.aaron.timer.TabCompletes.PositionTabCompleter;

import java.io.IOException;
import java.util.ArrayList;

public class Position {
    PositionTabCompleter TabCompleter = new PositionTabCompleter();
    Config config = new Config();
    public StringBuilder positions = new StringBuilder();
    public void pos(String name, Player p) {
        if (config.contains("position." + name)) {
            name = (String) config.get("position." + name + ".name");
            int x = (int) config.get("position." + name + ".x");
            int y = (int) config.get("position." + name + ".y");
            int z = (int) config.get("position." + name + ".z");
            String world = (String) config.get("position." + name + ".world");

            /*if (positions.toString().equalsIgnoreCase("")) {
                positions.append(name);
                p.sendMessage(positions + "");
            } else {
                positions.append(", " + name);
                p.sendMessage(positions + "");
            }*/

            String pos = ("§8[§6Position§8] §9" + config.get("position." + name + ".name") + " §8[§6" + x + ", " + y + ", " + z + ", " + world + "§8]");
            p.sendMessage(pos);
        } else {
            try {
                int x = p.getLocation().getBlockX();
                int y = p.getLocation().getBlockY();
                int z = p.getLocation().getBlockZ();
                String world = p.getWorld().getName();

                if (p.getWorld().getName().equalsIgnoreCase("world")) {
                    world = "Overworld";
                } else if (p.getWorld().getName().equalsIgnoreCase("world_nether")) {
                    world = "Nether";
                } else if (p.getWorld().getName().equalsIgnoreCase("world_the_end")) {
                    world = "End";
                }

                config.set("position." + name + ".name", name);
                config.set("position." + name + ".x", x);
                config.set("position." + name + ".y", y);
                config.set("position." + name + ".z", z);
                config.set("position." + name + ".world", world);
                TabCompleter.addList(name);

                String pos = ("§8[§6Position§8] §a" + config.get("position." + name + ".name") + "§7 von §a" + p.getName() + " §8[§6" + x + ", " + y + ", " + z + ", " + world + "§8]");
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.sendMessage(pos);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void share(Player p) {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.sendMessage("§8[§6Position§8] §a" + p.getName() + " §7befindet sich bei §8[§6" + p.getLocation().getBlockX() + ", " + p.getLocation().getBlockY() + ", " + p.getLocation().getBlockZ() + ", " + p.getWorld().getName() + "§8]");
        }
    }

    public void reset() {
        try {
            config.delete("position");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
