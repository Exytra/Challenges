package me.aaron.timer.pos;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.aaron.timer.TabCompletes.PositionTabCompleter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Position {
    public static ArrayList<String> positionlist = new ArrayList<>();
    PositionTabCompleter TabCompleter = new PositionTabCompleter();
    Config config = new Config();
    public StringBuilder positions = new StringBuilder();
    public void pos(String name, Player p) {
        if (!name.equalsIgnoreCase("list")) {
            if (config.contains("position." + name)) {
                name = (String) config.get("position." + name + ".name");
                int x = (int) config.get("position." + name + ".x");
                int y = (int) config.get("position." + name + ".y");
                int z = (int) config.get("position." + name + ".z");
                String world = (String) config.get("position." + name + ".world");
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
                    positionlist.add(name);

                    String pos = ("§8[§6Position§8] §a" + config.get("position." + name + ".name") + "§7 von §a" + p.getName() + " §8[§6" + x + ", " + y + ", " + z + ", " + world + "§8]");
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.sendMessage(pos);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            list(p);
        }
    }

    public void share(Player p) {
        String world = p.getWorld().getName();
        if (p.getWorld().getName().equalsIgnoreCase("world")) {
            world = "Overworld";
        } else if (p.getWorld().getName().equalsIgnoreCase("world_nether")) {
            world = "Nether";
        } else if (p.getWorld().getName().equalsIgnoreCase("world_the_end")) {
            world = "End";
        }
        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.sendMessage("§8[§6Position§8] §a" + p.getName() + " §7befindet sich bei §8[§6" + p.getLocation().getBlockX() + ", " + p.getLocation().getBlockY() + ", " + p.getLocation().getBlockZ() + ", " + world + "§8]");
        }
    }

    public void remove(String name, Player p) {
        if (Config.contains("position." + name)) {
            try {
                Config.set("position." + name, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            positionlist.remove(name);
            p.sendMessage(Main.getPrefix("Position", "Die Position §c" + name + " §7wurde §cerfolgreich gelöscht."));
        } else {
            p.sendMessage(Main.getPrefix("Position", "Die Position §9" + name + " §7existiert nicht."));
        }
    }

    public void list(Player p) {
        if (positionlist.size() != 0) {
            p.sendMessage(Main.getPrefix("Position", "Verfügbare Positionen: §e" + positionlist.toString().replace("[", "").replace("]", "")));
        } else {
            p.sendMessage(Main.getPrefix("Position", "§cKeine Positionen §7gespeichert."));
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
