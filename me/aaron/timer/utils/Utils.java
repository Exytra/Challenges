package me.aaron.timer.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.aaron.timer.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
}
