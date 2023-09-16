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
        this.assembler.placing.clear();
        this.assembler.pieces.add(piece);
        VoxelShape a = new VoxelShape(fullBox);
        a.cutout.add(box);
        piece.freeSpace = a;
        
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
        public VoxelShape freeSpace;
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
        
        public boolean canAttach(BlockJigsawInfo blockJigsawInfo2, BlockDirection direction) {
            return direction == getOpposite(blockJigsawInfo2.getFront())
                    && this.nbt.targetName.equals(blockJigsawInfo2.nbt.name);
        }
    }

    public static class Assembler {
        int maxDepth;
        List<Piece> pieces;
        public final Deque<Piece> placing = new ArrayDeque<>();

        BlockJigsawInfo[] parentJigsawsArr = new BlockJigsawInfo[5];
        BlockJigsawInfo[] childPieceJigsawBlocksArr = new BlockJigsawInfo[5];
        int[] childTemplatesArr = new int[46];
        BlockRotation[] childRotationsArr = new BlockRotation[4];

        Assembler(int maxDepth, List<Piece> pieces) {
            this.maxDepth = maxDepth;
            this.pieces = pieces;
            for (int i = 0; i < this.parentJigsawsArr.length; i++) {
                this.parentJigsawsArr[i] = new BlockJigsawInfo();
            }
            for (int i = 0; i < this.childPieceJigsawBlocksArr.length; i++) {
                this.childPieceJigsawBlocksArr[i] = new BlockJigsawInfo();
            }
        }

        public void tryPlacing(Piece parentPiece, ChunkRand rand) {
            int parentPieceDepth = parentPiece.depth;
            BPos parentPiecePos = parentPiece.pos;
            VoxelShape parentPieceInnerFreeSpace = null;
            BlockBox parentPieceBox = parentPiece.box;

            BlockJigsawInfo[] parentJigsaws = this.parentJigsawsArr;
            int parentJigsawsLen = getShuffledJigsawBlocks(rand, parentJigsaws, parentPiece.id, parentPiece.rotation, parentPiecePos);
            nextParentJigsaw:
            for (int parentJigsawIndex = 0; parentJigsawIndex < parentJigsawsLen; parentJigsawIndex++) {
                BlockJigsawInfo parentJigsaw = parentJigsaws[parentJigsawIndex];

                BlockDirection parentJigsawFront = parentJigsaw.getFront();
                MutableBlockPos parentJigsawPos = parentJigsaw.pos;
                BPos childJigsawPos = new BPos(parentJigsawPos.x + parentJigsawFront.getVector().getX(),
                        parentJigsawPos.y + parentJigsawFront.getVector().getY(),
                        parentJigsawPos.z + parentJigsawFront.getVector().getZ());

                VoxelShape freeSpace;

                if (parentPieceBox.contains(childJigsawPos)) {
                    if (parentPieceInnerFreeSpace == null) {
                        parentPieceInnerFreeSpace = new VoxelShape(parentPieceBox);
                    }
                	freeSpace = parentPieceInnerFreeSpace;
                } else {
                    freeSpace = parentPiece.freeSpace;
                }
                  
                if (parentPieceDepth == this.maxDepth) continue;

                Main.A += 1;

                int[] childTemplates = this.childTemplatesArr;
                int childTemplatesLen = getShuffledTemplatesFromPool(rand, parentJigsaw.nbt.poolType, childTemplates);
                for (int childTemplateIndex = 0; childTemplateIndex < childTemplatesLen; childTemplateIndex++) {
                    int childPieceId = childTemplates[childTemplateIndex];
                	if (childPieceId == 60) // empty piece
                		break;

                    Set<BlockDirection> directions = AncientCityJigsawBlocks.PIECE_CONNECTION_DIRECTIONS[childPieceId].get(parentJigsaw.nbt.targetName);
                    if (directions == null) {
//                        rand.advance(4 - 1 + (AncientCityJigsawBlocks.JIGSAW_BLOCKS_V2.get(jigsawpiece1).size() - 1) * 4);
                        Main.skipShuffle(rand, 4);
                        for (int i = 0; i < 4; i++) {
                            Main.skipShuffle(rand, AncientCityJigsawBlocks.JIGSAW_BLOCKS_V2.get(childPieceId).size());
                        }
                        continue;
                    }

                    BlockRotation[] childRotations = this.childRotationsArr;
                    getShuffledBlockRotations(rand, childRotations);
                    for (BlockRotation childPieceRotation : childRotations) {

                        BlockRotation childRotationInverse = switch (childPieceRotation) {
                            case CLOCKWISE_90 -> BlockRotation.COUNTERCLOCKWISE_90;
                            case COUNTERCLOCKWISE_90 -> BlockRotation.CLOCKWISE_90;
                            default -> childPieceRotation;
                        };
                        if (!directions.contains(childRotationInverse.rotate(parentJigsawFront))) {
//                            rand.advance(AncientCityJigsawBlocks.JIGSAW_BLOCKS_V2.get(jigsawpiece1).size() - 1);
                            Main.skipShuffle(rand, AncientCityJigsawBlocks.JIGSAW_BLOCKS_V2.get(childPieceId).size());
                            continue;
                        }

                    	BPos childPieceSize = AncientCityStructureSize.STRUCTURE_SIZE_V2.get(childPieceId);

                        BlockJigsawInfo[] arr2 = this.childPieceJigsawBlocksArr;
                        int len2 = getShuffledJigsawBlocks(rand, arr2, childPieceId, childPieceRotation, BPos.ORIGIN);
                        for (int ji2 = 0; ji2 < len2; ji2++) {
                            BlockJigsawInfo childJigsaw = arr2[ji2];
//                            Main.A += 1;
                        	
                        	if (parentJigsaw.canAttach(childJigsaw, parentJigsawFront)) {
//                                Main.B += 1;

                            	MutableBlockPos childJigsawOffset = childJigsaw.pos;
                                BPos childPiecePos = new BPos(
                                		childJigsawPos.getX() - childJigsawOffset.x,
                                		childJigsawPos.getY() - childJigsawOffset.y,
                                		childJigsawPos.getZ() - childJigsawOffset.z);
                                        
                                BlockBox childPieceBox;
                                if (childPieceSize.getX() < 1 || childPieceSize.getY() < 1 || childPieceSize.getZ() < 1) {
                                	childPieceBox = new BlockBox(childPiecePos.getX(), childPiecePos.getY(), childPiecePos.getZ(), childPiecePos.getX(), childPiecePos.getY(), childPiecePos.getZ());
                                } else {
                                	childPieceBox = BlockBox.getBoundingBox(childPiecePos, childPieceRotation, BPos.ORIGIN, BlockMirror.NONE, childPieceSize);
                                }
                                        
                                if (isInsideFreeSpace(freeSpace, childPieceBox)) {
//                                    Main.C += 1;

                                	freeSpace.cutout.add(childPieceBox);

                                    int childPieceDepth = parentPieceDepth + 1;
                                    Piece childPiece = new Piece(childPieceId, childPiecePos, childPieceBox, childPieceRotation,childPieceDepth);
                                    childPiece.freeSpace = freeSpace;

                                    this.pieces.add(childPiece);

                                    if (childPieceDepth <= this.maxDepth){
                                      	this.placing.addLast(childPiece);
                                    }
                                    
                                    continue nextParentJigsaw;
                                }
                        	}
                        }
                    }
                }
            }
        }
        
        private boolean isInsideFreeSpace(VoxelShape freeSpace, BlockBox box) {
            if (box.minX < freeSpace.outer.minX || box.minY < freeSpace.outer.minY || box.minZ < freeSpace.outer.minZ || box.maxX > freeSpace.outer.maxX || box.maxY > freeSpace.outer.maxY || box.maxZ > freeSpace.outer.maxZ) {
                return false;
            }

            for (BlockBox cutoutBox : freeSpace.cutout){
                if (intersects(box, cutoutBox)){
                    return false;
                }
            }
            return true;
        }
        
        public static boolean intersects(BlockBox box1, BlockBox box) {
            return box1.maxX >= box.minX && box1.minX <= box.maxX && box1.maxZ >= box.minZ && box1.minZ <= box.maxZ && box1.maxY >= box.minY && box1.minY <= box.maxY;
        }
    }

    public static int getShuffledJigsawBlocks(JRand rand, BlockJigsawInfo[] arr, int id, BlockRotation rotation, BPos offset) {//taking 20% need to opti
        JigsawBlock[] blocks = AncientCityJigsawBlocks.JIGSAW_BLOCKS[id];
        int len = blocks.length;
        for (int i = 0; i < len; i++) {
            JigsawBlock jigsawBlock = blocks[i];
            BlockJigsawInfo blockJigsawInfo = arr[i];
            blockJigsawInfo.nbt = jigsawBlock;
            blockJigsawInfo.pos.setRotateOffset(jigsawBlock.relativePos, rotation, BPos.ORIGIN, offset);
//                blockJigsawInfo.pos.set(this.rotation.rotate(jigsawBlock.relativePos, BPos.ORIGIN).add(offset));
//                blockJigsawInfo.pos = this.rotation.rotate(jigsawBlock.relativePos, BPos.ORIGIN).add(offset);
            blockJigsawInfo.rotation = rotation;
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
