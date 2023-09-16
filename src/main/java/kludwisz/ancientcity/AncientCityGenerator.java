package kludwisz.ancientcity;

import java.util.*;

import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.util.block.BlockBox;
import com.seedfinding.mccore.util.block.BlockDirection;
import com.seedfinding.mccore.util.block.BlockMirror;
import com.seedfinding.mccore.util.block.BlockRotation;
import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mccore.util.data.Triplet;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.LootTable;
import com.seedfinding.mcseed.rand.JRand;

import kludwisz.ancientcity.jigsawpools.AncientCityJigsawBlocks;
import kludwisz.ancientcity.jigsawpools.AncientCityPools;
import kludwisz.ancientcity.jigsawpools.JigsawBlock;
import kludwisz.ancientcity.structures.AncientCityPieceNames;
import kludwisz.ancientcity.structures.AncientCityStructureLoot;
import kludwisz.ancientcity.structures.AncientCityStructureSize;
import kludwisz.ancientcity.util.MutableBlockPos;
import kludwisz.util.DecoratorRand;
import kludwisz.util.VoxelShape;

// ancient city generator by Kludwisz
// huge thanks to profotoce59 for the original code: 
// https://github.com/profotoce59/VillageGenerator

public class AncientCityGenerator {
    private static final int MAX_DIST = 116; // max distance from city anchor

    public List<Piece> pieces = new ArrayList<>();
    private long worldseed;
    public final Assembler assembler = new Assembler(6, this.pieces);

    public AncientCityGenerator() {}
    
    public boolean generate(long worldseed, int chunkX, int chunkZ, ChunkRand rand) {
        this.pieces.clear();
        this.worldseed = worldseed;

        // choose random starting template and rotation
        rand.setCarverSeed(this.worldseed, chunkX, chunkZ, MCVersion.v1_19); // version doesnt matter
        BlockRotation rotation = BlockRotation.getRandom(rand);
        int template = START_TEMPLATES[rand.nextInt(START_TEMPLATES.length)];
        BPos size = AncientCityStructureSize.STRUCTURE_SIZE_V2.get(template);
        
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
        int centerY = box.minY + 1;
        int y = startPos.getY() + 1;

        // create the first piece
        Piece piece = new Piece(template, startPos, box, rotation, 0);
        piece.move(0, y - centerY, 0);
        
        // create structure max bounding box
        BlockBox fullBox = new BlockBox(centerX - MAX_DIST, y - MAX_DIST, centerZ - MAX_DIST, centerX + MAX_DIST, y + MAX_DIST, centerZ + MAX_DIST);
        this.assembler.pieces.add(piece);
        VoxelShape a = new VoxelShape(fullBox);
        a.inner.add(box);
        piece.voxelShape = a;
        
        // place pieces
        this.assembler.placing.addLast(piece);
        while (!this.assembler.placing.isEmpty()) {
            this.assembler.tryPlacing(this.assembler.placing.removeFirst(), rand);
        }
        
        return true;
    }

    static public class Piece {
    	int id;
        public BPos pos;
        public BlockBox box;
        public BlockRotation rotation;
        private VoxelShape voxelShape;
        int depth;

        public String getName(){
            return AncientCityPieceNames.PIECENAMES.get(this.id);
        }
        
        Piece(int id, BPos pos, BlockBox box, BlockRotation rotation, int depth) {
            this.id = id;
            this.pos = pos;
            this.box = box;
            this.rotation = rotation;
            this.depth = depth;
        }

        public void move(int x, int y, int z) {
            box.move(x, y, z);
            pos = pos.add(x, y, z);
        }

