package me.aaron.timer.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Permissions {
    public static HashMap<Player, Rank>  ranks = new HashMap<>();

    public static void setRank(Player p, Rank rank) {
        ranks.put(p, (rank == null) ? Rank.GUEST : rank);
    }

    public static Rank getRank(Player p) {
        return ranks.get(p);
    }

    public static boolean hasPermission(Player p, Rank rank) {
        switch (rank) {
            case ADMIN:
                return getRank(p) == Rank.ADMIN;
            case OP:
                return getRank(p) == Rank.ADMIN || getRank(p) == Rank.OP;
            case USER:
                return getRank(p) == Rank.ADMIN || getRank(p) == Rank.OP || getRank(p) == Rank.USER;
            case GUEST:
                return true;
        }
        return false;
    }

    public static String getPrefix(Rank rank) {
        return rank.prefix;
    }

    public enum Rank {
        GUEST("§8[§7Spec§8] "),
        USER(""),
        OP(""),
        ADMIN("§8[§4Admin§8] ");

        private final String prefix;

        Rank(String prefix) {
            this.prefix = prefix;
        }
    }
}
