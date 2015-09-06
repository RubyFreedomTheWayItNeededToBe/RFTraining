package com.freedomservers.RubyFreedom;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;

public class RF_TrainingHandler {
    public static boolean inTrainingModeSession() {
        if (TFM_ConfigEntry.TRAINING_SESSION.getBoolean()) {
            return true;
        }
        else {
            return false;
        }
    }
}
