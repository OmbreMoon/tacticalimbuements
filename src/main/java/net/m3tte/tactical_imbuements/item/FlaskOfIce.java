
package net.m3tte.tactical_imbuements.item;

import net.m3tte.tactical_imbuements.definitions.ImbuementDefinitions;
import net.m3tte.tactical_imbuements.procedures.UseImbueFlasks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;

public class FlaskOfIce extends Item {
	public FlaskOfIce() {
		super(new Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(4).rarity(Rarity.UNCOMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.BOW;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(new TextComponent("[On Use] ").withStyle(ChatFormatting.YELLOW).append(new TextComponent("Applies freeze to your weapon for 30 seconds.").withStyle(ChatFormatting.GRAY)));
		list.add(new TextComponent("[On Impact] ").withStyle(ChatFormatting.YELLOW).append(new TextComponent("Freeze nearby enemies").withStyle(ChatFormatting.GRAY)));
		list.add(new TextComponent("[Freeze] ").withStyle(ChatFormatting.DARK_AQUA).append(new TextComponent("Applies stacking slowing effects.").withStyle(ChatFormatting.AQUA)));
		list.add(new TextComponent("The everlasting frost shall claim the lives of many, chilling their bodies to the core. ").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);

		UseImbueFlasks.useImbuementFlask(world, ImbuementDefinitions.FREEZEID, entity, hand);
		return ar;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		UseImbueFlasks.useImbuementFlask(context.getLevel(), ImbuementDefinitions.FREEZEID, context.getPlayer(), context.getHand());
		return InteractionResult.SUCCESS;
	}
}
