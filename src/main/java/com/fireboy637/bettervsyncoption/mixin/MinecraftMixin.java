package com.fireboy637.bettervsyncoption.mixin;

import com.fireboy637.bettervsyncoption.BetterVsyncOption;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.GpuSurface;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Collection;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @WrapOperation(
            method = "renderFrame",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/GpuSurface$PresentMode;getSupportedVsyncMode(Ljava/util/Collection;Z)Lcom/mojang/blaze3d/systems/GpuSurface$PresentMode;"
            )
    )
    private GpuSurface.PresentMode wrapPresentMode( // Try to put custom present mode
            Collection<GpuSurface.PresentMode> supportedModes,
            boolean vsync,
            Operation<GpuSurface.PresentMode> original
    ) {
        var customMode = BetterVsyncOption.presentModeOption.get();
        return supportedModes.contains(customMode) ?
                customMode : original.call(supportedModes, vsync);
    }
}
