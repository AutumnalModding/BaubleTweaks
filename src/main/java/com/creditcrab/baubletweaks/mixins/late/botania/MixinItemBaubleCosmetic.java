package com.creditcrab.baubletweaks.mixins.late.botania;

import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import vazkii.botania.api.item.ICosmeticBauble;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.item.equipment.bauble.ItemBaubleCosmetic;

@Mixin(ItemBaubleCosmetic.class)
public abstract class MixinItemBaubleCosmetic extends ItemBauble implements IBaubleExpanded, ICosmeticBauble {
    public MixinItemBaubleCosmetic(String name) {
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
