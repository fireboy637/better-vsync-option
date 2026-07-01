package com.fireboy637.bettervsyncoption.mixin.blaze3d;

import com.fireboy637.bettervsyncoption.BetterVsyncOption;
import com.mojang.blaze3d.systems.GpuSurface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

@Mixin(GpuSurface.PresentMode.class)
public class PresentModeMixin {
    // Use our custom present mode :)
    @Inject(
            method = "getSupportedVsyncMode",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void tryCustomPresentMode(
            Collection<GpuSurface.PresentMode> supportedModes,
            boolean vsync,
            CallbackInfoReturnable<GpuSurface.PresentMode> cir)
    {
        var custom = BetterVsyncOption.presentModeOption.get();
        cir.setReturnValue(supportedModes.contains(custom) ? custom : GpuSurface.PresentMode.FIFO); // FIFO is always supported (on normal drivers)
    }
}
