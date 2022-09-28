package io.github.betterthanupdates.forge.mixin.client;

import modloader.ModLoader;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.RedstoneDustBlock;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderer;
import net.minecraft.world.BlockView;

import io.github.betterthanupdates.forge.ForgeClientReflection;
import io.github.betterthanupdates.forge.client.render.ForgeTessellator;

@Environment(EnvType.CLIENT)
@Mixin(BlockRenderer.class)
public abstract class BlockRendererMixin {
	@Shadow
	public BlockView blockView;
	@Shadow
	public int textureOverride;
	@Shadow
	public boolean field_81;
	@Shadow
	public boolean mirrorTexture;
	@Shadow
	public boolean renderAllSides;
	@Shadow
	public int eastFaceRotation;
	@Shadow
	public int westFaceRotation;
	@Shadow
	public int southFaceRotation;
	@Shadow
	public int northFaceRotation;
	@Shadow
	public int topFaceRotation;
	@Shadow
	public int bottomFaceRotation;
	@Shadow
	public int field_55;

	@Shadow
	public abstract void renderEastFace(Block arg, double d, double e, double f, int i);

	@Shadow
	public abstract void renderWestFace(Block arg, double d, double e, double f, int i);

	@Shadow
	public abstract void renderNorthFace(Block arg, double d, double e, double f, int i);

	@Shadow
	public abstract void renderSouthFace(Block arg, double d, double e, double f, int i);

	@Shadow
	public boolean field_92;
	@Shadow
	public float field_68;
	@Shadow
	public float field_66;
	@Shadow
	public float field_65;
	@Shadow
	public float field_64;
	@Shadow
	public float field_63;
	@Shadow
	public float field_62;
	@Shadow
	public float field_61;
	@Shadow
	public float field_60;
	@Shadow
	public float field_59;
	@Shadow
	public float field_58;
	@Shadow
	public float field_57;
	@Shadow
	public float field_56;
	@Shadow
	public float field_105;
	@Shadow
	public float field_41;
	@Shadow
	public float field_52;
	@Shadow
	public float field_97;
	@Shadow
	public float field_47;
	@Shadow
	public float field_48;
	@Shadow
	public float field_54;
	@Shadow
	public float field_50;
	@Shadow
	public float field_42;
	@Shadow
	public boolean field_70;
	@Shadow
	public boolean field_74;
	@Shadow
	public boolean field_76;
	@Shadow
	public boolean field_78;
	@Shadow
	public float field_100;
	@Shadow
	public float field_101;
	@Shadow
	public float field_51;
	@Shadow
	public float field_94;
	@Shadow
	public float field_43;
	@Shadow
	public float field_44;
	@Shadow
	public float field_53;
	@Shadow
	public float field_45;
	@Shadow
	public float field_102;
	@Shadow
	public boolean field_75;
	@Shadow
	public boolean field_71;
	@Shadow
	public boolean field_73;
	@Shadow
	public boolean field_79;
	@Shadow
	public float field_104;
	@Shadow
	public float field_99;
	@Shadow
	public float field_49;
	@Shadow
	public boolean field_72;
	@Shadow
	public boolean field_80;
	@Shadow
	public float field_103;
	@Shadow
	public float field_96;
	@Shadow
	public float field_46;
	@Shadow
	public boolean field_69;
	@Shadow
	public boolean field_77;

	@Shadow
	public abstract void renderTopFace(Block arg, double d, double e, double f, int i);

	@Shadow
	public float field_98;

	@Shadow
	public abstract void renderBottomFace(Block arg, double d, double e, double f, int i);

	@Shadow
	public float field_95;
	@Shadow
	public float field_93;

	/**
	 * @author Eloraam
	 * @reason implement Forge hooks
	 */
	@Inject(method = "<init>()V", at = @At("RETURN"))
	private void forge$init(CallbackInfo ci) {
		this.blockView = null;
		this.forgeCtr();
	}

	/**
	 * @author Eloraam
	 * @reason implement Forge hooks
	 */
	@Inject(method = "<init>(Lnet/minecraft/world/BlockView;)V", at = @At("RETURN"))
	private void ctrSetDefaultValues(BlockView blockView, CallbackInfo ci) {
		this.forgeCtr();
	}

	/**
	 * @author Eloraam
	 * @reason implement Forge hooks
	 */
	private void forgeCtr() {
		this.textureOverride = -1;
		this.mirrorTexture = false;
		this.renderAllSides = false;
		this.field_81 = true;
		this.eastFaceRotation = 0;
		this.westFaceRotation = 0;
		this.southFaceRotation = 0;
		this.northFaceRotation = 0;
		this.topFaceRotation = 0;
		this.bottomFaceRotation = 0;
		this.field_55 = 1;
	}

