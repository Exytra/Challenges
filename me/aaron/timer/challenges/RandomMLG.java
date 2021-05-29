package me.aaron.timer.challenges;

import jdk.nashorn.internal.ir.Block;
import me.aaron.timer.Main;
import me.aaron.timer.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomMLG {
    public static int minTime;
    public static int maxTime;
    public static int minHeight;
    public static int maxHeight;
    private static boolean isMLG = false;
    public static ArrayList<Player> deadPlayers = new ArrayList<>();
    public static ArrayList<Location> blockLocations = new ArrayList<>();
    private static int neededTime;
    private static int currentTime = 0;
    private static HashMap<Player, Location> locations = new HashMap<>();
    private static HashMap<Player, ItemStack[]> inventoryContents = new HashMap<>();
    private static HashMap<Player, GameMode> prevGameMode = new HashMap<>();

    public static void start() {
        neededTime = Utils.getRandomInt(minTime, maxTime);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> {
                if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                    if (!isMLG && SettingsModes.challenge.get(SettingsItems.ItemType.RANDOM_MLG) == SettingsItems.ItemState.ENABLED) {
                        currentTime ++;
                        if (neededTime <= currentTime) {
                            ArrayList<Player> allPlayers = new ArrayList<>();
                            allPlayers.addAll(Bukkit.getOnlinePlayers());
                            Random random = new Random();
                            Player p = allPlayers.get(random.nextInt(Bukkit.getOnlinePlayers().size()));
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                locations.put(pl, pl.getLocation());
                                inventoryContents.put(pl, pl.getInventory().getContents());
                                prevGameMode.put(pl, pl.getGameMode());
                            }
                            isMLG = true;
                            Location loc = new Location(Bukkit.getWorld("MLG-World"), 0, Utils.getRandomInt(minHeight, maxHeight) + 4, 0);
                            p.getInventory().clear();
                            p.teleport(loc);
                            p.getInventory().setItem(0, new ItemStack(getMLGMaterial(MLG.values()[Utils.getRandomInt(0, 3)])));
                            p.getInventory().setHeldItemSlot(0);
                            for (Player pl : Bukkit.getOnlinePlayers()) {
                                if (pl != p) {
                                    locations.put(pl, pl.getLocation());
                                    inventoryContents.put(pl, pl.getInventory().getContents());
                                    pl.getInventory().clear();
                                    pl.teleport(loc);
                                    pl.setGameMode(GameMode.SPECTATOR);
                                }
                            }
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                                if (!deadPlayers.contains(p)) {
                                    for (Player pl : Bukkit.getOnlinePlayers()) {
                                        pl.sendMessage(Main.getPrefix("Random-MLG", "Der MLG wurde §aerfolgreich absolviert."));
                                        pl.teleport(locations.get(pl));
                                        pl.getInventory().setContents(inventoryContents.get(pl));
                                        pl.setGameMode(prevGameMode.get(pl));
                                    }
                                } else {
                                    for (Player pl : Bukkit.getOnlinePlayers()) {
                                        pl.sendMessage(Main.getPrefix("Random-MLG", "Der MLG wurde §cnicht erfolgreich §7absolviert."));
                                        pl.getInventory().clear();
                                        if (pl != p) {
                                            pl.teleport(locations.get(pl));
                                            pl.getInventory().setContents(inventoryContents.get(pl));
                                            pl.setGameMode(prevGameMode.get(pl));
                                        }
                                    }
                                }
                                locations.clear();
                                inventoryContents.clear();
                                prevGameMode.clear();
                                currentTime = 0;
                                isMLG = false;
                                neededTime = Utils.getRandomInt(minTime, maxTime);
                                deadPlayers.clear();
                                for (Location blockLoc : blockLocations) {
                                    blockLoc.getBlock().setType(Material.AIR);
                                }
                                for (Entity entity : Bukkit.getWorld("MLG-World").getEntities()) {
                                    entity.remove();
                                }
                                blockLocations.clear();
                            }, 60);
                        }
                    }
                }
            }, 0, 20);
    }

    private static MLG getMLG() {
        Random random = new Random();
        return MLG.values()[random.nextInt(MLG.values().length)];
    }

    public static Material getMLGMaterial(MLG mlg) {
        return mlg.material;
    }

    public enum MLG {
        WATER(Material.WATER_BUCKET),
        SLIME(Material.SLIME_BLOCK),
        BOAT(Material.OAK_BOAT),
        COBWEB(Material.COBWEB);

        private final Material material;

        MLG(Material material) {
            this.material = material;
        }
    }
}
