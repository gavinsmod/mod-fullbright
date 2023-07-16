package com.peasenet.mod;

import com.peasenet.config.FullbrightConfig;
import com.peasenet.main.Settings;
import com.peasenet.mods.render.ModFullBright;
import com.peasenet.main.GavinsMod;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FullBrightMod implements ModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("gavinsmod");

    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        LOGGER.info("Initializing GEM FullBright Mod...");
        Settings.addConfig("fullbright", new FullbrightConfig());
        GavinsMod.addMod(new ModFullBright());
        LOGGER.info("GEM FullBright Mod Initialized!");
    }
}
