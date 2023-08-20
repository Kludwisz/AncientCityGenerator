package kludwisz.ancientcity;

import com.seedfinding.mccore.block.Block;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.util.block.BlockBox;
import com.seedfinding.mccore.util.block.BlockDirection;
import com.seedfinding.mccore.util.block.BlockMirror;
import com.seedfinding.mccore.util.block.BlockRotation;
import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mccore.util.data.Quad;
import com.seedfinding.mccore.util.data.Triplet;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.LootTable;

import kludwisz.ancientcity.jigsawpools.AncientCityJigsawBlocks;
import kludwisz.ancientcity.jigsawpools.AncientCityPools;
import kludwisz.ancientcity.jigsawpools.PoolType;
import kludwisz.ancientcity.structures.AncientCityStructureLoot;
import kludwisz.ancientcity.structures.AncientCityStructureSize;
import kludwisz.util.DecoratorRand;
import kludwisz.util.Shuffler;
import kludwisz.util.VoxelShape;

import java.util.*;

// ancient city generator by Kludwisz
// huge thanks to profotoce59 for the original code: 
// https://github.com/profotoce59/VillageGenerator

public class AncientCityGenerator {
    public List<Piece> pieces;
    private static final int MAX_DIST = 116; // max distance from city anchor
    private long worldseed;
    
    public AncientCityGenerator() {}
    
    public boolean generate(long worldseed, int chunkX, int chunkZ, ChunkRand rand) {
    	this.worldseed = worldseed;
        pieces = new ArrayList<>();

        // choose random starting template and rotation
        rand.setCarverSeed(this.worldseed, chunkX, chunkZ, MCVersion.v1_19); // version doesnt matter
        BlockRotation rotation = BlockRotation.getRandom(rand);
        String template = rand.getRandom(START_POOL.getTemplates());
        BPos size = AncientCityStructureSize.STRUCTURE_SIZE.get(template);
        
        // STARTING JIGSAW:
        // For ancient cities, there is only 1 starting jigsaw at the same position in all structure starts, so we can use
        // this shortened version of getRandomNamedJigsaw() that uses precalculated values
        BPos invertedStartJigsawPos = new BPos(-13, -24, -20).transform(BlockMirror.NONE, rotation, BPos.ORIGIN);
        for (int i = 5; i > 1; --i)
        	rand.nextInt(i);

        // moving startPos by the relative position of ancient city anchor to get correct bounding boxes
        BPos startPos = new CPos(chunkX, chunkZ).toBlockPos(-27).add(invertedStartJigsawPos);
        BlockBox box = BlockBox.getBoundingBox(startPos, rotation, BPos.ORIGIN, BlockMirror.NONE, size);
        int centerX = (box.minX + box.maxX) / 2;
        int centerZ = (box.minZ + box.maxZ) / 2;
        int heightY = 1; // this just offsets all the pieces 1 block up
        int y = startPos.getY() + heightY;
        int centerY = box.minY + 1;

        // create the first piece
        Piece piece = new Piece(template, startPos, box, rotation,0);
        piece.move(0, y - centerY, 0);
        piece.setBoundsTop(y + MAX_DIST);
        
        // create structure max bounding box
        BlockBox fullBox = new BlockBox(centerX - MAX_DIST, y - MAX_DIST, centerZ - MAX_DIST, centerX + MAX_DIST + 1, y + MAX_DIST + 1, centerZ + MAX_DIST + 1);
        Assembler assembler = new Assembler(6, this.pieces, y);
        assembler.pieces.add(piece);
        VoxelShape a = new VoxelShape(fullBox);
        a.fullBoxes.add(new BlockBox(box.minX,box.minY,box.minZ,box.maxX+1,box.maxY+1,box.maxZ+1));
        piece.voxelShape = a;
        
        // place pieces
        assembler.placing.addLast(piece);
        while(!assembler.placing.isEmpty()) {
            assembler.tryPlacing(assembler.placing.removeFirst(), rand);
        }
        
        return true;
    }

    static public class Piece {
        String name;
        public BPos pos;
        BlockBox box;
        public BlockRotation rotation;
        int boundsTop;
        private VoxelShape voxelShape;
        int depth;

        public String getName(){
            return this.name;
        }
        
        Piece(String name, BPos pos, BlockBox box, BlockRotation rotation, int depth) {
            this.name = name;
            this.pos = pos;
            this.box = box;
            this.rotation = rotation;
            this.voxelShape = new VoxelShape(box);
            this.depth = depth;
        }

        public void move(int x, int y, int z) {
            box.move(x, y, z);
            pos = pos.add(x, y, z);
        }

        public void setBoundsTop(int boundsTop) {
            this.boundsTop = boundsTop;
        }

