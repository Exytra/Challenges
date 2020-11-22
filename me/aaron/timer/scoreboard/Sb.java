package me.aaron.timer.scoreboard;

import me.aaron.timer.Main;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

public class Sb extends JavaPlugin {
    public static void createScoreboard(Player p) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
            @Override
            public void run() {
                p.setPlayerListHeader("§8§m                        §8[§9SERVER§8]§m                        §7\n \nWillkommen §9" + p.getName() + " \n §7Spieler Online: §9" + Bukkit.getOnlinePlayers().size() + "\n");
                if (Bukkit.getServer().hasWhitelist()) {
                    p.setPlayerListFooter("\n §7§oDieses Netzwerk ist privat! \n §c§oKein Fremder kann diesen Server betreten!");
                } else {
                    p.setPlayerListFooter("\n §7§oDieses Netzwerk ist öffentlich! \n §a§oJeder kann diesen Server betreten!");
                }
                if (SettingsModes.scoreboard.get(SettingsItems.ItemType.TABHP) == SettingsItems.ItemState.ENABLED) {
                    try {
                        if (Bukkit.getOnlinePlayers().size() != 0) {
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                Scoreboard board = pl.getScoreboard();
                                Objective objective = board.getObjective("showhealth");
                                if (objective == null) {
                                    String dname = ChatColor.RED + "\u2665";
                                    objective = board.registerNewObjective("showhealth", "health", dname, RenderType.HEARTS);
                                    objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        if (Bukkit.getOnlinePlayers().size() != 0) {
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                Scoreboard board = pl.getScoreboard();
                                Objective objective = board.getObjective("showhealth");
                                objective.unregister();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 20);
    }
}
