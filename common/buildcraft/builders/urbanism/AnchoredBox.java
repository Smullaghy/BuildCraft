/**
 * Copyright (c) 2011-2014, SpaceToad and the BuildCraft Team
 * http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package buildcraft.builders.urbanism;

import net.minecraft.nbt.NBTTagCompound;
import buildcraft.core.Box;
import buildcraft.core.network.NetworkData;

public class AnchoredBox {
	@NetworkData
	public Box box = new Box();

	@NetworkData
	public float x1, y1, z1;

	public enum Kind {
		LASER_RED,
		LASER_YELLOW,
		LASER_GREEN,
		LASER_BLUE,
		STRIPES
	}

	@NetworkData
	public Kind kind = Kind.LASER_RED;

	public void setP2 (float x2, float y2, float z2) {
		box.initialize(x1, y1, z1, x2, y2, z2);
	}

	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setByte("kind", (byte) kind.ordinal());
		nbt.setFloat("anchorX", x1);
		nbt.setFloat("anchorY", y1);
		nbt.setFloat("anchorZ", z1);

		box.writeToNBT(nbt);
	}

	public void readFromNBT(NBTTagCompound nbt) {
		kind = Kind.values() [nbt.getShort("kind")];
		x1 = nbt.getFloat("anchorX");
		y1 = nbt.getFloat("anchorY");
		z1 = nbt.getFloat("anchorZ");

		box.initialize(nbt);
	}
}