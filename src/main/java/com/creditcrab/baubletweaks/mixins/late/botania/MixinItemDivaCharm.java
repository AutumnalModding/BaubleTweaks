package com.creditcrab.baubletweaks.mixins.late.botania;

import baubles.api.BaubleType;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.IBaubleExpanded;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.item.equipment.bauble.ItemDivaCharm;

@Mixin(ItemDivaCharm.class)
public abstract class MixinItemDivaCharm  extends ItemBauble implements IBaubleExpanded, IManaUsingItem, IBaubleRender {


    public MixinItemDivaCharm(String name) {
        super(name);
    }

    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[]{BaubleExpandedSlots.amuletType,BaubleExpandedSlots.charmType};
    }
}