        public List<BlockJigsawInfo> getShuffledJigsawBlocks(BPos offset, ChunkRand rand) {//taking 20% need to opti
            List<Pair<Quad<PoolType, Pair<String,String>, Pair<BlockDirection,BlockDirection>, Block>, BPos>> blocks = AncientCityJigsawBlocks.JIGSAW_BLOCKS.get(this.name);
            List<BlockJigsawInfo> list = new ArrayList<>(blocks.size());
            
            for (Pair<Quad<PoolType, Pair<String,String>, Pair<BlockDirection, BlockDirection>, Block>, BPos> b : blocks) {

                BlockJigsawInfo blockJigsawInfo = new BlockJigsawInfo(b.getFirst()
                        , rotation.rotate(b.getSecond(), new BPos(0,0,0)).add(offset), rotation );
                list.add(blockJigsawInfo);
            }
            Shuffler.shuffle(list, rand);
            return list;
        }
        
        public void setVoxelShape(VoxelShape mutableobject1) {
            this.voxelShape = mutableobject1;
        }
        public VoxelShape getVoxelShape() {
            return this.voxelShape;
        }
    }

    public static class BlockJigsawInfo {
        Quad<PoolType, Pair<String,String>, Pair<BlockDirection,BlockDirection>, Block> nbt;
        BPos pos;
        BlockRotation rotation;
        public BlockJigsawInfo(Quad<PoolType, Pair<String,String>, Pair<BlockDirection,BlockDirection>, Block> nbt, BPos pos, BlockRotation rotation) {
            // nbt is stored as pool,(name,targetname),orientation,final_state
            this.nbt = nbt;
            this.pos = pos;
            this.rotation = rotation;
        }

        public BlockDirection getFront() {
            return rotation.rotate(this.nbt.getThird().getFirst());
        }
        
        public BlockDirection getOpposite(BlockDirection b){
            switch (b) {
                case NORTH:
                    return BlockDirection.SOUTH;
                case SOUTH:
                    return BlockDirection.NORTH;
                case WEST:
                    return BlockDirection.EAST;
                case EAST:
                    return BlockDirection.WEST;
                case DOWN:
                    return BlockDirection.UP;
                case UP:
                    return BlockDirection.DOWN;
                default:
                    throw new IllegalStateException("Unable to get facing of " );
            }
        }
        public boolean canAttach15(BlockJigsawInfo blockJigsawInfo2, BlockDirection direction) { //1.15 version is faster and seems the same

            return direction == this.getOpposite(blockJigsawInfo2.getFront())
                    && this.nbt.getSecond().getSecond().equals(blockJigsawInfo2.nbt.getSecond().getFirst());

        }
    }

    public static class Assembler {
        int maxDepth;
        List<Piece> pieces;

        private final Deque<Piece> placing = new ArrayDeque<>();
        Assembler(int maxDepth, List<Piece> pieces, int heightY) {
            this.maxDepth = maxDepth;
            this.pieces = pieces;
        }

