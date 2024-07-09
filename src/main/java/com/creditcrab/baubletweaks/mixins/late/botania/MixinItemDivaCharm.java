package com.creditcrab.baubletweaks.mixins.late.botania;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.lwjgl.Sys;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.subtile.functional.SubTileHeiseiDream;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.item.equipment.bauble.ItemDivaCharm;
import vazkii.botania.common.lib.LibObfuscation;

import java.util.List;

@Mixin(value = ItemDivaCharm.class,remap = false)
public abstract class MixinItemDivaCharm  extends ItemBauble implements IBaubleExpanded, IManaUsingItem, IBaubleRender {
    public MixinItemDivaCharm(String name) {
        super(name);
    }

    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[]{BaubleExpandedSlots.charmType};
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        BaubleItemHelper.addSlotInformation(list,getBaubleTypes(stack));
    }

    /**
     * @author Nick
     * @reason use extended bauble query in damage event
     */
    @SubscribeEvent
    @Overwrite
    public void onEntityDamaged(LivingHurtEvent event) {
        if (event.source.getEntity() instanceof EntityPlayer player && event.entityLiving instanceof EntityLiving && !event.entityLiving.worldObj.isRemote && Math.random() < 0.6000000238418579) {
            ItemStack charm = BaublesApi.getBaubles(player).getStackInSlot(BaubleExpandedSlots.getIndexesOfAssignedSlotsOfType(BaubleExpandedSlots.charmType)[0]);
            if (charm != null && charm.getItem() == (Object)this) {
                if (ManaItemHandler.requestManaExact(charm, player, 250, false)) {
                    List<IMob> mobs = player.worldObj.getEntitiesWithinAABB(IMob.class, AxisAlignedBB.getBoundingBox(event.entity.posX - 20.0, event.entity.posY - 20.0, event.entity.posZ - 20.0, event.entity.posX + 20.0, event.entity.posY + 20.0, event.entity.posZ + 20.0));
                    if (mobs.size() > 1 && SubTileHeiseiDream.brainwashEntity((EntityLiving)event.entityLiving, mobs)) {
                        if (event.entityLiving instanceof EntityCreeper) {
                            ReflectionHelper.setPrivateValue(EntityCreeper.class, (EntityCreeper)event.entityLiving, 2, LibObfuscation.TIME_SINCE_IGNITED);
                        }

                        event.entityLiving.heal(event.entityLiving.getMaxHealth());
                        if (event.entityLiving.isDead) {
                            event.entityLiving.isDead = false;
                        }

                        ManaItemHandler.requestManaExact(charm, player, 250, true);
                        player.worldObj.playSoundAtEntity(player, "botania:divaCharm", 1.0F, 1.0F);
                        double x = event.entityLiving.posX;
                        double y = event.entityLiving.posY;
                        double z = event.entityLiving.posZ;

                        for(int i = 0; i < 50; ++i) {
                            Botania.proxy.sparkleFX(event.entityLiving.worldObj, x + Math.random() * (double)event.entityLiving.width, y + Math.random() * (double)event.entityLiving.height, z + Math.random() * (double)event.entityLiving.width, 1.0F, 1.0F, 0.25F, 1.0F, 3);
                        }
                    }
                }
            }
        }

    }
}
