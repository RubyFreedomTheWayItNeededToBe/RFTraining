package com.freedomservers.RubyFreedom;

import com.freedomservers.RubyFreedom.Listeners.PlayerListener;
import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class RF_TrainingHandler {
    public static PlayerCommandPreprocessEvent checkevent;
    
    public RF_TrainingHandler() {
        PlayerListener.checkevent = checkevent;
    }
    
    
    public static boolean inTrainingModeSession() {
        if (TFM_ConfigEntry.TRAINING_SESSION.getBoolean()) {
            return true;
        } else {
            return false;
        }
    }

    public static void trainMe(Player player) {
        player.sendMessage(ChatColor.RED + "Welcome to RubyFreedom training! Type 'continue' in chat to continue.");
    }

    public static void continueMe(Player player) {
        player.sendMessage(ChatColor.RED + "Go over all the commands by going to http://mc13.fadehost.com:28966/help");
        player.sendMessage(ChatColor.RED + "When you are done, just type 'quiz me' in chat to start the quiz.");
    }

    public static void quiz(Player player) {
        String cmd = checkevent.getMessage();
        player.sendMessage("[QUIZ] Please respond to each question in chat.");
        player.sendMessage("[1] Type in the command that is used to ban someone.");
        if(!cmd.equalsIgnoreCase("/gtfo")) {
            checkevent.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Incorrect! Try again!");
        }
        else {
            checkevent.setCancelled(true);
            player.sendMessage(ChatColor.GREEN + "Correct! Moving on.");
        }
    }
}
