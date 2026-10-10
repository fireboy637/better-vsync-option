package com.fireboy637.bettervsyncoption.mixin.accessor;

import com.mojang.renderpearl.api.device.GpuSurface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GpuSurface.PresentMode.class)
public interface PresentModeAccessor {
    @Accessor("PRESENT_MODES_VSYNC")
    static GpuSurface.PresentMode[] getPresentModesVsync() {
        throw new AssertionError();
    }
}
