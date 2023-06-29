package io.github.betterthanupdates.reforged.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import reforged.ICustomDrop;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import io.github.betterthanupdates.reforged.block.ReforgedBlock;

@Mixin(Block.class)
public abstract class BlockMixin implements ReforgedBlock {
	@Shadow
	public abstract int getDropId(int i, Random random);

	@Shadow
	public abstract int getDropCount(Random random);

	@Shadow
	protected abstract int droppedMeta(int i);

	// Reforged fields
	@Unique
	private ItemStack currentItemStack;

	@Override
	public int quantityDropped(int i, Random random) {
		return this.getDropCount(random);
	}

	@Override
	public int quantityDropped(int i, Random random, ItemStack stack) {
		return stack != null && stack.getItem() instanceof ICustomDrop
				? ((ICustomDrop) stack.getItem()).getQuantityDropped((Block) (Object) this, i, random, stack)
				: this.quantityDropped(i, random);
	}

	@Override
	public int damageDropped(int i, ItemStack stack) {
		return stack != null && stack.getItem() instanceof ICustomDrop ? ((ICustomDrop) stack.getItem()).getDamageDropped((Block) (Object) this, i, stack) : this.droppedMeta(i);
	}

	@Override
	public int idDropped(int i, Random random, ItemStack stack) {
		return stack != null && stack.getItem() instanceof ICustomDrop
				? ((ICustomDrop) stack.getItem()).getIdDropped((Block) (Object) this, i, random, stack)
				: this.getDropId(i, random);
	}

	int cachedL;

	@Inject(method = "beforeDestroyedByExplosion", at = @At("HEAD"))
	private void reforged$beforeDestroyedByExplosion(World i, int j, int k, int l, int f, float par6, CallbackInfo ci) {
		this.cachedL = f;
	}

	@Redirect(method = "beforeDestroyedByExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getDropCount(Ljava/util/Random;)I"))
	private int reforged$beforeDestroyedByExplosion(Block instance, Random random) {
		return ((ReforgedBlock) instance).quantityDropped(this.cachedL, random, this.currentItemStack);
	}

	@Redirect(method = "beforeDestroyedByExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getDropId(ILjava/util/Random;)I"))
	private int reforged$beforeDestroyedByExplosion(Block instance, int l, Random random) {
		return ((ReforgedBlock) instance).idDropped(l, random, this.currentItemStack);
	}

	@Redirect(method = "beforeDestroyedByExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;droppedMeta(I)I"))
	private int reforged$beforeDestroyedByExplosion(Block instance, int i) {
		return ((ReforgedBlock) instance).damageDropped(i, this.currentItemStack);
	}

	@Inject(method = "afterBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;drop(Lnet/minecraft/world/World;IIII)V"))
	private void reforged$getCurrentItemStack(World arg2, PlayerEntity i, int j, int k, int l, int par6, CallbackInfo ci) {
		this.currentItemStack = i.getHeldItem();
	}

	@Inject(method = "afterBreak", at = @At("RETURN"))
	private void reforged$afterBreak(World arg2, PlayerEntity i, int j, int k, int l, int par6, CallbackInfo ci) {
		this.currentItemStack = null;
	}
}
