package shockahpi;

import io.github.betterthanupdates.Legacy;
import net.minecraft.world.World;

/**
 * Part of ShockAhPI that allows interception of block placement.
 * @author ShockAh
 */
@Legacy
public interface IInterceptBlockSet {
	@Legacy
	boolean canIntercept(World arg, Loc loc, int i);

	@Legacy
	int intercept(World arg, Loc loc, int i);
}
