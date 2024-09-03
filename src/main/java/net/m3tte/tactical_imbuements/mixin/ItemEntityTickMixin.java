package net.m3tte.tactical_imbuements.mixin;

import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ItemEntity.class)
public class ItemEntityTickMixin {

    @Inject(at = @At(value = "HEAD"), method = "tick()V", cancellable = true)
    private void tick(CallbackInfo cbk) {

        ItemEntity itemEntity = ((ItemEntity)(Object)this);

        ItemStack stack = ((getItemInvoker) itemEntity).invokeGetItem();

        if (stack.getTag() == null)
            return;
        String imbueType = stack.getTag().getString("imbueType");


        if (imbueType.length() > 1) {
            stack.removeTagKey("imbueCounter");
            stack.removeTagKey("maxImbueTime");
            stack.removeTagKey("imbueType");
        }

    }
}