        public int getShuffledJigsawBlocks(BPos offset, JRand rand, BlockJigsawInfo[] arr) {//taking 20% need to opti
            JigsawBlock[] blocks = AncientCityJigsawBlocks.JIGSAW_BLOCKS[this.id];
            int len = blocks.length;
            for (int i = 0; i < len; i++) {
                JigsawBlock jigsawBlock = blocks[i];
                BlockJigsawInfo blockJigsawInfo = arr[i];
                blockJigsawInfo.nbt = jigsawBlock;
                blockJigsawInfo.pos.setRotateOffset(jigsawBlock.relativePos, this.rotation, BPos.ORIGIN, offset);
//                blockJigsawInfo.pos.set(this.rotation.rotate(jigsawBlock.relativePos, BPos.ORIGIN).add(offset));
//                blockJigsawInfo.pos = this.rotation.rotate(jigsawBlock.relativePos, BPos.ORIGIN).add(offset);
                blockJigsawInfo.rotation = this.rotation;
            }
            Main.shuffle(rand, arr, len);
            return len;

//        	List<JigsawBlock> blocks = AncientCityJigsawBlocks.JIGSAW_BLOCKS_V2.get(this.id);
//            if (blocks.isEmpty())
//            	return List.of();

//            BlockJigsawInfo[] arr = new BlockJigsawInfo[blocks.size()];
//            for (int i = 0; i < blocks.size(); i++) {
//                JigsawBlock b = blocks.get(i);
//                arr[i] = new BlockJigsawInfo(b, rotation.rotate(b.relativePos, new BPos(0,0,0)).add(offset), rotation );
//            }
//
//            switch (blocks.size()) {
//                case 5: {
//                    int i = rand.nextInt(5);
//                    BlockJigsawInfo tmp = arr[4];
//                    arr[4] = arr[i];
//                    arr[i] = tmp;
//                }
//                case 4: {
//                    int i = rand.nextInt(4);
//                    BlockJigsawInfo tmp = arr[3];
//                    arr[3] = arr[i];
//                    arr[i] = tmp;
//                }
//                case 3: {
//                    int i = rand.nextInt(3);
//                    BlockJigsawInfo tmp = arr[2];
//                    arr[2] = arr[i];
//                    arr[i] = tmp;
//                }
//                case 2: {
//                    int i = rand.nextInt(2);
//                    BlockJigsawInfo tmp = arr[1];
//                    arr[1] = arr[i];
//                    arr[i] = tmp;
//                }
//                case 1: {
//                } break;
//                default:
//                    throw new RuntimeException("Unhandled");
//            }
//
//            return Arrays.asList(arr);
            
//            List<BlockJigsawInfo> list = new ArrayList<>(blocks.size());
//
//            for (JigsawBlock b : blocks) {
//                BlockJigsawInfo blockJigsawInfo = new BlockJigsawInfo(b, rotation.rotate(b.relativePos, new BPos(0,0,0)).add(offset), rotation );
//                list.add(blockJigsawInfo);
//            }
//            rand.shuffle(list);
//            return list;
        }
        
        public void setVoxelShape(VoxelShape mutableobject1) {
            this.voxelShape = mutableobject1;
        }
        
        public VoxelShape getVoxelShape() {
            return this.voxelShape;
        }
    }

    public static class BlockJigsawInfo {
        public JigsawBlock nbt;
        public MutableBlockPos pos = new MutableBlockPos();
//        public BPos pos;
        public BlockRotation rotation;
        public BlockJigsawInfo() {

        }

        public BlockDirection getFront() {
            return rotation.rotate(this.nbt.direction1);
        }
        
        public static BlockDirection getOpposite(BlockDirection b){
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
        
        public boolean canAttach15(BlockJigsawInfo blockJigsawInfo2, BlockDirection direction) {
            return direction == getOpposite(blockJigsawInfo2.getFront())
                    && this.nbt.targetName.equals(blockJigsawInfo2.nbt.name);
        }
    }

    public static class Assembler {
        int maxDepth;
        List<Piece> pieces;
        BlockJigsawInfo[] jigsawBlocks1 = new BlockJigsawInfo[5];
        BlockJigsawInfo[] jigsawBlocks2 = new BlockJigsawInfo[5];
        int[] poolTemplates1 = new int[46];
        BlockRotation[] rotations1 = new BlockRotation[4];

        private final Deque<Piece> placing = new ArrayDeque<>();
        Assembler(int maxDepth, List<Piece> pieces) {
            this.maxDepth = maxDepth;
            this.pieces = pieces;
            for (int i = 0; i < this.jigsawBlocks1.length; i++) {
                this.jigsawBlocks1[i] = new BlockJigsawInfo();
            }
            for (int i = 0; i < this.jigsawBlocks2.length; i++) {
                this.jigsawBlocks2[i] = new BlockJigsawInfo();
            }
        }

