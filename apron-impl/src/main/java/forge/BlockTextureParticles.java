/**
 * This software is provided under the terms of the Minecraft Forge Public
 * License v1.1.
 */

package forge;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.entity.particle.ParticleEntity;

import io.github.betterthanupdates.Legacy;

@Legacy
public class BlockTextureParticles {
	public String texture;
	public List<ParticleEntity> effects = new ArrayList<>();

	public BlockTextureParticles() {
	}
}
