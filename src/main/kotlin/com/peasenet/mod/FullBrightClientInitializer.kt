package com.peasenet.mod

import com.peasenet.config.FullbrightConfig
import com.peasenet.main.GavinsMod
import com.peasenet.main.GavinsModClient
import com.peasenet.main.Mods
import com.peasenet.main.Settings
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.world.LightType

class FullBrightClientInitializer : ClientModInitializer {
    override fun onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(ClientTickEvents.StartTick {
            if (GavinsModClient.player == null) return@StartTick
            checkAutoFullBright()
        })
    }

    /**
     * Checks if the auto full bright feature is enabled.
     */
    private fun checkAutoFullBright() {
        var config = Settings.getConfig<FullbrightConfig>("fullbright")
        if (!config.autoFullBright) return
        val skyBrightness = GavinsModClient.minecraftClient.getWorld()
            .getLightLevel(LightType.SKY, GavinsModClient.player!!.getBlockPos().up())
        val blockBrightness = GavinsModClient.minecraftClient.getWorld()
            .getLightLevel(LightType.BLOCK, GavinsModClient.player!!.getBlockPos().up())
        val currTime = GavinsModClient.minecraftClient.getWorld().timeOfDay % 24000
        var shouldBeFullBright = (currTime >= 13000 || currTime <= 100 || skyBrightness <= 2) && blockBrightness <= 2
        shouldBeFullBright = shouldBeFullBright || GavinsModClient.player!!.isSubmergedInWater()
        Mods.getMod("fullbright") ?: return
        if (shouldBeFullBright && !Mods.isActive("fullbright"))
            Mods.getMod("fullbright")?.activate()
        else if (Mods.isActive("fullbright") && !shouldBeFullBright)
            Mods.getMod("fullbright")?.deactivate()
    }
}