        public void tryPlacing(Piece piece, ChunkRand rand) {
            int depth = piece.depth;
            BPos pos = piece.pos;
            VoxelShape pieceInnerFreeSpace = null;
            BlockBox pieceBox = piece.box;
            int minY = pieceBox.minY;

            BlockJigsawInfo[] arr1 = this.jigsawBlocks1;
            int len1 = piece.getShuffledJigsawBlocks(pos, rand, arr1);
            label139:
            for (int ji1 = 0; ji1 < len1; ji1++) {
                BlockJigsawInfo blockJigsawInfo = arr1[ji1];
//                if (blockJigsawInfo.nbt.targetName.equals("empty")) {
//                    continue;
//                }
//                if (blockJigsawInfo.nbt.targetName.equals("bottom")) {
////                    if (depth != this.maxDepth) {
////                        rand.advance()
////                    }
//                    continue;
//                }

                BlockDirection direction = blockJigsawInfo.getFront();
                MutableBlockPos parentJigsawPos = blockJigsawInfo.pos;
//                BPos parentJigsawPos = blockJigsawInfo.pos;
                BPos childJigsawPos = new BPos(parentJigsawPos.x + direction.getVector().getX(),
                        parentJigsawPos.y + direction.getVector().getY(),
                        parentJigsawPos.z + direction.getVector().getZ());
                int y = parentJigsawPos.y - minY;

                VoxelShape freeSpace;

                if (pieceBox.contains(childJigsawPos)) {
                    if (pieceInnerFreeSpace == null) {
                        pieceInnerFreeSpace = new VoxelShape(pieceBox);
                    }
                	freeSpace = pieceInnerFreeSpace;
                } else {
                    freeSpace = piece.getVoxelShape();
                }
                  
                if (depth == this.maxDepth) continue;

                Main.A += 1;

                int[] arr3 = this.poolTemplates1;
                int len3 = getShuffledTemplatesFromPool(rand, blockJigsawInfo.nbt.poolType, arr3);
                for (int ji3 = 0; ji3 < len3; ji3++) {
                    int jigsawpiece1 = arr3[ji3];
                	if (jigsawpiece1 == 60) // empty piece
                		break;

                    Set<BlockDirection> directions = AncientCityJigsawBlocks.PIECE_CONNECTION_DIRECTIONS[jigsawpiece1].get(blockJigsawInfo.nbt.targetName);
                    if (directions == null) {
//                        rand.advance(4 - 1 + (AncientCityJigsawBlocks.JIGSAW_BLOCKS_V2.get(jigsawpiece1).size() - 1) * 4);
                        Main.skipShuffle(rand, 4);
                        for (int i = 0; i < 4; i++) {
                            Main.skipShuffle(rand, AncientCityJigsawBlocks.JIGSAW_BLOCKS_V2.get(jigsawpiece1).size());
                        }
                        continue;
                    }

                    BlockRotation[] arr4 = this.rotations1;
                    getShuffledBlockRotations(rand, arr4);
                    for (BlockRotation rotation1 : arr4) {
                        BlockRotation rotation2 = rotation1;
                        if (rotation2 == BlockRotation.CLOCKWISE_90) rotation2 = BlockRotation.COUNTERCLOCKWISE_90;
                        else if (rotation2 == BlockRotation.COUNTERCLOCKWISE_90) rotation2 = BlockRotation.CLOCKWISE_90;
                        if (!directions.contains(rotation2.rotate(direction))) {
//                            rand.advance(AncientCityJigsawBlocks.JIGSAW_BLOCKS_V2.get(jigsawpiece1).size() - 1);
                            Main.skipShuffle(rand, AncientCityJigsawBlocks.JIGSAW_BLOCKS_V2.get(jigsawpiece1).size());
                            continue;
                        }

                    	BPos size1 = AncientCityStructureSize.STRUCTURE_SIZE_V2.get(jigsawpiece1);
                        BlockBox box1 = size1.getX() == -1 ? new BlockBox(0,0,0,0,0,0) : BlockBox.getBoundingBox(BPos.ORIGIN, rotation1, BPos.ORIGIN, BlockMirror.NONE, size1);
                        Piece piece1 = new Piece(jigsawpiece1, BPos.ORIGIN, box1, rotation1, 0);

                        BlockJigsawInfo[] arr2 = this.jigsawBlocks2;
                        int len2 = piece1.getShuffledJigsawBlocks(BPos.ORIGIN, rand, arr2);
                        for (int ji2 = 0; ji2 < len2; ji2++) {
                            BlockJigsawInfo blockJigsawInfo2 = arr2[ji2];
//                            Main.A += 1;
                        	
                        	if (blockJigsawInfo.canAttach15(blockJigsawInfo2, direction)) {
//                                Main.B += 1;

                            	MutableBlockPos blockPos3 = blockJigsawInfo2.pos;
//                                BPos blockPos3 = blockJigsawInfo2.pos;
                                BPos blockPos4 = new BPos(
                                		childJigsawPos.getX() - blockPos3.x,
                                		childJigsawPos.getY() - blockPos3.y,
                                		childJigsawPos.getZ() - blockPos3.z);
                                        
                                BlockBox box2;
                                if(size1.equals(BPos.ORIGIN)){
                                	box2 = new BlockBox(blockPos4.getX(), blockPos4.getY(), blockPos4.getZ(),
                                            			blockPos4.getX(), blockPos4.getY(), blockPos4.getZ());
                                }
                                else {
                                	box2 = BlockBox.getBoundingBox(blockPos4, rotation1, BPos.ORIGIN, BlockMirror.NONE, size1);
                                }
                                        
                                int j1 = box2.minY;
                                int k1 = blockPos3.y;
                                int l1 = y - k1 + blockJigsawInfo.getFront().getVector().getY();
                                        
                                int i2 = minY + l1;
                                int j2 = i2 - j1;
                                
                                BlockBox box3 = new BlockBox(box2.minX,box2.minY,box2.minZ,box2.maxX,box2.maxY,box2.maxZ);
                                box3.move(0, j2, 0);
                                BPos blockpos5 = blockPos4.add(0, j2, 0);
                                        
                                if (isNotEmpty(freeSpace,box3)) {
//                                    Main.C += 1;

                                	freeSpace.inner.add(box3);
                                            
                                    Piece piece2 = new Piece(jigsawpiece1,blockpos5,box3,rotation1,depth+1);
                                    
                                    if(depth+1 <= this.maxDepth){
                                    	this.pieces.add(piece2);
                                    	piece2.setVoxelShape(freeSpace);
                                      	this.placing.addLast(piece2);
                                    }
                                    
                                    continue label139;
                                }
                        	}
                        }
                    }
                }
            }
        }
        