	/**
	 * @author Eloraam
	 * @reason implement Forge hooks
	 */
	@Inject(method = "render", cancellable = true, at = @At("RETURN"))
	private void forge$render(Block block, int i, int j, int k, CallbackInfoReturnable<Boolean> cir) {
		int l = block.getRenderType();

		switch (l) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
				break;
			default:
				cir.setReturnValue(ModLoader.RenderWorldBlock((BlockRenderer) (Object) this, this.blockView, i, j, k, block, l));
		}
	}

	/**
	 * @author Eloraam
	 * @reason implement Forge hooks
	 */
	@Overwrite
	public boolean renderRedstoneDust(Block block, int i, int j, int k) {
		Tessellator tessellator = Tessellator.INSTANCE;
		int l = this.blockView.getBlockMeta(i, j, k);
		int i1 = block.getTextureForSide(1, l);

		if (this.textureOverride >= 0) {
			i1 = this.textureOverride;
		}

		float f = block.getBrightness(this.blockView, i, j, k);
		float[] af = ForgeClientReflection.BlockRenderer$redstoneColors[l];
		float f1 = af[0];
		float f2 = af[1];
		float f3 = af[2];
		tessellator.color(f * f1, f * f2, f * f3);
		int j1 = (i1 & 15) << 4;
		int k1 = i1 & 240;
		double d = (float) j1 / 256.0F;
		double d1 = ((float) j1 + 15.99F) / 256.0F;
		double d2 = (float) k1 / 256.0F;
		double d3 = ((float) k1 + 15.99F) / 256.0F;
		boolean flag = RedstoneDustBlock.method_1287(this.blockView, i - 1, j, k, 1)
				|| !this.blockView.canSuffocate(i - 1, j, k) && RedstoneDustBlock.method_1287(this.blockView, i - 1, j - 1, k, -1);
		boolean flag1 = RedstoneDustBlock.method_1287(this.blockView, i + 1, j, k, 3)
				|| !this.blockView.canSuffocate(i + 1, j, k) && RedstoneDustBlock.method_1287(this.blockView, i + 1, j - 1, k, -1);
		boolean flag2 = RedstoneDustBlock.method_1287(this.blockView, i, j, k - 1, 2)
				|| !this.blockView.canSuffocate(i, j, k - 1) && RedstoneDustBlock.method_1287(this.blockView, i, j - 1, k - 1, -1);
		boolean flag3 = RedstoneDustBlock.method_1287(this.blockView, i, j, k + 1, 0)
				|| !this.blockView.canSuffocate(i, j, k + 1) && RedstoneDustBlock.method_1287(this.blockView, i, j - 1, k + 1, -1);

		if (!this.blockView.canSuffocate(i, j + 1, k)) {
			if (this.blockView.canSuffocate(i - 1, j, k) && RedstoneDustBlock.method_1287(this.blockView, i - 1, j + 1, k, -1)) {
				flag = true;
			}

			if (this.blockView.canSuffocate(i + 1, j, k) && RedstoneDustBlock.method_1287(this.blockView, i + 1, j + 1, k, -1)) {
				flag1 = true;
			}

			if (this.blockView.canSuffocate(i, j, k - 1) && RedstoneDustBlock.method_1287(this.blockView, i, j + 1, k - 1, -1)) {
				flag2 = true;
			}

			if (this.blockView.canSuffocate(i, j, k + 1) && RedstoneDustBlock.method_1287(this.blockView, i, j + 1, k + 1, -1)) {
				flag3 = true;
			}
		}

		float f4 = (float) (i + 0);
		float f5 = (float) (i + 1);
		float f6 = (float) (k + 0);
		float f7 = (float) (k + 1);
		byte byte0 = 0;

		if ((flag || flag1) && !flag2 && !flag3) {
			byte0 = 1;
		}

		if ((flag2 || flag3) && !flag1 && !flag) {
			byte0 = 2;
		}

		if (byte0 != 0) {
			d = (float) (j1 + 16) / 256.0F;
			d1 = ((float) (j1 + 16) + 15.99F) / 256.0F;
			d2 = (float) k1 / 256.0F;
			d3 = ((float) k1 + 15.99F) / 256.0F;
		}

		if (byte0 == 0) {
			if (flag1 || flag2 || flag3 || flag) {
				if (!flag) {
					f4 += 0.3125F;
				}

				if (!flag) {
					d += 0.01953125;
				}

				if (!flag1) {
					f5 -= 0.3125F;
				}

				if (!flag1) {
					d1 -= 0.01953125;
				}

				if (!flag2) {
					f6 += 0.3125F;
				}

				if (!flag2) {
					d2 += 0.01953125;
				}

				if (!flag3) {
					f7 -= 0.3125F;
				}

				if (!flag3) {
					d3 -= 0.01953125;
				}
			}

			tessellator.vertex((double) f5, (double) ((float) j + 0.015625F), (double) f7, d1, d3);
			tessellator.vertex((double) f5, (double) ((float) j + 0.015625F), (double) f6, d1, d2);
			tessellator.vertex((double) f4, (double) ((float) j + 0.015625F), (double) f6, d, d2);
			tessellator.vertex((double) f4, (double) ((float) j + 0.015625F), (double) f7, d, d3);
			tessellator.color(f, f, f);
			tessellator.vertex((double) f5, (double) ((float) j + 0.015625F), (double) f7, d1, d3 + 0.0625);
			tessellator.vertex((double) f5, (double) ((float) j + 0.015625F), (double) f6, d1, d2 + 0.0625);
			tessellator.vertex((double) f4, (double) ((float) j + 0.015625F), (double) f6, d, d2 + 0.0625);
			tessellator.vertex((double) f4, (double) ((float) j + 0.015625F), (double) f7, d, d3 + 0.0625);
		} else if (byte0 == 1) {
			tessellator.vertex((double) f5, (double) ((float) j + 0.015625F), (double) f7, d1, d3);
			tessellator.vertex((double) f5, (double) ((float) j + 0.015625F), (double) f6, d1, d2);
			tessellator.vertex((double) f4, (double) ((float) j + 0.015625F), (double) f6, d, d2);
			tessellator.vertex((double) f4, (double) ((float) j + 0.015625F), (double) f7, d, d3);
			tessellator.color(f, f, f);
			tessellator.vertex((double) f5, (double) ((float) j + 0.015625F), (double) f7, d1, d3 + 0.0625);
			tessellator.vertex((double) f5, (double) ((float) j + 0.015625F), (double) f6, d1, d2 + 0.0625);
			tessellator.vertex((double) f4, (double) ((float) j + 0.015625F), (double) f6, d, d2 + 0.0625);
			tessellator.vertex((double) f4, (double) ((float) j + 0.015625F), (double) f7, d, d3 + 0.0625);
		} else if (byte0 == 2) {
			tessellator.vertex((double) f5, (double) ((float) j + 0.015625F), (double) f7, d1, d3);
			tessellator.vertex((double) f5, (double) ((float) j + 0.015625F), (double) f6, d, d3);
			tessellator.vertex((double) f4, (double) ((float) j + 0.015625F), (double) f6, d, d2);
			tessellator.vertex((double) f4, (double) ((float) j + 0.015625F), (double) f7, d1, d2);
			tessellator.color(f, f, f);
			tessellator.vertex((double) f5, (double) ((float) j + 0.015625F), (double) f7, d1, d3 + 0.0625);
			tessellator.vertex((double) f5, (double) ((float) j + 0.015625F), (double) f6, d, d3 + 0.0625);
			tessellator.vertex((double) f4, (double) ((float) j + 0.015625F), (double) f6, d, d2 + 0.0625);
			tessellator.vertex((double) f4, (double) ((float) j + 0.015625F), (double) f7, d1, d2 + 0.0625);
		}

		if (!this.blockView.canSuffocate(i, j + 1, k)) {
			double d4 = (double) ((float) (j1 + 16) / 256.0F);
			double d5 = (double) (((float) (j1 + 16) + 15.99F) / 256.0F);
			double d6 = (double) ((float) k1 / 256.0F);
			double d7 = (double) (((float) k1 + 15.99F) / 256.0F);

			if (this.blockView.canSuffocate(i - 1, j, k) && this.blockView.getBlockId(i - 1, j + 1, k) == Block.REDSTONE_DUST.id) {
				tessellator.color(f * f1, f * f2, f * f3);
				tessellator.vertex((double) ((float) i + 0.015625F), (double) ((float) (j + 1) + 0.021875F), (double) (k + 1), d5, d6);
				tessellator.vertex((double) ((float) i + 0.015625F), (double) (j + 0), (double) (k + 1), d4, d6);
				tessellator.vertex((double) ((float) i + 0.015625F), (double) (j + 0), (double) (k + 0), d4, d7);
				tessellator.vertex((double) ((float) i + 0.015625F), (double) ((float) (j + 1) + 0.021875F), (double) (k + 0), d5, d7);
				tessellator.color(f, f, f);
				tessellator.vertex((double) ((float) i + 0.015625F), (double) ((float) (j + 1) + 0.021875F), (double) (k + 1), d5, d6 + 0.0625);
				tessellator.vertex((double) ((float) i + 0.015625F), (double) (j + 0), (double) (k + 1), d4, d6 + 0.0625);
				tessellator.vertex((double) ((float) i + 0.015625F), (double) (j + 0), (double) (k + 0), d4, d7 + 0.0625);
				tessellator.vertex((double) ((float) i + 0.015625F), (double) ((float) (j + 1) + 0.021875F), (double) (k + 0), d5, d7 + 0.0625);
			}

			if (this.blockView.canSuffocate(i + 1, j, k) && this.blockView.getBlockId(i + 1, j + 1, k) == Block.REDSTONE_DUST.id) {
				tessellator.color(f * f1, f * f2, f * f3);
				tessellator.vertex((double) ((float) (i + 1) - 0.015625F), (double) (j + 0), (double) (k + 1), d4, d7);
				tessellator.vertex((double) ((float) (i + 1) - 0.015625F), (double) ((float) (j + 1) + 0.021875F), (double) (k + 1), d5, d7);
				tessellator.vertex((double) ((float) (i + 1) - 0.015625F), (double) ((float) (j + 1) + 0.021875F), (double) (k + 0), d5, d6);
				tessellator.vertex((double) ((float) (i + 1) - 0.015625F), (double) (j + 0), (double) (k + 0), d4, d6);
				tessellator.color(f, f, f);
				tessellator.vertex((double) ((float) (i + 1) - 0.015625F), (double) (j + 0), (double) (k + 1), d4, d7 + 0.0625);
				tessellator.vertex((double) ((float) (i + 1) - 0.015625F), (double) ((float) (j + 1) + 0.021875F), (double) (k + 1), d5, d7 + 0.0625);
				tessellator.vertex((double) ((float) (i + 1) - 0.015625F), (double) ((float) (j + 1) + 0.021875F), (double) (k + 0), d5, d6 + 0.0625);
				tessellator.vertex((double) ((float) (i + 1) - 0.015625F), (double) (j + 0), (double) (k + 0), d4, d6 + 0.0625);
			}

			if (this.blockView.canSuffocate(i, j, k - 1) && this.blockView.getBlockId(i, j + 1, k - 1) == Block.REDSTONE_DUST.id) {
				tessellator.color(f * f1, f * f2, f * f3);
				tessellator.vertex((double) (i + 1), (double) (j + 0), (double) ((float) k + 0.015625F), d4, d7);
				tessellator.vertex((double) (i + 1), (double) ((float) (j + 1) + 0.021875F), (double) ((float) k + 0.015625F), d5, d7);
				tessellator.vertex((double) (i + 0), (double) ((float) (j + 1) + 0.021875F), (double) ((float) k + 0.015625F), d5, d6);
				tessellator.vertex((double) (i + 0), (double) (j + 0), (double) ((float) k + 0.015625F), d4, d6);
				tessellator.color(f, f, f);
				tessellator.vertex((double) (i + 1), (double) (j + 0), (double) ((float) k + 0.015625F), d4, d7 + 0.0625);
				tessellator.vertex((double) (i + 1), (double) ((float) (j + 1) + 0.021875F), (double) ((float) k + 0.015625F), d5, d7 + 0.0625);
				tessellator.vertex((double) (i + 0), (double) ((float) (j + 1) + 0.021875F), (double) ((float) k + 0.015625F), d5, d6 + 0.0625);
				tessellator.vertex((double) (i + 0), (double) (j + 0), (double) ((float) k + 0.015625F), d4, d6 + 0.0625);
			}

			if (this.blockView.canSuffocate(i, j, k + 1) && this.blockView.getBlockId(i, j + 1, k + 1) == Block.REDSTONE_DUST.id) {
				tessellator.color(f * f1, f * f2, f * f3);
				tessellator.vertex((double) (i + 1), (double) ((float) (j + 1) + 0.021875F), (double) ((float) (k + 1) - 0.015625F), d5, d6);
				tessellator.vertex((double) (i + 1), (double) (j + 0), (double) ((float) (k + 1) - 0.015625F), d4, d6);
				tessellator.vertex((double) (i + 0), (double) (j + 0), (double) ((float) (k + 1) - 0.015625F), d4, d7);
				tessellator.vertex((double) (i + 0), (double) ((float) (j + 1) + 0.021875F), (double) ((float) (k + 1) - 0.015625F), d5, d7);
				tessellator.color(f, f, f);
				tessellator.vertex((double) (i + 1), (double) ((float) (j + 1) + 0.021875F), (double) ((float) (k + 1) - 0.015625F), d5, d6 + 0.0625);
				tessellator.vertex((double) (i + 1), (double) (j + 0), (double) ((float) (k + 1) - 0.015625F), d4, d6 + 0.0625);
				tessellator.vertex((double) (i + 0), (double) (j + 0), (double) ((float) (k + 1) - 0.015625F), d4, d7 + 0.0625);
				tessellator.vertex((double) (i + 0), (double) ((float) (j + 1) + 0.021875F), (double) ((float) (k + 1) - 0.015625F), d5, d7 + 0.0625);
			}
		}

		return true;
	}

	/**
	 * @author Eloraam
	 * @reason implement Forge hooks
	 */
	@Overwrite
	public boolean method_50(Block block, int i, int j, int k, float f, float f1, float f2) {
		this.field_92 = true;
		boolean flag = false;
		float f3 = this.field_93;
		float f4 = this.field_93;
		float f5 = this.field_93;
		float f6 = this.field_93;
		boolean flag1 = true;
		boolean flag2 = true;
		boolean flag3 = true;
		boolean flag4 = true;
		boolean flag5 = true;
		boolean flag6 = true;
		this.field_93 = block.getBrightness(this.blockView, i, j, k);
		this.field_94 = block.getBrightness(this.blockView, i - 1, j, k);
		this.field_95 = block.getBrightness(this.blockView, i, j - 1, k);
		this.field_96 = block.getBrightness(this.blockView, i, j, k - 1);
		this.field_97 = block.getBrightness(this.blockView, i + 1, j, k);
		this.field_98 = block.getBrightness(this.blockView, i, j + 1, k);
		this.field_99 = block.getBrightness(this.blockView, i, j, k + 1);
		this.field_70 = Block.ALLOWS_GRASS_UNDER[this.blockView.getBlockId(i + 1, j + 1, k)];
		this.field_78 = Block.ALLOWS_GRASS_UNDER[this.blockView.getBlockId(i + 1, j - 1, k)];
		this.field_74 = Block.ALLOWS_GRASS_UNDER[this.blockView.getBlockId(i + 1, j, k + 1)];
		this.field_76 = Block.ALLOWS_GRASS_UNDER[this.blockView.getBlockId(i + 1, j, k - 1)];
		this.field_71 = Block.ALLOWS_GRASS_UNDER[this.blockView.getBlockId(i - 1, j + 1, k)];
		this.field_79 = Block.ALLOWS_GRASS_UNDER[this.blockView.getBlockId(i - 1, j - 1, k)];
		this.field_73 = Block.ALLOWS_GRASS_UNDER[this.blockView.getBlockId(i - 1, j, k - 1)];
		this.field_75 = Block.ALLOWS_GRASS_UNDER[this.blockView.getBlockId(i - 1, j, k + 1)];
		this.field_72 = Block.ALLOWS_GRASS_UNDER[this.blockView.getBlockId(i, j + 1, k + 1)];
		this.field_69 = Block.ALLOWS_GRASS_UNDER[this.blockView.getBlockId(i, j + 1, k - 1)];
		this.field_80 = Block.ALLOWS_GRASS_UNDER[this.blockView.getBlockId(i, j - 1, k + 1)];
		this.field_77 = Block.ALLOWS_GRASS_UNDER[this.blockView.getBlockId(i, j - 1, k - 1)];

		if (block.texture == 3) {
			flag6 = false;
			flag5 = false;
			flag4 = false;
			flag3 = false;
			flag1 = false;
		}

		if (this.textureOverride >= 0) {
			flag6 = false;
			flag5 = false;
			flag4 = false;
			flag3 = false;
			flag1 = false;
		}

		if (this.renderAllSides || block.isSideRendered(this.blockView, i, j - 1, k, 0)) {
			float f7;
			float f13;
			float f19;
			float f25;

			if (this.field_55 <= 0) {
				f25 = this.field_95;
				f19 = this.field_95;
				f13 = this.field_95;
				f7 = this.field_95;
			} else {
				this.field_101 = block.getBrightness(this.blockView, i - 1, --j, k);
				this.field_103 = block.getBrightness(this.blockView, i, j, k - 1);
				this.field_104 = block.getBrightness(this.blockView, i, j, k + 1);
				this.field_41 = block.getBrightness(this.blockView, i + 1, j, k);

				if (!this.field_77 && !this.field_79) {
					this.field_100 = this.field_101;
				} else {
					this.field_100 = block.getBrightness(this.blockView, i - 1, j, k - 1);
				}

				if (!this.field_80 && !this.field_79) {
					this.field_102 = this.field_101;
				} else {
					this.field_102 = block.getBrightness(this.blockView, i - 1, j, k + 1);
				}

				if (!this.field_77 && !this.field_78) {
					this.field_105 = this.field_41;
				} else {
					this.field_105 = block.getBrightness(this.blockView, i + 1, j, k - 1);
				}

				if (!this.field_80 && !this.field_78) {
					this.field_42 = this.field_41;
				} else {
					this.field_42 = block.getBrightness(this.blockView, i + 1, j, k + 1);
				}

				++j;
				f7 = (this.field_102 + this.field_101 + this.field_104 + this.field_95) / 4.0F;
				f25 = (this.field_104 + this.field_95 + this.field_42 + this.field_41) / 4.0F;
				f19 = (this.field_95 + this.field_103 + this.field_41 + this.field_105) / 4.0F;
				f13 = (this.field_101 + this.field_100 + this.field_95 + this.field_103) / 4.0F;
			}

			this.field_56 = this.field_57 = this.field_58 = this.field_59 = (flag1 ? f : 1.0F) * 0.5F;
			this.field_60 = this.field_61 = this.field_62 = this.field_63 = (flag1 ? f1 : 1.0F) * 0.5F;
			this.field_64 = this.field_65 = this.field_66 = this.field_68 = (flag1 ? f2 : 1.0F) * 0.5F;
			this.field_56 *= f7;
			this.field_60 *= f7;
			this.field_64 *= f7;
			this.field_57 *= f13;
			this.field_61 *= f13;
			this.field_65 *= f13;
			this.field_58 *= f19;
			this.field_62 *= f19;
			this.field_66 *= f19;
			this.field_59 *= f25;
			this.field_63 *= f25;
			this.field_68 *= f25;
			this.renderBottomFace(block, (double) i, (double) j, (double) k, block.getTextureForSide(this.blockView, i, j, k, 0));
			flag = true;
		}

		if (this.renderAllSides || block.isSideRendered(this.blockView, i, j + 1, k, 1)) {
			float f8;
			float f14;
			float f20;
			float f26;

			if (this.field_55 <= 0) {
				f26 = this.field_98;
				f20 = this.field_98;
				f14 = this.field_98;
				f8 = this.field_98;
			} else {
				this.field_44 = block.getBrightness(this.blockView, i - 1, ++j, k);
				this.field_48 = block.getBrightness(this.blockView, i + 1, j, k);
				this.field_46 = block.getBrightness(this.blockView, i, j, k - 1);
				this.field_49 = block.getBrightness(this.blockView, i, j, k + 1);

				if (!this.field_69 && !this.field_71) {
					this.field_43 = this.field_44;
				} else {
					this.field_43 = block.getBrightness(this.blockView, i - 1, j, k - 1);
				}

				if (!this.field_69 && !this.field_70) {
					this.field_47 = this.field_48;
				} else {
					this.field_47 = block.getBrightness(this.blockView, i + 1, j, k - 1);
				}

				if (!this.field_72 && !this.field_71) {
					this.field_45 = this.field_44;
				} else {
					this.field_45 = block.getBrightness(this.blockView, i - 1, j, k + 1);
				}

				if (!this.field_72 && !this.field_70) {
					this.field_50 = this.field_48;
				} else {
					this.field_50 = block.getBrightness(this.blockView, i + 1, j, k + 1);
				}

				--j;
				f26 = (this.field_45 + this.field_44 + this.field_49 + this.field_98) / 4.0F;
				f8 = (this.field_49 + this.field_98 + this.field_50 + this.field_48) / 4.0F;
				f14 = (this.field_98 + this.field_46 + this.field_48 + this.field_47) / 4.0F;
				f20 = (this.field_44 + this.field_43 + this.field_98 + this.field_46) / 4.0F;
			}

			this.field_56 = this.field_57 = this.field_58 = this.field_59 = flag2 ? f : 1.0F;
			this.field_60 = this.field_61 = this.field_62 = this.field_63 = flag2 ? f1 : 1.0F;
			this.field_64 = this.field_65 = this.field_66 = this.field_68 = flag2 ? f2 : 1.0F;
			this.field_56 *= f8;
			this.field_60 *= f8;
			this.field_64 *= f8;
			this.field_57 *= f14;
			this.field_61 *= f14;
			this.field_65 *= f14;
			this.field_58 *= f20;
			this.field_62 *= f20;
			this.field_66 *= f20;
			this.field_59 *= f26;
			this.field_63 *= f26;
			this.field_68 *= f26;
			int l = block.getTextureForSide(this.blockView, i, j, k, 1);
			this.renderTopFace(block, (double) i, (double) j, (double) k, l);
			flag = true;
		}

		if (this.renderAllSides || block.isSideRendered(this.blockView, i, j, k - 1, 2)) {
			float f9;
			float f15;
			float f21;
			float f27;

			if (this.field_55 <= 0) {
				f27 = this.field_96;
				f21 = this.field_96;
				f15 = this.field_96;
				f9 = this.field_96;
			} else {
				this.field_51 = block.getBrightness(this.blockView, i - 1, j, --k);
				this.field_103 = block.getBrightness(this.blockView, i, j - 1, k);
				this.field_46 = block.getBrightness(this.blockView, i, j + 1, k);
				this.field_52 = block.getBrightness(this.blockView, i + 1, j, k);

				if (!this.field_73 && !this.field_77) {
					this.field_100 = this.field_51;
				} else {
					this.field_100 = block.getBrightness(this.blockView, i - 1, j - 1, k);
				}

				if (!this.field_73 && !this.field_69) {
					this.field_43 = this.field_51;
				} else {
					this.field_43 = block.getBrightness(this.blockView, i - 1, j + 1, k);
				}

				if (!this.field_76 && !this.field_77) {
					this.field_105 = this.field_52;
				} else {
					this.field_105 = block.getBrightness(this.blockView, i + 1, j - 1, k);
				}

				if (!this.field_76 && !this.field_69) {
					this.field_47 = this.field_52;
				} else {
					this.field_47 = block.getBrightness(this.blockView, i + 1, j + 1, k);
				}

				++k;
				f9 = (this.field_51 + this.field_43 + this.field_96 + this.field_46) / 4.0F;
				f15 = (this.field_96 + this.field_46 + this.field_52 + this.field_47) / 4.0F;
				f21 = (this.field_103 + this.field_96 + this.field_105 + this.field_52) / 4.0F;
				f27 = (this.field_100 + this.field_51 + this.field_103 + this.field_96) / 4.0F;
			}

			this.field_56 = this.field_57 = this.field_58 = this.field_59 = (flag3 ? f : 1.0F) * 0.8F;
			this.field_60 = this.field_61 = this.field_62 = this.field_63 = (flag3 ? f1 : 1.0F) * 0.8F;
			this.field_64 = this.field_65 = this.field_66 = this.field_68 = (flag3 ? f2 : 1.0F) * 0.8F;
			this.field_56 *= f9;
			this.field_60 *= f9;
			this.field_64 *= f9;
			this.field_57 *= f15;
			this.field_61 *= f15;
			this.field_65 *= f15;
			this.field_58 *= f21;
			this.field_62 *= f21;
			this.field_66 *= f21;
			this.field_59 *= f27;
			this.field_63 *= f27;
			this.field_68 *= f27;
			int i1 = block.getTextureForSide(this.blockView, i, j, k, 2);
			this.renderEastFace(block, (double) i, (double) j, (double) k, i1);

			if (((ForgeTessellator) Tessellator.INSTANCE).defaultTexture() && ForgeClientReflection.BlockRenderer$cfgGrassFix && i1 == 3 && this.textureOverride < 0) {
				this.field_56 *= f;
				this.field_57 *= f;
				this.field_58 *= f;
				this.field_59 *= f;
				this.field_60 *= f1;
				this.field_61 *= f1;
				this.field_62 *= f1;
				this.field_63 *= f1;
				this.field_64 *= f2;
				this.field_65 *= f2;
				this.field_66 *= f2;
				this.field_68 *= f2;
				this.renderEastFace(block, (double) i, (double) j, (double) k, 38);
			}

			flag = true;
		}

		if (this.renderAllSides || block.isSideRendered(this.blockView, i, j, k + 1, 3)) {
			float f10;
			float f16;
			float f22;
			float f28;

			if (this.field_55 <= 0) {
				f28 = this.field_99;
				f22 = this.field_99;
				f16 = this.field_99;
				f10 = this.field_99;
			} else {
				this.field_53 = block.getBrightness(this.blockView, i - 1, j, ++k);
				this.field_54 = block.getBrightness(this.blockView, i + 1, j, k);
				this.field_104 = block.getBrightness(this.blockView, i, j - 1, k);
				this.field_49 = block.getBrightness(this.blockView, i, j + 1, k);

				if (!this.field_75 && !this.field_80) {
					this.field_102 = this.field_53;
				} else {
					this.field_102 = block.getBrightness(this.blockView, i - 1, j - 1, k);
				}

				if (!this.field_75 && !this.field_72) {
					this.field_45 = this.field_53;
				} else {
					this.field_45 = block.getBrightness(this.blockView, i - 1, j + 1, k);
				}

				if (!this.field_74 && !this.field_80) {
					this.field_42 = this.field_54;
				} else {
					this.field_42 = block.getBrightness(this.blockView, i + 1, j - 1, k);
				}

				if (!this.field_74 && !this.field_72) {
					this.field_50 = this.field_54;
				} else {
					this.field_50 = block.getBrightness(this.blockView, i + 1, j + 1, k);
				}

				--k;
				f10 = (this.field_53 + this.field_45 + this.field_99 + this.field_49) / 4.0F;
				f28 = (this.field_99 + this.field_49 + this.field_54 + this.field_50) / 4.0F;
				f22 = (this.field_104 + this.field_99 + this.field_42 + this.field_54) / 4.0F;
				f16 = (this.field_102 + this.field_53 + this.field_104 + this.field_99) / 4.0F;
			}

			this.field_56 = this.field_57 = this.field_58 = this.field_59 = (flag4 ? f : 1.0F) * 0.8F;
			this.field_60 = this.field_61 = this.field_62 = this.field_63 = (flag4 ? f1 : 1.0F) * 0.8F;
			this.field_64 = this.field_65 = this.field_66 = this.field_68 = (flag4 ? f2 : 1.0F) * 0.8F;
			this.field_56 *= f10;
			this.field_60 *= f10;
			this.field_64 *= f10;
			this.field_57 *= f16;
			this.field_61 *= f16;
			this.field_65 *= f16;
			this.field_58 *= f22;
			this.field_62 *= f22;
			this.field_66 *= f22;
			this.field_59 *= f28;
			this.field_63 *= f28;
			this.field_68 *= f28;
			int j1 = block.getTextureForSide(this.blockView, i, j, k, 3);
			this.renderWestFace(block, (double) i, (double) j, (double) k, block.getTextureForSide(this.blockView, i, j, k, 3));

			if (((ForgeTessellator) Tessellator.INSTANCE).defaultTexture() && ForgeClientReflection.BlockRenderer$cfgGrassFix && j1 == 3 && this.textureOverride < 0) {
				this.field_56 *= f;
				this.field_57 *= f;
				this.field_58 *= f;
				this.field_59 *= f;
				this.field_60 *= f1;
				this.field_61 *= f1;
				this.field_62 *= f1;
				this.field_63 *= f1;
				this.field_64 *= f2;
				this.field_65 *= f2;
				this.field_66 *= f2;
				this.field_68 *= f2;
				this.renderWestFace(block, (double) i, (double) j, (double) k, 38);
			}

			flag = true;
		}

		if (this.renderAllSides || block.isSideRendered(this.blockView, i - 1, j, k, 4)) {
			float f11;
			float f17;
			float f23;
			float f29;

			if (this.field_55 <= 0) {
				f29 = this.field_94;
				f23 = this.field_94;
				f17 = this.field_94;
				f11 = this.field_94;
			} else {
				this.field_101 = block.getBrightness(this.blockView, --i, j - 1, k);
				this.field_51 = block.getBrightness(this.blockView, i, j, k - 1);
				this.field_53 = block.getBrightness(this.blockView, i, j, k + 1);
				this.field_44 = block.getBrightness(this.blockView, i, j + 1, k);

				if (!this.field_73 && !this.field_79) {
					this.field_100 = this.field_51;
				} else {
					this.field_100 = block.getBrightness(this.blockView, i, j - 1, k - 1);
				}

				if (!this.field_75 && !this.field_79) {
					this.field_102 = this.field_53;
				} else {
					this.field_102 = block.getBrightness(this.blockView, i, j - 1, k + 1);
				}

				if (!this.field_73 && !this.field_71) {
					this.field_43 = this.field_51;
				} else {
					this.field_43 = block.getBrightness(this.blockView, i, j + 1, k - 1);
				}

				if (!this.field_75 && !this.field_71) {
					this.field_45 = this.field_53;
				} else {
					this.field_45 = block.getBrightness(this.blockView, i, j + 1, k + 1);
				}

				++i;
				f29 = (this.field_101 + this.field_102 + this.field_94 + this.field_53) / 4.0F;
				f11 = (this.field_94 + this.field_53 + this.field_44 + this.field_45) / 4.0F;
				f17 = (this.field_51 + this.field_94 + this.field_43 + this.field_44) / 4.0F;
				f23 = (this.field_100 + this.field_101 + this.field_51 + this.field_94) / 4.0F;
			}

			this.field_56 = this.field_57 = this.field_58 = this.field_59 = (flag5 ? f : 1.0F) * 0.6F;
			this.field_60 = this.field_61 = this.field_62 = this.field_63 = (flag5 ? f1 : 1.0F) * 0.6F;
			this.field_64 = this.field_65 = this.field_66 = this.field_68 = (flag5 ? f2 : 1.0F) * 0.6F;
			this.field_56 *= f11;
			this.field_60 *= f11;
			this.field_64 *= f11;
			this.field_57 *= f17;
			this.field_61 *= f17;
			this.field_65 *= f17;
			this.field_58 *= f23;
			this.field_62 *= f23;
			this.field_66 *= f23;
			this.field_59 *= f29;
			this.field_63 *= f29;
			this.field_68 *= f29;
			int k1 = block.getTextureForSide(this.blockView, i, j, k, 4);
			this.renderNorthFace(block, (double) i, (double) j, (double) k, k1);

			if (((ForgeTessellator) Tessellator.INSTANCE).defaultTexture() && ForgeClientReflection.BlockRenderer$cfgGrassFix && k1 == 3 && this.textureOverride < 0) {
				this.field_56 *= f;
				this.field_57 *= f;
				this.field_58 *= f;
				this.field_59 *= f;
				this.field_60 *= f1;
				this.field_61 *= f1;
				this.field_62 *= f1;
				this.field_63 *= f1;
				this.field_64 *= f2;
				this.field_65 *= f2;
				this.field_66 *= f2;
				this.field_68 *= f2;
				this.renderNorthFace(block, (double) i, (double) j, (double) k, 38);
			}

			flag = true;
		}

		if (this.renderAllSides || block.isSideRendered(this.blockView, i + 1, j, k, 5)) {
			float f12;
			float f18;
			float f24;
			float f30;

			if (this.field_55 <= 0) {
				f30 = this.field_97;
				f24 = this.field_97;
				f18 = this.field_97;
				f12 = this.field_97;
			} else {
				this.field_41 = block.getBrightness(this.blockView, ++i, j - 1, k);
				this.field_52 = block.getBrightness(this.blockView, i, j, k - 1);
				this.field_54 = block.getBrightness(this.blockView, i, j, k + 1);
				this.field_48 = block.getBrightness(this.blockView, i, j + 1, k);

				if (!this.field_78 && !this.field_76) {
					this.field_105 = this.field_52;
				} else {
					this.field_105 = block.getBrightness(this.blockView, i, j - 1, k - 1);
				}

				if (!this.field_78 && !this.field_74) {
					this.field_42 = this.field_54;
				} else {
					this.field_42 = block.getBrightness(this.blockView, i, j - 1, k + 1);
				}

				if (!this.field_70 && !this.field_76) {
					this.field_47 = this.field_52;
				} else {
					this.field_47 = block.getBrightness(this.blockView, i, j + 1, k - 1);
				}

				if (!this.field_70 && !this.field_74) {
					this.field_50 = this.field_54;
				} else {
					this.field_50 = block.getBrightness(this.blockView, i, j + 1, k + 1);
				}

				--i;
				f12 = (this.field_41 + this.field_42 + this.field_97 + this.field_54) / 4.0F;
				f30 = (this.field_97 + this.field_54 + this.field_48 + this.field_50) / 4.0F;
				f24 = (this.field_52 + this.field_97 + this.field_47 + this.field_48) / 4.0F;
				f18 = (this.field_105 + this.field_41 + this.field_52 + this.field_97) / 4.0F;
			}

			this.field_56 = this.field_57 = this.field_58 = this.field_59 = (flag6 ? f : 1.0F) * 0.6F;
			this.field_60 = this.field_61 = this.field_62 = this.field_63 = (flag6 ? f1 : 1.0F) * 0.6F;
			this.field_64 = this.field_65 = this.field_66 = this.field_68 = (flag6 ? f2 : 1.0F) * 0.6F;
			this.field_56 *= f12;
			this.field_60 *= f12;
			this.field_64 *= f12;
			this.field_57 *= f18;
			this.field_61 *= f18;
			this.field_65 *= f18;
			this.field_58 *= f24;
			this.field_62 *= f24;
			this.field_66 *= f24;
			this.field_59 *= f30;
			this.field_63 *= f30;
			this.field_68 *= f30;
			int l1 = block.getTextureForSide(this.blockView, i, j, k, 5);
			this.renderSouthFace(block, (double) i, (double) j, (double) k, l1);

			if (((ForgeTessellator) Tessellator.INSTANCE).defaultTexture() && ForgeClientReflection.BlockRenderer$cfgGrassFix && l1 == 3 && this.textureOverride < 0) {
				this.field_56 *= f;
				this.field_57 *= f;
				this.field_58 *= f;
				this.field_59 *= f;
				this.field_60 *= f1;
				this.field_61 *= f1;
				this.field_62 *= f1;
				this.field_63 *= f1;
				this.field_64 *= f2;
				this.field_65 *= f2;
				this.field_66 *= f2;
				this.field_68 *= f2;
				this.renderSouthFace(block, (double) i, (double) j, (double) k, 38);
			}

			flag = true;
		}

		this.field_92 = false;
		return flag;
	}

	/**
	 * @author Eloraam
	 * @reason implement Forge hooks
	 */
	@Overwrite
	public boolean method_58(Block block, int i, int j, int k, float f, float f1, float f2) {
		this.field_92 = false;
		Tessellator tessellator = Tessellator.INSTANCE;
		boolean flag = false;
		float f3 = 0.5F;
		float f4 = 1.0F;
		float f5 = 0.8F;
		float f6 = 0.6F;
		float f7 = f4 * f;
		float f8 = f4 * f1;
		float f9 = f4 * f2;
		float f10 = f3;
		float f11 = f5;
		float f12 = f6;
		float f13 = f3;
		float f14 = f5;
		float f15 = f6;
		float f16 = f3;
		float f17 = f5;
		float f18 = f6;

		if (block != Block.GRASS) {
			f10 = f3 * f;
			f11 = f5 * f;
			f12 = f6 * f;
			f13 = f3 * f1;
			f14 = f5 * f1;
			f15 = f6 * f1;
			f16 = f3 * f2;
			f17 = f5 * f2;
			f18 = f6 * f2;
		}

		float f19 = block.getBrightness(this.blockView, i, j, k);

		if (this.renderAllSides || block.isSideRendered(this.blockView, i, j - 1, k, 0)) {
			float f20 = block.getBrightness(this.blockView, i, j - 1, k);
			tessellator.color(f10 * f20, f13 * f20, f16 * f20);
			this.renderBottomFace(block, (double) i, (double) j, (double) k, block.getTextureForSide(this.blockView, i, j, k, 0));
			flag = true;
		}

		if (this.renderAllSides || block.isSideRendered(this.blockView, i, j + 1, k, 1)) {
			float f21 = block.getBrightness(this.blockView, i, j + 1, k);

			if (block.maxY != 1.0 && !block.material.isLiquid()) {
				f21 = f19;
			}

			tessellator.color(f7 * f21, f8 * f21, f9 * f21);
			this.renderTopFace(block, (double) i, (double) j, (double) k, block.getTextureForSide(this.blockView, i, j, k, 1));
			flag = true;
		}

		if (this.renderAllSides || block.isSideRendered(this.blockView, i, j, k - 1, 2)) {
			float f22 = block.getBrightness(this.blockView, i, j, k - 1);

			if (block.minZ > 0.0) {
				f22 = f19;
			}

			tessellator.color(f11 * f22, f14 * f22, f17 * f22);
			int l = block.getTextureForSide(this.blockView, i, j, k, 2);
			this.renderEastFace(block, (double) i, (double) j, (double) k, l);

			if (((ForgeTessellator) Tessellator.INSTANCE).defaultTexture() && ForgeClientReflection.BlockRenderer$cfgGrassFix && l == 3 && this.textureOverride < 0) {
				tessellator.color(f11 * f22 * f, f14 * f22 * f1, f17 * f22 * f2);
				this.renderEastFace(block, (double) i, (double) j, (double) k, 38);
			}

			flag = true;
		}

		if (this.renderAllSides || block.isSideRendered(this.blockView, i, j, k + 1, 3)) {
			float f23 = block.getBrightness(this.blockView, i, j, k + 1);

			if (block.maxZ < 1.0) {
				f23 = f19;
			}

			tessellator.color(f11 * f23, f14 * f23, f17 * f23);
			int i1 = block.getTextureForSide(this.blockView, i, j, k, 3);
			this.renderWestFace(block, (double) i, (double) j, (double) k, i1);

			if (((ForgeTessellator) Tessellator.INSTANCE).defaultTexture() && ForgeClientReflection.BlockRenderer$cfgGrassFix && i1 == 3 && this.textureOverride < 0) {
				tessellator.color(f11 * f23 * f, f14 * f23 * f1, f17 * f23 * f2);
				this.renderWestFace(block, (double) i, (double) j, (double) k, 38);
			}

			flag = true;
		}

		if (this.renderAllSides || block.isSideRendered(this.blockView, i - 1, j, k, 4)) {
			float f24 = block.getBrightness(this.blockView, i - 1, j, k);

			if (block.minX > 0.0) {
				f24 = f19;
			}

			tessellator.color(f12 * f24, f15 * f24, f18 * f24);
			int j1 = block.getTextureForSide(this.blockView, i, j, k, 4);
			this.renderNorthFace(block, (double) i, (double) j, (double) k, j1);

			if (((ForgeTessellator) Tessellator.INSTANCE).defaultTexture() && ForgeClientReflection.BlockRenderer$cfgGrassFix && j1 == 3 && this.textureOverride < 0) {
				tessellator.color(f12 * f24 * f, f15 * f24 * f1, f18 * f24 * f2);
				this.renderNorthFace(block, (double) i, (double) j, (double) k, 38);
			}

			flag = true;
		}

		if (this.renderAllSides || block.isSideRendered(this.blockView, i + 1, j, k, 5)) {
			float f25 = block.getBrightness(this.blockView, i + 1, j, k);

			if (block.maxX < 1.0) {
				f25 = f19;
			}

			tessellator.color(f12 * f25, f15 * f25, f18 * f25);
			int k1 = block.getTextureForSide(this.blockView, i, j, k, 5);
			this.renderSouthFace(block, (double) i, (double) j, (double) k, k1);

			if (((ForgeTessellator) Tessellator.INSTANCE).defaultTexture() && ForgeClientReflection.BlockRenderer$cfgGrassFix && k1 == 3 && this.textureOverride < 0) {
				tessellator.color(f12 * f25 * f, f15 * f25 * f1, f18 * f25 * f2);
				this.renderSouthFace(block, (double) i, (double) j, (double) k, 38);
			}

			flag = true;
		}

		return flag;
	}

	/**
	 * @author Eloraam
	 * @reason implement Forge hooks
	 */
	@Inject(method = "method_48", at = @At("RETURN"))
	private void forge$method_48(Block block, int i, float f, CallbackInfo ci) {
		int k = block.getRenderType();

		if (k != 0 && k != 16) {
			switch (k) {
				case 1:
				case 2:
				case 6:
				case 10:
				case 11:
				case 13:
					break;
				default:
					ModLoader.RenderInvBlock((BlockRenderer) (Object) this, block, i, k);
			}
		}
	}

	/**
	 * @author Eloraam
	 * @reason implement Forge hooks
	 */
	@Inject(method = "method_42", at = @At("RETURN"), cancellable = true)
	private static void forge$method_42(int i, CallbackInfoReturnable<Boolean> cir) {
		switch (i) {
			case 0:
			case 10:
			case 11:
			case 13:
				break;
			default:
				cir.setReturnValue(ModLoader.RenderBlockIsItemFull3D(i));
		}
	}

	/**
	 * @author Eloraam
	 * @reason implement Forge hooks
	 */
	@Inject(method = "<clinit>", at = @At("RETURN"))
	private static void forge$cinit(CallbackInfo ci) {
		for (int i = 0; i < ForgeClientReflection.BlockRenderer$redstoneColors.length; ++i) {
			float f = (float) i / 15.0F;
			float f1 = f * 0.6F + 0.4F;

			if (i == 0) {
				f = 0.0F;
			}

			float f2 = f * f * 0.7F - 0.5F;
			float f3 = f * f * 0.6F - 0.7F;

			if (f2 < 0.0F) {
				f2 = 0.0F;
			}

			if (f3 < 0.0F) {
				f3 = 0.0F;
			}

			ForgeClientReflection.BlockRenderer$redstoneColors[i] = new float[] {f1, f2, f3};
		}
	}
}
