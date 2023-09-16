package kludwisz.ancientcity.util;

import com.seedfinding.mccore.util.block.BlockBox;
import com.seedfinding.mccore.util.block.BlockDirection;
import com.seedfinding.mccore.util.block.BlockRotation;
import com.seedfinding.mccore.util.pos.BPos;

public class BlockBoxUtil {
    public static void set(BlockBox box, MutableBlockPos pos) {
        box.minX = pos.x;
        box.minY = pos.y;
        box.minZ = pos.z;
        box.maxX = pos.x;
        box.maxY = pos.y;
        box.maxZ = pos.z;
    }

    public static void set(BlockBox box, BlockBox other) {
        box.minX = other.minX;
        box.minY = other.minY;
        box.minZ = other.minZ;
        box.maxX = other.maxX;
        box.maxY = other.maxY;
        box.maxZ = other.maxZ;
    }

    public static void setSizeRotatePos(BlockBox box, BPos size, BlockRotation rotation, MutableBlockPos pos) {
        int sx = size.getX() - 1;
        int sy = size.getY() - 1;
        int sz = size.getZ() - 1;
        if (rotation == BlockRotation.CLOCKWISE_90 || rotation == BlockRotation.COUNTERCLOCKWISE_90) {
            int tmp = sx;
            sx = sz;
            sz = tmp;
        }
        box.minY = pos.y;
        box.maxY = pos.y + sy;
        switch (rotation) {
            case NONE -> {
                box.minX = pos.x;
                box.minZ = pos.z;
                box.maxX = pos.x + sx;
                box.maxZ = pos.z + sz;
            }
            case CLOCKWISE_90 -> {
                box.minX = pos.x - sx;
                box.minZ = pos.z;
                box.maxX = pos.x;
                box.maxZ = pos.z + sz;
            }
            case CLOCKWISE_180 -> {
                box.minX = pos.x - sx;
                box.minZ = pos.z - sz;
                box.maxX = pos.x;
                box.maxZ = pos.z;
            }
            case COUNTERCLOCKWISE_90 -> {
                box.minX = pos.x;
                box.minZ = pos.z - sz;
                box.maxX = pos.x + sx;
                box.maxZ = pos.z;
            }
        }
    }

    public static boolean contains(BlockBox box, MutableBlockPos pos) {
        return pos.x >= box.minX && pos.x <= box.maxX && pos.z >= box.minZ && pos.z <= box.maxZ && pos.y >= box.minY && pos.y <= box.maxY;
    }
}
