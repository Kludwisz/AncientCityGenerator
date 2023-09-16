package kludwisz.ancientcity.util;

import com.seedfinding.mccore.util.block.BlockBox;
import com.seedfinding.mccore.util.block.BlockRotation;
import com.seedfinding.mccore.util.pos.BPos;

public class BlockBoxUtil {
    public static void set(BlockBox box, BlockBox other) {
        box.minX = other.minX;
        box.minY = other.minY;
        box.minZ = other.minZ;
        box.maxX = other.maxX;
        box.maxY = other.maxY;
        box.maxZ = other.maxZ;
    }

    public static void setSizeRotateOffset(BlockBox box, BPos size, BlockRotation rotation) {

    }
}
