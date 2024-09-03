package net.m3tte.tactical_imbuements.mixin;

import com.mojang.blaze3d.vertex.BufferBuilder;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ItemEntity.class)
public interface getItemInvoker {
    @Invoker("getItem")
    public ItemStack invokeGetItem();
}
