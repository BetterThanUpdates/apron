/**
 * This software is provided under the terms of the Minecraft Forge Public
 * License v1.1.
 */

package forge;

import net.minecraft.client.render.WorldEventRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;

import io.github.betterthanupdates.Legacy;

@Legacy
public interface IHighlightHandler {
	boolean onBlockHighlight(WorldEventRenderer worldEventRenderer, PlayerEntity player, HitResult hitResult, int i, ItemStack itemStack, float f);
}
