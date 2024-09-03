
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

public class FlaskOfVenomItem extends Item {
	public FlaskOfVenomItem() {
		super(new Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(4).rarity(Rarity.UNCOMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.BOW;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(new TextComponent("[On Use] ").withStyle(ChatFormatting.YELLOW).append(new TextComponent("Applies poison to your weapon for 30 seconds.").withStyle(ChatFormatting.GRAY)));
		list.add(new TextComponent("[On Impact] ").withStyle(ChatFormatting.YELLOW).append(new TextComponent("Splash Your enemy with venom, decaying their armor and body.").withStyle(ChatFormatting.GRAY)));
		list.add(new TextComponent("[Venomous Bile] ").withStyle(ChatFormatting.DARK_GREEN).append(new TextComponent("Rapidly decays enemy armor. Parries decay enemy weapon.").withStyle(ChatFormatting.GREEN)));
		list.add(new TextComponent("Many shall falter at the edge of your blade, reduced to a pile of mire.").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);

		UseImbueFlasks.useImbuementFlask(world, ImbuementDefinitions.VENOMID, entity, hand);
		return ar;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		UseImbueFlasks.useImbuementFlask(context.getLevel(), ImbuementDefinitions.VENOMID, context.getPlayer(), context.getHand());
		return InteractionResult.SUCCESS;
	}
}