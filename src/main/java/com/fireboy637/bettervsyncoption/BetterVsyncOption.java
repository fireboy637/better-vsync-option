package com.fireboy637.bettervsyncoption;

import com.mojang.blaze3d.systems.GpuSurface;
import com.mojang.serialization.Codec;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;

import net.minecraft.util.ByIdMap;

import java.util.List;
import java.util.function.IntFunction;

public class BetterVsyncOption {
	private static final IntFunction<GpuSurface.PresentMode> BY_ID = ByIdMap.continuous(Enum::ordinal, GpuSurface.PresentMode.values(), ByIdMap.OutOfBoundsStrategy.WRAP);
	private static final Codec<GpuSurface.PresentMode> CODEC = Codec.INT.xmap(BY_ID::apply, Enum::ordinal);
	public static final OptionInstance<GpuSurface.PresentMode> presentModeOption = new OptionInstance<>(
			"options.presentModeOption",
			OptionInstance.noTooltip(),
			(_, value) -> Component.literal(value.name()),
			new OptionInstance.Enum<>(List.of(GpuSurface.PresentMode.values()), CODEC),
			GpuSurface.PresentMode.FIFO,
			_ -> Minecraft.getInstance().invalidateSurfaceConfiguration()
	);
}
