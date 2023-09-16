package kludwisz.util;

import com.seedfinding.mccore.util.block.BlockBox;

import java.util.ArrayList;
import java.util.List;

public class VoxelShape {
    public BlockBox outer;
    public List<BlockBox> cutout = new ArrayList<>();

    public VoxelShape(BlockBox box) {
        outer = box;
    }
}
