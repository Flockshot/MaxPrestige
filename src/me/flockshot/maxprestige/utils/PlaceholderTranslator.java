package me.flockshot.maxprestige.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.flockshot.maxprestige.MaxPrestigePlugin;

public class PlaceholderTranslator extends PlaceholderExpansion
{
    private MaxPrestigePlugin plugin;
    
    public PlaceholderTranslator(MaxPrestigePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "Flockshot";
    }

    @Override
    public String getIdentifier() {
        return "maxprestige";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier)
    {
        if(player == null){
            return "";
        }

        if(identifier.equals("formatted_money"))
        {
            double balance = plugin.econ.getBalance(player);

            if(balance>=1000)
                return format(BigDecimal.valueOf(balance).toBigInteger());
            else
                return balance+"";
        }

        return null;
    }

    
    private static final NavigableMap<BigInteger, String> suffixes = new TreeMap<> ();
    static {
      suffixes.put(new BigInteger("1000"), "k");
      suffixes.put(new BigInteger("1000000"), "m");
      suffixes.put(new BigInteger("1000000000"), "B");
      suffixes.put(new BigInteger("1000000000000"), "T");
      suffixes.put(new BigInteger("1000000000000000"), "Q");
      suffixes.put(new BigInteger("1000000000000000000"), "Qt");
      suffixes.put(new BigInteger("1000000000000000000000"), "Sx");
      suffixes.put(new BigInteger("1000000000000000000000000"), "Sp");
      suffixes.put(new BigInteger("1000000000000000000000000000"), "Oc");
    }

    public static String format(BigInteger value)
    {
        if(value.equals(new BigInteger("999999999999999900000000000"))) return "1Oc";    
        
        Entry<BigInteger, String> e = suffixes.floorEntry(value);
        BigInteger divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value.divide((divideBy.divide(new BigInteger("10")))).longValue();
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }
    
    
    
    
}
