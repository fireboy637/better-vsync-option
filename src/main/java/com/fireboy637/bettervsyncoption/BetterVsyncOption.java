package com.fireboy637.bettervsyncoption;

import com.mojang.renderpearl.api.device.GpuSurface;
import com.mojang.serialization.Codec;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;

import net.minecraft.network.chat.TextColor;
import net.minecraft.util.ByIdMap;

import java.util.List;

public class BetterVsyncOption {
    private static final Codec<GpuSurface.PresentMode> CODEC = Codec.INT.xmap(
			ByIdMap.continuous(
					Enum::ordinal,
					GpuSurface.PresentMode.values(),
					ByIdMap.OutOfBoundsStrategy.WRAP
			)::apply,
			Enum::ordinal
	);
	public static final OptionInstance<GpuSurface.PresentMode> presentModeOption = new OptionInstance<>(
			"options.presentMode.presentModeOption",
			mode -> {
				var minecraft = Minecraft.getInstance();
				var base = Component.translatable("options.presentMode." + mode.name());
				if (!minecraft.windowSurface().supportedPresentModes().contains(mode)) {
                    base.append("\n").append(Component.translatable("options.presentMode.unsupported").withColor(TextColor.RED));
                }
				return Tooltip.create(base);
			},
			(_, value) -> Component.literal(value.name()),
			new OptionInstance.Enum<>(List.of(GpuSurface.PresentMode.values()), CODEC),
			GpuSurface.PresentMode.FIFO,
			mode -> {
				var minecraft = Minecraft.getInstance();
				minecraft.options.enableVsync().set(List.of(GpuSurface.PresentMode.PRESENT_MODES_VSYNC).contains(mode));
				minecraft.invalidateSurfaceConfiguration();
			}
	);
}
