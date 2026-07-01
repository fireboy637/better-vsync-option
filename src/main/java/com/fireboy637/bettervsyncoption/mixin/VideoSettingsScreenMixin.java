package com.fireboy637.bettervsyncoption.mixin;

import com.fireboy637.bettervsyncoption.BetterVsyncOption;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.options.VideoSettingsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VideoSettingsScreen.class)
public class VideoSettingsScreenMixin {
    @SuppressWarnings("rawtypes")
    @Redirect(
            method = "displayOptions",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Options;enableVsync()Lnet/minecraft/client/OptionInstance;"
            )
    )
    private static OptionInstance redirectVsync(Options instance) {
        return BetterVsyncOption.presentModeOption;
    }
}