        public void tryPlacing(Piece piece, ChunkRand rand) {
            int depth = piece.depth;
            BPos pos = piece.pos;
            VoxelShape mutableobject = new VoxelShape();
            BlockBox box = piece.box;
            int minY = box.minY;
            
            label139:

            for (BlockJigsawInfo blockJigsawInfo : piece.getShuffledJigsawBlocks(pos, rand)) {
                BlockDirection blockDirection = blockJigsawInfo.getFront();
                BPos blockPos = blockJigsawInfo.pos;
                BPos relativeBlockPos = new BPos(blockPos.getX() + blockDirection.getVector().getX(),
                        blockPos.getY() + blockDirection.getVector().getY(),
                        blockPos.getZ() + blockDirection.getVector().getZ());
                int y = blockPos.getY() - minY;
                
                Pair<PoolType, List<Pair<String, Integer>> > pool = AncientCityPools.CITY_POOLS.get(blockJigsawInfo.nbt.getFirst());
                
                if (pool != null && pool.getSecond().size() != 0) {
                    PoolType fallbackLocation = pool.getFirst();
                    Pair<PoolType, List<Pair<String, Integer>> > fallbackPool = AncientCityPools.CITY_POOLS.get(fallbackLocation);
                    
                    if (fallbackPool != null && fallbackPool.getSecond().size() != 0) {

                    	JigSawPool jigSawPool1 = new JigSawPool(pool.getSecond());
                        JigSawPool jigSawPool2 = new JigSawPool(fallbackPool.getSecond());
                        boolean isInside = box.contains(relativeBlockPos);
                        VoxelShape mutableobject1;
                        if (isInside) {
                            mutableobject1 = mutableobject;
                            if (mutableobject.isNull()) {
                                mutableobject.setValue(box,true);

                            }
                        } else {
                            mutableobject1 = piece.getVoxelShape();
                        }
                        LinkedList<String> list = new LinkedList<>();
                        
                        if (depth != this.maxDepth) {
                            list = jigSawPool1.getTemplates();
                            if(list.size() != 0) {
                            	Shuffler.shuffle(list, rand);
                            	//rand.advance(1);    not done in newer versions
                            }
                        }
                        LinkedList<String> listtmp = jigSawPool2.getTemplates();
                        if(listtmp.size() != 0) {
                        	Shuffler.shuffle(list, rand);
                        	//rand.advance(1);    not done in newer versions
                        }
                        list.addAll(listtmp);
                        
                        
                        for (String jigsawpiece1 : list) {
                        	
                            if (jigsawpiece1.equals("empty")){
                                break;
                            }
                            
                            List<BlockRotation> shuffledRotations = Shuffler.shuffledCopy(Arrays.asList(BlockRotation.values()), rand);
                            for (BlockRotation rotation1 : shuffledRotations ) {
                                
                                BPos size1 = AncientCityStructureSize.STRUCTURE_SIZE.get(jigsawpiece1);
                                BlockBox box1 = size1==null ? new BlockBox(0,0,0,0,0,0) : BlockBox.getBoundingBox(BPos.ORIGIN, rotation1, BPos.ORIGIN, BlockMirror.NONE, size1);
                                Piece piece1 = new Piece(jigsawpiece1, BPos.ORIGIN, box1, rotation1, 0);
                                List<BlockJigsawInfo> list1;
                                list1 = piece1.getShuffledJigsawBlocks(BPos.ORIGIN, rand);

                                // ancient city doesnt use expansion hack
                                int i1 = 0;

                                BlockDirection direction = blockJigsawInfo.getFront();

                                // Loop to see if we can attach
                                for (BlockJigsawInfo blockJigsawInfo2 : list1) {
                                    if (blockJigsawInfo.canAttach15(blockJigsawInfo2,direction)) {
                                        
                                        BPos blockPos3 = blockJigsawInfo2.pos;
                                        
                                        BPos blockPos4 = new BPos(relativeBlockPos.getX() - blockPos3.getX(),
                                                relativeBlockPos.getY() - blockPos3.getY(), relativeBlockPos.getZ() - blockPos3.getZ());
                                        
                                        //System.out.println(blockJigsawInfo.pos + " -> " + blockPos4);
                                        BlockBox box2;
                                        if(size1 == null){
                                            box2 = new BlockBox(blockPos4.getX(),blockPos4.getY(),blockPos4.getZ(),blockPos4.getX(),
                                                    blockPos4.getY(),blockPos4.getZ());
                                        }
                                        else {
                                            box2 = BlockBox.getBoundingBox(blockPos4, rotation1, BPos.ORIGIN, BlockMirror.NONE, size1);
                                        }
                                        int j1 = box2.minY;
                                        int k1 = blockPos3.getY();
                                        int l1 = y - k1 + blockJigsawInfo.getFront().getVector().getY();
                                        
                                        // all ac pieces are rigid
                                        int i2 = minY + l1;
                                        
                                        int j2 = i2 - j1;
                                        BlockBox box3 = new BlockBox(box2.minX,box2.minY,box2.minZ,box2.maxX,box2.maxY,box2.maxZ);
                                        box3.move(0, j2, 0);
                                        BPos blockpos5 = blockPos4.add(0, j2, 0);
                                        
                                        if (isNotEmpty(mutableobject1,box3)) {
                                            mutableobject1.fullBoxes.add(new BlockBox(box3.minX,box3.minY,box3.minZ,
                                                    box3.maxX+1,box3.maxY+1,box3.maxZ+1));
                                            
                                            Piece piece2 = new Piece(jigsawpiece1,blockpos5,box3,rotation1,depth+1);
                                            if(depth+1<= this.maxDepth){
                                                this.pieces.add(piece2);
                                                piece2.setVoxelShape(mutableobject1);
                                                this.placing.addLast(piece2);
                                                //System.out.println("placed piece: " + piece2.name);
                                            }
                                            continue label139;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        private boolean isNotEmpty(VoxelShape voxelShape,BlockBox box1) {
            if(box1.minX<voxelShape.getX().get(0) || box1.minY<voxelShape.getY().get(0) || box1.minZ<voxelShape.getZ().get(0)
            || box1.maxX>=voxelShape.getLastX() || box1.maxY>=voxelShape.getLastY() || box1.maxZ>=voxelShape.getLastZ())
            	return false;
            
            for (BlockBox fullBox: voxelShape.fullBoxes){
                if(intersects2(box1,fullBox)){
                    return false;
                }
            }
            return true;
        }
        
        public boolean intersects2(BlockBox box1, BlockBox box) {
            return box1.maxX >= box.minX && box1.minX < box.maxX && box1.maxZ >= box.minZ && box1.minZ < box.maxZ && box1.maxY >= box.minY && box1.minY < box.maxY;
        }
    }

    public static class JigSawPool {
        private final LinkedList<String> templates = new LinkedList<>();

        JigSawPool(List<Pair<String, Integer>> templates) {
            for(Pair<String, Integer> template : templates) {
                for(int i = 0; i < template.getSecond(); i++) {
                    this.templates.addLast(template.getFirst());
                }
            }
        }

        public LinkedList<String> getTemplates() {
            return templates;
        }
    }

    public static final JigSawPool START_POOL = new JigSawPool(AncientCityPools.CITY_POOLS.get(PoolType.CITY_CENTER).getSecond());

    
    // -------------------------
    // SEEDFINDING UTILS
    // -------------------------
    
    public List<Piece> getPieces() {
        return this.pieces;
    }
    
    public List<Pair<BPos, LootTable>> getChests() {
    	ArrayList<Pair<BPos, LootTable>> result = new ArrayList<>();
    	
    	for (Piece p : this.pieces) {
    		List<Pair<BPos, LootTable>> chests = AncientCityStructureLoot.STRUCTURE_LOOT.get(p.getName());
    		if (chests==null || chests.size() == 0) 
    			continue;
    		
    		for (Pair<BPos, LootTable> chest : chests) {
    			BPos rotatedOffset = chest.getFirst().transform(BlockMirror.NONE, p.rotation, BPos.ORIGIN).add(new BPos(0,-1,0));
    			BPos realChestPos = p.pos.add(rotatedOffset);
    			result.add(new Pair<>(realChestPos, chest.getSecond()));
    		}
    	}
    	
    	return result;
    }
    
    // TODO test this thoroughly
    public List<Triplet<BPos, LootTable, Long>> getChestsWithLootSeeds() {
    	ArrayList<Triplet<BPos, LootTable, Long>> result = new ArrayList<>();
    	HashMap<CPos, DecoratorRand> chunkRandoms = new HashMap<>();
    	//HashSet<CPos> compromisedChunks = new HashSet<>();
    	
    	for (Piece p : this.pieces) {
    		//if (p.getName().equals("sculk_patch")) {
    		//	compromisedChunks.add(p.pos.toChunkPos());
    		//	continue;
    		//}
    		
    		List<Pair<BPos, LootTable>> chests = AncientCityStructureLoot.STRUCTURE_LOOT.get(p.getName());
    		if (chests==null || chests.size() == 0) 
    			continue;	 

    		for (Pair<BPos, LootTable> chest : chests) {
    			BPos rotatedOffset = chest.getFirst().transform(BlockMirror.NONE, p.rotation, BPos.ORIGIN).add(new BPos(0,-1,0));
    			BPos realChestPos = p.pos.add(rotatedOffset);
    			CPos chestChunkPos = realChestPos.toChunkPos();
    			//if (compromisedChunks.contains(chestChunkPos))
    			//	continue;
    			
    			if (!chunkRandoms.containsKey(chestChunkPos)) {
    				DecoratorRand rand = new DecoratorRand();
    				long popseed = rand.getPopulationSeed(this.worldseed, chestChunkPos.getX()<<4, chestChunkPos.getZ()<<4);
    				rand.setDecoratorSeed(popseed, 0, 7);
    				chunkRandoms.put(chestChunkPos, rand);
    			} 

    			long lootseed = chunkRandoms.get(chestChunkPos).nextLong();
				result.add(new Triplet<>(realChestPos, chest.getSecond(), lootseed));
    		}
    	}
    	
    	return result;
    }
    
    public List<Triplet<CPos, LootTable, Integer>> getChestChunksWithRandCalls() {
    	ArrayList<Triplet<CPos, LootTable, Integer>> result = new ArrayList<>();
    	HashMap<CPos, Integer> callMap = new HashMap<>();
    	
    	for (Piece p : this.pieces) {
    		List<Pair<BPos, LootTable>> chests = AncientCityStructureLoot.STRUCTURE_LOOT.get(p.getName());
    		if (chests==null || chests.size() == 0) 
    			continue;	 

    		for (Pair<BPos, LootTable> chest : chests) {
    			BPos rotatedOffset = chest.getFirst().transform(BlockMirror.NONE, p.rotation, BPos.ORIGIN);
    			BPos realChestPos = p.pos.add(rotatedOffset);
    			CPos chestChunkPos = realChestPos.toChunkPos();
    			
    			if (!callMap.containsKey(chestChunkPos)) {
    				callMap.put(chestChunkPos, 0);
    			} 

    			int calls = callMap.get(chestChunkPos);
    			callMap.put(chestChunkPos, calls+1);
				result.add(new Triplet<>(chestChunkPos, chest.getSecond(), calls));
    		}
    	}
    	return result;
    }
}