        private boolean isNotEmpty(VoxelShape voxelShape, BlockBox box1) {
            if (box1.minX < voxelShape.outer.minX || box1.minY < voxelShape.outer.minY || box1.minZ < voxelShape.outer.minZ || box1.maxX > voxelShape.outer.maxX || box1.maxY > voxelShape.outer.maxY || box1.maxZ > voxelShape.outer.maxZ) {
                return false;
            }

            for (BlockBox fullBox: voxelShape.inner){
//            for (int i = voxelShape.fullBoxes.size() - 1; i >= 0; i--) {
//                BlockBox fullBox = voxelShape.fullBoxes.get(i);
                if(intersects2(box1,fullBox)){
                    return false;
                }
            }
            return true;
        }
        
        public static boolean intersects2(BlockBox box1, BlockBox box) {
            return box1.maxX >= box.minX && box1.minX <= box.maxX && box1.maxZ >= box.minZ && box1.minZ <= box.maxZ && box1.maxY >= box.minY && box1.minY <= box.maxY;
        }
    }
    
    private static int getShuffledTemplatesFromPool(JRand rand, int poolId, int[] arr) {
        int[] pool = AncientCityPools.CITY_POOLS[poolId];
        int len = pool.length;
        System.arraycopy(pool, 0, arr, 0, len);
    	Main.shuffle(rand, arr, len);
    	return len;
    }

    public static void getShuffledBlockRotations(JRand rand, BlockRotation[] arr) {
        arr[0] = BlockRotation.NONE;
        arr[1] = BlockRotation.CLOCKWISE_90;
        arr[2] = BlockRotation.CLOCKWISE_180;
        arr[3] = BlockRotation.COUNTERCLOCKWISE_90;
        Main.shuffle(rand, arr);
    }

    public static final int[] START_TEMPLATES = AncientCityPools.CITY_POOLS[0];

    
    // -------------------------
    // SEEDFINDING UTILS
    // -------------------------
    
    public List<Piece> getPieces() {
        return this.pieces;
    }
    
    
    public List<Pair<BPos, LootTable>> getChests() {
    	ArrayList<Pair<BPos, LootTable>> result = new ArrayList<>();
    	
    	for (Piece p : this.pieces) {
    		List<Pair<BPos, LootTable>> chests = AncientCityStructureLoot.STRUCTURE_LOOT_V2.get(p.id);
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
    
    
    public List<Triplet<BPos, LootTable, Long>> getChestsWithLootSeeds() {
    	ArrayList<Triplet<BPos, LootTable, Long>> result = new ArrayList<>();
    	HashMap<CPos, DecoratorRand> chunkRandoms = new HashMap<>();
  	
    	for (Piece p : this.pieces) {
    		
    		List<Pair<BPos, LootTable>> chests = AncientCityStructureLoot.STRUCTURE_LOOT_V2.get(p.id);
    		if (chests==null || chests.size() == 0) 
    			continue;	 

    		for (Pair<BPos, LootTable> chest : chests) {
    			BPos rotatedOffset = chest.getFirst().transform(BlockMirror.NONE, p.rotation, BPos.ORIGIN).add(new BPos(0,-1,0));
    			BPos realChestPos = p.pos.add(rotatedOffset);
    			CPos chestChunkPos = realChestPos.toChunkPos();
    			
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
    		List<Pair<BPos, LootTable>> chests = AncientCityStructureLoot.STRUCTURE_LOOT_V2.get(p.id);
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
