package com.fireboy637.bettervsyncoption.mixin;

import com.fireboy637.bettervsyncoption.BetterVsyncOption;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public abstract class OptionsMixin {
    @Inject(
            method = "processOptions",
            at = @At("HEAD")
    )
    private static void saveCustomPresentMode(Options.FieldAccess access, CallbackInfo ci) {
        access.process("presentMode", BetterVsyncOption.presentModeOption);
    }
}
