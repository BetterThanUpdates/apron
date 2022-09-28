package io.github.betterthanupdates.shockahpi.item;

import shockahpi.BlockHarvestPower;
import shockahpi.Tool;
import shockahpi.ToolBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class ShockAhPIToolItem extends Tool {
	private Block[] field_2712;
	public float field_2713 = Float.NaN;
	public int field_2714 = Integer.MIN_VALUE;
	public ToolMaterial field_2711;

	public ShockAhPIToolItem(int itemID, int damage, ToolMaterial material, Block[] blocks) {
		super(false, null, itemID, material.getDurability(), (float) (damage + material.getAttackDamage()), getToolPower(material), material.getMiningSpeed());
		this.field_2711 = material;
		this.toolBase = this.getToolBase();

		if (blocks != null) {
			for (Block block : blocks) {
				if (block != null) {
					this.mineBlocks.add(new BlockHarvestPower(block.id, 0.0F));
				}
			}
		}
	}

	public ToolBase getToolBase() {
		if (this instanceof ShockAhPIPickaxeItem) {
			return ToolBase.PICKAXE;
		} else if (this instanceof ShockAhPIAxeItem) {
			return ToolBase.AXE;
		} else {
			return this instanceof ShockAhPIShovelItem ? ToolBase.SHOVEL : null;
		}
	}

	public static float getToolPower(ToolMaterial material) {
		if (material == ToolMaterial.DIAMOND) {
			return 80.0F;
		} else if (material == ToolMaterial.IRON) {
			return 60.0F;
		} else {
			return material != ToolMaterial.STONE ? 20.0F : 40.0F;
		}
	}

	@Override
	public boolean canHarvest(Block block) {
		if (!this.usingSAPI && !this.isBlockOnList(block.id)) {
			if (this instanceof ShockAhPIPickaxeItem) {
				if (this.id <= 369) {
					return false;
				}

				if (block.material == Material.STONE || block.material == Material.ICE) {
					return true;
				}

				if (block.material == Material.METAL && this.basePower >= 40.0F) {
					return true;
				}
			} else if (this instanceof ShockAhPIAxeItem) {
				if (this.id <= 369) {
					return false;
				}

				if (block.material == Material.WOOD
						|| block.material == Material.LEAVES
						|| block.material == Material.PLANT
						|| block.material == Material.CACTUS
						|| block.material == Material.PUMPKIN) {
					return true;
				}
			} else if (this instanceof ShockAhPIShovelItem) {
				if (this.id <= 369) {
					return false;
				}

				if (block.material == Material.ORGANIC
						|| block.material == Material.DIRT
						|| block.material == Material.SAND
						|| block.material == Material.SNOW
						|| block.material == Material.SNOW_BLOCK
						|| block.material == Material.CLAY) {
					return true;
				}
			}
		}

		return super.canHarvest(block);
	}

	private boolean isBlockOnList(int blockID) {
		for (BlockHarvestPower power : this.mineBlocks) {
			if (power.blockID == blockID) {
				return true;
			}
		}

		if (this.toolBase != null) {
			for (BlockHarvestPower power : this.toolBase.mineBlocks) {
				if (power.blockID == blockID) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public float getStrengthOnBlock(ItemStack stack, Block block) {
		if (this.field_2712 != null) {
			for (Block value : this.field_2712) {
				if (value == block) {
					return this.getToolSpeed();
				}
			}

			return 1.0F;
		} else {
			return super.getStrengthOnBlock(stack, block);
		}
	}

	@Override
	public int getAttackDamage(Entity entity) {
		int i = super.getAttackDamage(entity);
		return this.field_2714 != Integer.MIN_VALUE && (double) i == Math.floor(this.baseDamage) ? this.field_2714 : i;
	}

	@Override
	protected float getToolSpeed() {
		return Float.isNaN(this.field_2713) ? super.getToolSpeed() : this.field_2713;
	}
}
