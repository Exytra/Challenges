package me.aaron.timer.challenges;

import me.aaron.timer.Main;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import me.aaron.timer.utils.Timer;
import me.aaron.timer.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ForceHeight {
    public static int ForceHeightScheduler;
    private BossBar bossBar;
    private int neededtime;
    private int currenttime;
    public static int forcedheight;
    private int lefttime;

    public void start() {
        forcedheight = 0;
        neededtime = Utils.getRandomInt(180, 520);
        currenttime = 0;
        bossBar = Bukkit.createBossBar("Der Timer ist pausiert.", BarColor.WHITE, BarStyle.SOLID);
        bossBar.setVisible(true);
        for (Player pl : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(pl);
        }
        update();
    }

    public void update() {
        ForceHeightScheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(Main.class), () -> {
            if (SettingsModes.challenge.get(SettingsItems.ItemType.FORCE_HEIGHT) == SettingsItems.ItemState.ENABLED) {
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    bossBar.addPlayer(pl);
                }
                if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                    if (!(currenttime >= neededtime)) {
                        if (forcedheight == 0) {
                            bossBar.setColor(BarColor.BLUE);
                            bossBar.setTitle("Warten auf neue §9Höhe§7 ...");
                            bossBar.setProgress(1);
                        } else {
                            bossBar.setProgress(Utils.getBossBarProgress(neededtime, currenttime, true));
                            bossBar.setColor(BarColor.BLUE);
                            bossBar.setTitle("§7Höhe: §9" + forcedheight + " §8| §7Zeit: " + Timer.ConvertTimerTime((neededtime - currenttime), "§9"));
                        }
                        currenttime++;
                    } else {
                        if (forcedheight == 0) {
                            forcedheight = Utils.getRandomInt(1, 256);
                            neededtime = Utils.getRandomInt(45, 300);
                            currenttime = 0;
                            bossBar.setTitle("§7Höhe: §9" + forcedheight + " §8| §7Zeit: " + Timer.ConvertTimerTime((neededtime - currenttime), "§9"));
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                pl.sendMessage(Main.getPrefix("Force Height", "§9Neue Anweisung: §7Stehe in " + Timer.ConvertTimerTime(neededtime, "§9") + " §7auf Höhe §9" + forcedheight));
                            }
                        } else {
                            int checkedplayers = 0;
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                if (pl.getLocation().getBlockY() != forcedheight) {
                                    pl.setHealth(0);
                                    for (Player pl2 : Bukkit.getOnlinePlayers()) {
                                        pl2.sendMessage(Main.getPrefix("Force Height", "§cDer Spieler §9" + pl.getName() + " §7befand sich §cnicht auf der Höhe §9" + forcedheight + "§7."));
                                    }
                                } else {
                                    checkedplayers++;
                                }
                            }
                            if (checkedplayers == Bukkit.getOnlinePlayers().size()) {
                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                    pl.sendMessage(Main.getPrefix("Force Height", "§aAlle Spieler §7befanden sich §aauf der Höhe §9" + forcedheight));
                                }
                            }
                            neededtime = Utils.getRandomInt(180, 520);
                            currenttime = 0;
                            forcedheight = 0;
                        }
                    }
                } else {
                    bossBar.setColor(BarColor.WHITE);
                    bossBar.setTitle("Der Timer ist pausiert.");
                    bossBar.setProgress(1);
                }
            } else {
                bossBar.removeAll();
                bossBar.setVisible(false);
                Bukkit.getScheduler().cancelTask(ForceHeightScheduler);
            }
        }, 0, 20);
    }
}
