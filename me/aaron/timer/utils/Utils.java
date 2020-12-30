package me.aaron.timer.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.aaron.timer.Main;
import net.dv8tion.jda.api.JDA;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class Utils {
    public static Inventory fillWithGlass(Inventory inv) {
        ItemStack itemStack = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(" ");
        itemStack.setItemMeta(itemMeta);
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, itemStack);
        }
        return inv;
    }

    public static void sendChange(String Title, String Subtitle) {
        if (SettingsModes.settings.get(SettingsItems.ItemType.SENDTITLE) == SettingsItems.ItemState.ENABLED) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                pl.sendTitle(Title, Subtitle, 5, 40, 5);
            }
        }
    }

    public static void SendToServer(Player p, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        p.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
    }

    public static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static void sendWrongArgs(Player p) {
        p.sendMessage("§8[§cError§8] §cDie von dir eingegebenen Argumente sind falsch.");
    }

    public static double getBossBarProgress(int max, int current, boolean reverse) {
        int left = max - current;
        double bossbarfactor = 1.0 / max;
        if (reverse) {
            return left * bossbarfactor;
        } else {
            return current * bossbarfactor;
        }
    }

    public static ItemStack getHead(String id) {
        net.minecraft.server.v1_16_R3.ItemStack item = CraftItemStack.asNMSCopy(new ItemStack(Material.PLAYER_HEAD, 1));
        NBTTagCompound tag;
        if (item.hasTag()) {
            tag = item.getTag();
        } else {
            tag = new NBTTagCompound();
        }
        NBTTagCompound skullOwner = new NBTTagCompound();
        NBTTagCompound properties = new NBTTagCompound();
        NBTTagList textures = new NBTTagList();
        NBTTagCompound texture = new NBTTagCompound();

        texture.setString("Value", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTliZjMyOTJlMTI2YTEwNWI1NGViYTcxM2FhMWIxNTJkNTQxYTFkODkzODgyOWM1NjM2NGQxNzhlZDIyYmYifX19");
        textures.add(texture);
        properties.set("textures", textures);
        skullOwner.set("Properties", properties);
        tag.set("SkullOwner", skullOwner);

        item.setTag(tag);
        return CraftItemStack.asBukkitCopy(item);
    }

    public static String firstLatterCapitalized(String string) {
        StringBuilder msg = new StringBuilder();
        boolean nextUpperCase = true;
        for (int i = 0; i < string.length(); i ++) {
            String current = string.substring(i, i + 1);
            if (nextUpperCase) {
                msg.append(current.toUpperCase());
                nextUpperCase = false;
            } else {
                msg.append(current);
            }
            if (current.equals(" ")) {
                nextUpperCase = true;
            }
        }
        return msg.toString();
    }

    public static int TimeToTicks(int hours, int minutes, int seconds) {
        int time = hours * 3600;
        time += minutes * 60;
        time += seconds;

        return time * 20;
    }

    public static Date convertLocalDateToDate(LocalDateTime date) {
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date calculateUnban(long timeInSeconds) {
        LocalDateTime dateTime = LocalDateTime.now().plusSeconds(timeInSeconds);
        return convertLocalDateToDate(dateTime);
    }

    public static void setNewRankPrefix(Player p, Permissions.Rank rank) {
        p.setDisplayName(Permissions.getPrefix(rank) + "§f" + p.getName());
        p.setCustomName(Permissions.getPrefix(rank) + "§f" + p.getName());
        p.setCustomNameVisible(true);
        p.setPlayerListName(Permissions.getPrefix(rank) + "§f" + p.getName());
    }

    public static Material randomItem() {
        Material item = null;
        Random random = new Random();
        while (item == null) {
            item = Material.values()[random.nextInt(Material.values().length)];
            if (item == Material.STRUCTURE_BLOCK || item == Material.BARRIER || item == Material.COMMAND_BLOCK_MINECART || item == Material.CHAIN_COMMAND_BLOCK || item == Material.REPEATING_COMMAND_BLOCK || item == Material.END_GATEWAY || item == Material.BEDROCK || item == Material.MOVING_PISTON || item == Material.KELP_PLANT || item == Material.AIR || item.getKey().getKey().contains("spawn") || item.getKey().getKey().contains("potted") || item.getKey().getKey().contains("wall")) {
                item = null;
            }
        }
        return item;
    }
}
