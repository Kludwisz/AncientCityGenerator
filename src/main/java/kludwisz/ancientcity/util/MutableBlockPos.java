package kludwisz.ancientcity.util;

import com.seedfinding.mccore.util.block.BlockRotation;
import com.seedfinding.mccore.util.pos.BPos;

public class MutableBlockPos {
    public int x;
    public int y;
    public int z;

    public MutableBlockPos() {

    }

    public void set(BPos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
    }

    public void setRotateOffset(BPos pos, BlockRotation rotation, BPos pivot, BPos offset) {
        int px = pivot.getX();
        int pz = pivot.getZ();
        switch (rotation) {
            case CLOCKWISE_90 -> {
                this.x = px + pz - pos.getZ() + offset.getX();
                this.y = pos.getY() + offset.getY();
                this.z = pz - px + pos.getX() + offset.getZ();
            }
            case CLOCKWISE_180 -> {
                this.x = px + px - pos.getX() + offset.getX();
                this.y = pos.getY() + offset.getY();
                this.z = pz + pz - pos.getZ() + offset.getZ();
            }
            case COUNTERCLOCKWISE_90 -> {
                this.x = px - pz + pos.getZ() + offset.getX();
                this.y = pos.getY() + offset.getY();
                this.z = px + pz - pos.getX() + offset.getZ();
            }
            default -> {
                this.x = pos.getX() + offset.getX();
                this.y = pos.getY() + offset.getY();
                this.z = pos.getZ() + offset.getZ();
            }
        }
    }
}
