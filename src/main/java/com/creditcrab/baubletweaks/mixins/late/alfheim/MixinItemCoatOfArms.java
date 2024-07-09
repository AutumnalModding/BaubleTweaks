package com.creditcrab.baubletweaks.mixins.late.alfheim;

import alfheim.api.item.IPriestColorOverride;
import alfheim.common.item.equipment.bauble.ItemCoatOfArms;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import vazkii.botania.api.item.ICosmeticBauble;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

@Mixin(ItemCoatOfArms.class)
public abstract class MixinItemCoatOfArms extends ItemBauble implements IBaubleExpanded, ICosmeticBauble, IPriestColorOverride, IFlowerComponent {
    public MixinItemCoatOfArms(String name) {
        super(name);
    }

    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[]{"cosmetic"};
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        BaubleItemHelper.addSlotInformation(list,getBaubleTypes(stack));
    }
}
