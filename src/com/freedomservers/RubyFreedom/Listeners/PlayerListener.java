package com.freedomservers.RubyFreedom.Listeners;

import static com.freedomservers.RubyFreedom.RFTraining.plugin;
import com.freedomservers.RubyFreedom.RF_TrainingHandler;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TFM_UuidManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener {
    
    public boolean cont = false;
    public static PlayerCommandPreprocessEvent checkevent;
    
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        
        //TODO: Make a wizard for training
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String cmd = event.getMessage();
        PlayerListener.checkevent = event;
        
        if(cmd.equals("/trainme")) {
            if (!TFM_AdminList.isSuperAdmin(player)) {
                return;
            }
            else {
                event.setCancelled(true);
                RF_TrainingHandler.trainMe(player);
            }
        }
    }
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if(RF_TrainingHandler.inTrainingModeSession()) {
            if (message.equalsIgnoreCase("continue")) {
                event.setCancelled(true);
                RF_TrainingHandler.continueMe(player);
                return;
            }
            if (message.equalsIgnoreCase("quiz me")) {
                event.setCancelled(true);
                RF_TrainingHandler.quiz(player);
                return;
            }
        }
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        String message = event.getLeaveMessage();        
        String ip = event.getPlayer().getAddress().getHostName().trim();

        if (message.contains("GTFO")) {
            if (RF_TrainingHandler.inTrainingModeSession()) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        TFM_BanManager.unbanUuid(TFM_UuidManager.getUniqueId(player));
                        TFM_BanManager.unbanIp(ip);
                        TFM_BanManager.unbanIp(TFM_Util.getFuzzyIp(ip));
                    }
                }.runTaskLater(plugin, 2L * 20L);
            }
        }
    }
    
    public boolean isContinuing(boolean cont) {
        return this.cont;
    }
    
    public void setContinuing(boolean cont) {
        this.cont = cont;
    }
}
