package me.aaron.timer.challenges;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Timer;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import me.aaron.timer.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class ForceBlock {
    public static int ForceBlockScheduler;
    private Main plugin;
    private BossBar bossBar;
    private int neededtime;
    private int currenttime;
    private Material forcedBlock;
    private int lefttime;
    String BlockNameWithDash;
    String BlockName;

    public ForceBlock (Main plugin) {
        this.plugin = plugin;
    }

    public void start() {
        forcedBlock = null;
        neededtime = Utils.getRandomInt(180, 360);
        currenttime = 0;
        bossBar = Bukkit.createBossBar("Der Timer ist pausiert.", BarColor.WHITE, BarStyle.SOLID);
        bossBar.setVisible(true);
        for (Player pl : Bukkit.getOnlinePlayers()) {
            addPlayer(pl);
        }
        update();
    }

    public void update() {
        ForceBlockScheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(Main.class), () -> {
            if (SettingsModes.challenge.get(SettingsItems.ItemType.FORCEBLOCK) == SettingsItems.ItemState.ENABLED) {
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    addPlayer(pl);
                }
                if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                    if (!(currenttime >= neededtime)) {
                        if (forcedBlock == null) {
                            bossBar.setColor(BarColor.BLUE);
                            bossBar.setTitle("Warten auf neue §9Anweisung ...");
                            currenttime++;
                        } else {
                            lefttime = neededtime - currenttime;
                            bossBar.setProgress(Utils.getBossBarProgress(neededtime, currenttime, true));
                            bossBar.setTitle("§7Stehe in " + Timer.ConvertTimerTime(lefttime, "§9") + "§7 auf §a" + BlockName);
                            bossBar.setColor(BarColor.BLUE);
                            currenttime ++;
                        }
                    } else {
                        if (forcedBlock == null) {
                            forcedBlock = getRandomBlock();
                            neededtime = Utils.getRandomInt(180, 360);
                            currenttime = 0;
                            BlockNameWithDash = forcedBlock.name();
                            BlockName = BlockNameWithDash.replace("_", " ");
                            bossBar.setTitle("§7Stehe in " + Timer.ConvertTimerTime(neededtime - currenttime, "§9") + "§7 auf §a" + BlockName);
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                pl.sendMessage(Main.getPrefix("Force-Block", "§9Neue Anweisung: §7Stehe in " + Timer.ConvertTimerTime(neededtime - currenttime, "§6") + "§7 auf §a" + BlockName));
                            }
                        } else {
                            int checkedPlayers = 0;
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                if (!(pl.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == forcedBlock) && !(pl.getLocation().getBlock().getType() == forcedBlock)) {
                                    pl.setHealth(0);
                                    for (Player pls : Bukkit.getOnlinePlayers()) {
                                        pls.sendMessage("§7§m                                                 ");
                                        pls.sendMessage(" ");
                                        pls.sendMessage(Main.getPrefix("Force-Block", "Der Spieler §6§l" + pl.getName() + "§7 stand auf §a" + pl.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().name().replace("_", " ")));
                                        pls.sendMessage(" ");
                                        pls.sendMessage("§7§m                                                 ");
                                    }
                                } else {
                                    checkedPlayers ++;
                                }
                                if (checkedPlayers == Bukkit.getOnlinePlayers().size()) {
                                    for (Player pls : Bukkit.getOnlinePlayers()) {
                                        pls.sendMessage(" ");
                                        pls.sendMessage(Main.getPrefix("Force-Block", "§9Alle Spieler §7standen auf §a" + BlockName));
                                        pls.sendMessage(" ");
                                    }
                                }
                                forcedBlock = null;
                                neededtime = Utils.getRandomInt(180, 360);
                                currenttime = 0;
                                bossBar.setTitle("Warten auf neue §9Anweisung ...");
                                bossBar.setProgress(1);
                            }
                        }
                    }
                } else {
                    bossBar.setColor(BarColor.WHITE);
                    bossBar.setTitle("Der Timer ist pausiert.");
                    bossBar.setProgress(1);
                }
            } else {
                bossBar.setVisible(false);
            }
        }, 0, 20);
    }

    public Material getRandomBlock() {
        Material block = null;
        Random random = new Random();
        while (block == null) {
            block = Material.values()[random.nextInt(Material.values().length)];
            if (!(block.isSolid()) || !(block.isBlock())) {
                block = null;
            }
            if (block == Material.STRUCTURE_BLOCK || block == Material.BARRIER || block == Material.COMMAND_BLOCK_MINECART || block == Material.CHAIN_COMMAND_BLOCK || block == Material.REPEATING_COMMAND_BLOCK || block == Material.DRAGON_HEAD || block == Material.DRAGON_EGG || block == Material.ENCHANTING_TABLE || block.name().contains("MINECART") || block.name().contains("SLAB") || block.name().contains("EGG")) {
                block = null;
            }
        }
        return block;
    }

    public void addPlayer(Player p) {
        bossBar.addPlayer(p);
    }
}
