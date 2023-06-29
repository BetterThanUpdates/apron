package io.github.betterthanupdates.reforged.mixin;

import forge.IShearable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.stat.Stats;

import io.github.betterthanupdates.reforged.item.ReforgedItem;

@Mixin(ShearsItem.class)
public class ShearsItemMixin extends Item implements ReforgedItem {
	public ShearsItemMixin(int i) {
		super(i);
	}

	/**
	 * @author Kleadron
	 * @reason implement Reforged function
	 */
	@Inject(method = "postMine", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;postMine(Lnet/minecraft/item/ItemStack;IIIILnet/minecraft/entity/LivingEntity;)Z"))
	private void reforged$postMine(ItemStack itemstack, int i, int j, int k, int l, LivingEntity livingEntity, CallbackInfoReturnable<Boolean> cir) {
		if (i != Block.COBWEB.id && i != Block.LEAVES.id // Don't apply damage if it has already been applied before.
				&& Block.BY_ID[i] instanceof IShearable) {
			itemstack.applyDamage(1, livingEntity);
		}
	}

	@Override
	public void interactWithEntity(ItemStack itemstack, LivingEntity entity) {
		if (!entity.world.isClient) {
			if (entity instanceof IShearable) {
				IShearable target = (IShearable) entity;

				if (target.isShearable(itemstack, entity.world, (int) entity.x, (int) entity.y, (int) entity.z)) {
					for (ItemStack stack : target.onSheared(itemstack, entity.world, (int) entity.x, (int) entity.y, (int) entity.z)) {
						ItemEntity ent = entity.dropItem(stack, 1.0F);
						ent.yVelocity += (double) (entity.rand.nextFloat() * 0.05F);
						ent.xVelocity += (double) ((entity.rand.nextFloat() - entity.rand.nextFloat()) * 0.1F);
						ent.zVelocity += (double) ((entity.rand.nextFloat() - entity.rand.nextFloat()) * 0.1F);
					}

					itemstack.applyDamage(1, entity);
				}
			}
		}
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, PlayerEntity player) {
		if (!player.world.isClient) {
			int id = player.world.getBlockId(x, y, z);

			if (Block.BY_ID[id] != null && Block.BY_ID[id] instanceof IShearable) {
				IShearable target = (IShearable) Block.BY_ID[id];

				if (target.isShearable(itemstack, player.world, x, y, z)) {
					for (ItemStack stack : target.onSheared(itemstack, player.world, x, y, z)) {
						float f = 0.7F;
						double d = (double) (player.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5;
						double d1 = (double) (player.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5;
						double d2 = (double) (player.rand.nextFloat() * f) + (double) (1.0F - f) * 0.5;
						ItemEntity entityitem = new ItemEntity(player.world, (double) x + d, (double) y + d1, (double) z + d2, stack);
						entityitem.pickupDelay = 10;
						player.world.spawnEntity(entityitem);
					}

					itemstack.applyDamage(1, player);
					player.increaseStat(Stats.mineBlock[id], 1);
				}
			}
		}

		return false;
	}
}
