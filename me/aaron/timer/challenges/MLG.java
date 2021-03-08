package me.aaron.timer.challenges;

import me.aaron.timer.Main;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import me.aaron.timer.utils.Timer;
import me.aaron.timer.utils.Utils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class MLG {
    public static int minTime;
    public static int maxTime;
    public static int minHeight;
    public static int maxHeight;
    public static boolean isMLG = false;
    public static ArrayList<Player> deadPlayers = new ArrayList<>();
    public static ArrayList<Location> waterLocations = new ArrayList<>();
    private static int neededTime;
    private static int currentTime = 0;
    private static HashMap<Player, Location> locations = new HashMap<>();
    private static HashMap<Player, ItemStack[]> inventoryContents = new HashMap<>();

    public static void createMLGWorld() {
        WorldCreator wc = new WorldCreator("MLG-World");
        wc.environment(World.Environment.NORMAL);
        wc.type(WorldType.FLAT);
        wc.generateStructures(false);
        wc.createWorld();
        Bukkit.getWorld("MLG-World").setDifficulty(Difficulty.PEACEFUL);
        Bukkit.getWorld("MLG-World").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
    }

    public static void start() {
        neededTime = Utils.getRandomInt(minTime, maxTime);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(Main.class), () -> {
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                if (!isMLG && SettingsModes.challenge.get(SettingsItems.ItemType.WATER_MLG) == SettingsItems.ItemState.ENABLED) {
                    currentTime++;
                    if (neededTime == currentTime) {
                        isMLG = true;
                        int player = 0;
                        for (Player pl : Bukkit.getOnlinePlayers()) {
                            locations.put(pl, pl.getLocation());
                            inventoryContents.put(pl, pl.getInventory().getContents());
                            pl.getInventory().clear();
                            pl.teleport(new Location(Bukkit.getWorld("MLG-World"), player * 10, Utils.getRandomInt(minHeight, maxHeight) + 4, 0));
                            pl.getInventory().setItem(0, new ItemStack(Material.WATER_BUCKET));
                            pl.getInventory().setHeldItemSlot(0);
                            player++;
                        }
                        Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main.class), () -> {
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                pl.teleport(locations.get(pl));
                                pl.getInventory().setContents(inventoryContents.get(pl));
                                if (!deadPlayers.contains(pl)) {
                                    pl.sendMessage(Main.getPrefix("MLG", "Du hast den MLG §aerfolgreich absolviert§7!"));
                                } else {
                                    pl.sendMessage(Main.getPrefix("MLG", "Du hast den MLG §cnicht geschafft§7!"));
                                }
                            }
                            locations.clear();
                            inventoryContents.clear();
                            currentTime = 0;
                            isMLG = false;
                            neededTime = Utils.getRandomInt(minTime, maxTime);
                            deadPlayers.clear();
                            for (Location loc : waterLocations) {
                                loc.getBlock().setType(Material.AIR);
                            }
                            waterLocations.clear();
                        }, 60);
                    }
                }
            }
        }, 0, 20);
    }
}
