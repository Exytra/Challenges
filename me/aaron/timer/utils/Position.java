package me.aaron.timer.utils;

import me.aaron.timer.Main;
import me.aaron.timer.tabCompletes.PositionTabCompleter;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;

public class Position {
    public static ArrayList<String> positionlist = new ArrayList<>();
    Config config = new Config();
    public void pos(String name, Player p) {
        if (!name.equalsIgnoreCase("list")) {
            if (Config.contains("position." + name)) {
                name = (String) config.get("position." + name + ".name");
                int x = (int) config.get("position." + name + ".x");
                int y = (int) config.get("position." + name + ".y");
                int z = (int) config.get("position." + name + ".z");
                String world = (String) config.get("position." + name + ".world");
                net.md_5.bungee.api.chat.TextComponent component = new TextComponent("§8[§6Position§8] §9" + config.get("position." + name + ".name") + " §8[§6" + x + "§8, §6" + y + "§8, §6" + z + "§8, §6" + world + "§8]");
                component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tppos " + name));
                component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Klicke hier um zur Position §9" + name + " §7teleportiert zu werden!").create()));
                p.spigot().sendMessage(component);
            } else {
                try {
                    int x = p.getLocation().getBlockX();
                    int y = p.getLocation().getBlockY();
                    int z = p.getLocation().getBlockZ();
                    String world = p.getWorld().getName();

                    switch (world) {
                        case "world":
                            world = "Overworld";
                            break;
                        case "world_nether":
                            world = "Nether";
                            break;
                        case "world_the_end":
                            world = "End";
                            break;
                    }

                    Config.set("position." + name + ".name", name);
                    Config.set("position." + name + ".x", x);
                    Config.set("position." + name + ".y", y);
                    Config.set("position." + name + ".z", z);
                    Config.set("position." + name + ".world", world);
                    positionlist.add(name);

                    String pos = ("§8[§6Position§8] §a" + config.get("position." + name + ".name") + "§7 von §a" + p.getName() + " §8[§6" + x + "§8, §6" + y + "§8, §6" + z + "§8, §6" + world + "§8]");
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
            pl.sendMessage("§8[§6Position§8] §a" + p.getName() + " §7befindet sich bei §8[§6" + p.getLocation().getBlockX() + "§8, §6" + p.getLocation().getBlockY() + "§8, §6" + p.getLocation().getBlockZ() + "§8, §6" + world + "§8]");
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
            PositionTabCompleter.positions.remove(name);
            p.sendMessage(Main.getPrefix("Position", "Die Position §c" + name + " §7wurde §cerfolgreich gelöscht."));
        } else {
            p.sendMessage(Main.getPrefix("Position", "Die Position §9" + name + " §7existiert nicht."));
        }
    }

    public void list(Player p) {
        positionlist.removeIf(str -> str.contains("share") || str.contains("list") || str.contains("remove"));
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
