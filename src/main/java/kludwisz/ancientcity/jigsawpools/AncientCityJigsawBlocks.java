package kludwisz.ancientcity.jigsawpools;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.seedfinding.mccore.block.Block;
import com.seedfinding.mccore.block.Blocks;
import com.seedfinding.mccore.util.block.BlockDirection;
import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mccore.util.data.Quad;
import com.seedfinding.mccore.util.pos.BPos;

// TODO get rid of the comments eventually

public class AncientCityJigsawBlocks {	// structure name, List of Jigsaws: (PoolType, Pair of (name, target), orientation pair, turns into, block vector)
	public static final HashMap<String, List<Pair<Quad<PoolType, Pair<String, String>, Pair<BlockDirection,BlockDirection>, Block>, BPos>>> JIGSAW_BLOCKS = new HashMap<>() {{
		this.put("city/entrance/entrance_connector", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.CITY_ENTRANCE/* minecraft:empty -> minecraft:entrance_middle*/, new Pair<>("empty", "entrance_middle"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,19)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(19,0,0)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(19,0,38)),
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:entrance_start -> minecraft:empty*/, new Pair<>("entrance_start", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(29,0,19))
		));
		this.put("city/entrance/entrance_path_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(14,0,0)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(14,0,38)),
				new Pair<>(new Quad<>( PoolType.CITY_ENTRANCE/* minecraft:entrance_middle -> minecraft:empty*/, new Pair<>("entrance_middle", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(34,0,19))
		));
		this.put("city/entrance/entrance_path_2", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(14,0,0)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(14,0,38)),
				new Pair<>(new Quad<>( PoolType.CITY_ENTRANCE/* minecraft:entrance_middle -> minecraft:empty*/, new Pair<>("entrance_middle", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(34,0,19))
		));
		this.put("city/entrance/entrance_path_3", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(14,0,0)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(14,0,38)),
				new Pair<>(new Quad<>( PoolType.CITY_ENTRANCE/* minecraft:entrance_middle -> minecraft:empty*/, new Pair<>("entrance_middle", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(34,0,19))
		));
		this.put("city/entrance/entrance_path_4", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(14,0,0)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(14,0,38)),
				new Pair<>(new Quad<>( PoolType.CITY_ENTRANCE/* minecraft:entrance_middle -> minecraft:empty*/, new Pair<>("entrance_middle", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(34,0,19))
		));
		this.put("city/entrance/entrance_path_5", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(14,0,0)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(14,0,38)),
				new Pair<>(new Quad<>( PoolType.CITY_ENTRANCE/* minecraft:entrance_middle -> minecraft:empty*/, new Pair<>("entrance_middle", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(34,0,19))
		));
		this.put("city_center/city_center_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_bottom*/, new Pair<>("empty", "connect_bottom"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,1,0)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_right*/, new Pair<>("empty", "connect_right"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,1,40)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_left*/, new Pair<>("empty", "connect_left"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,1,0)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_top*/, new Pair<>("empty", "connect_top"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,1,40)),
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:city_anchor -> minecraft:empty*/, new Pair<>("city_anchor", "empty"), new Pair<>(BlockDirection.DOWN, BlockDirection.WEST), Blocks.STRUCTURE_VOID), new BPos(13,24,20))
		));
		this.put("city_center/city_center_2", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_bottom*/, new Pair<>("empty", "connect_bottom"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,1,0)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_right*/, new Pair<>("empty", "connect_right"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,1,40)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_left*/, new Pair<>("empty", "connect_left"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,1,0)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_top*/, new Pair<>("empty", "connect_top"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,1,40)),
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:city_anchor -> minecraft:empty*/, new Pair<>("city_anchor", "empty"), new Pair<>(BlockDirection.DOWN, BlockDirection.WEST), Blocks.STRUCTURE_VOID), new BPos(13,24,20))
		));
		this.put("city_center/city_center_3", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_bottom*/, new Pair<>("empty", "connect_bottom"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,1,0)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_right*/, new Pair<>("empty", "connect_right"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,1,40)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_left*/, new Pair<>("empty", "connect_left"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,1,0)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_top*/, new Pair<>("empty", "connect_top"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,1,40)),
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:city_anchor -> minecraft:empty*/, new Pair<>("city_anchor", "empty"), new Pair<>(BlockDirection.DOWN, BlockDirection.WEST), Blocks.STRUCTURE_VOID), new BPos(13,24,20))
		));
		this.put("city_center/walls/bottom_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.CITY_ENTRANCE/*, minecraft:empty -> minecraft:entrance_start*/, new Pair<>("empty", "entrance_start"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,20)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_bottom_left_corner*/, new Pair<>("empty", "connect_bottom_left_corner"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(7,0,0)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_bottom_right_corner*/, new Pair<>("empty", "connect_bottom_right_corner"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(7,0,40)),
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_bottom -> minecraft:empty*/, new Pair<>("connect_bottom", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(13,0,0))
		));
		this.put("city_center/walls/bottom_2", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.CITY_ENTRANCE/*, minecraft:empty -> minecraft:entrance_start*/, new Pair<>("empty", "entrance_start"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,20)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_bottom_left_corner*/, new Pair<>("empty", "connect_bottom_left_corner"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(7,0,0)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_bottom_right_corner*/, new Pair<>("empty", "connect_bottom_right_corner"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(7,0,40)),
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_bottom -> minecraft:empty*/, new Pair<>("connect_bottom", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(13,0,0))
		));
		this.put("city_center/walls/bottom_left_corner", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_bottom_left_corner -> minecraft:empty*/, new Pair<>("connect_bottom_left_corner", "empty"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(7,0,13))
		));
		this.put("city_center/walls/bottom_right_corner", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_bottom_right_corner -> minecraft:empty*/, new Pair<>("connect_bottom_right_corner", "empty"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(7,0,0))
		));
		this.put("city_center/walls/bottom_right_corner_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_bottom_right_corner -> minecraft:empty*/, new Pair<>("connect_bottom_right_corner", "empty"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(7,0,0))
		));
		this.put("city_center/walls/bottom_right_corner_2", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_bottom_right_corner -> minecraft:empty*/, new Pair<>("connect_bottom_right_corner", "empty"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(7,0,0))
		));
		this.put("city_center/walls/left", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0)),
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_left -> minecraft:empty*/, new Pair<>("connect_left", "empty"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,0,13))
		));
		this.put("city_center/walls/right", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_right -> minecraft:empty*/, new Pair<>("connect_right", "empty"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,0)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(8,0,13))
		));
		this.put("city_center/walls/top", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_top -> minecraft:empty*/, new Pair<>("connect_top", "empty"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,40)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_top_left_corner*/,new Pair<>("empty", "connect_top_left_corner"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(6,0,0)),
				new Pair<>(new Quad<>( PoolType.CITY_CENTER_WALLS/* minecraft:empty -> minecraft:connect_top_right_corner*/,new Pair<>("empty", "connect_top_right_corner"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(6,0,40)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:empty -> minecraft:connect_wall*/, new Pair<>("empty", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(13,0,19))
		));
		this.put("city_center/walls/top_left_corner", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_top_left_corner -> minecraft:empty*/, new Pair<>("connect_top_left_corner", "empty"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(6,0,13))
		));
		this.put("city_center/walls/top_right_corner", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_top_right_corner -> minecraft:empty*/, new Pair<>("connect_top_right_corner", "empty"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(6,0,0))
		));
		this.put("structures/barracks", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,8)),
				new Pair<>(new Quad<>( PoolType.SCULK/* minecraft:bottom -> minecraft:bottom*/, new Pair<>("bottom", "bottom"), new Pair<>(BlockDirection.UP, BlockDirection.WEST), Blocks.STRUCTURE_VOID), new BPos(10,3,10)),
				new Pair<>(new Quad<>( PoolType.SCULK/* minecraft:bottom -> minecraft:bottom*/, new Pair<>("bottom", "bottom"), new Pair<>(BlockDirection.UP, BlockDirection.NORTH), Blocks.STRUCTURE_VOID), new BPos(11,3,2))
		));
		this.put("structures/camp_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,7))
		));
		this.put("structures/camp_2", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,7))
		));
		this.put("structures/camp_3", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,7))
		));
		this.put("structures/chamber_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.SCULK/* minecraft:bottom -> minecraft:bottom*/, new Pair<>("bottom", "bottom"), new Pair<>(BlockDirection.UP, BlockDirection.WEST), Blocks.STRUCTURE_VOID), new BPos(12,0,6)),
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(18,0,7)),
				new Pair<>(new Quad<>( PoolType.SCULK/* minecraft:bottom -> minecraft:bottom*/, new Pair<>("bottom", "bottom"), new Pair<>(BlockDirection.UP, BlockDirection.WEST), Blocks.STRUCTURE_VOID), new BPos(5,3,1))
		));
		this.put("structures/chamber_2", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(11,0,6)),
				new Pair<>(new Quad<>( PoolType.SCULK/* minecraft:bottom -> minecraft:bottom*/, new Pair<>("bottom", "bottom"), new Pair<>(BlockDirection.UP, BlockDirection.WEST), Blocks.STRUCTURE_VOID), new BPos(8,1,5))
		));
		this.put("structures/chamber_3", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.SCULK/* minecraft:bottom -> minecraft:bottom*/, new Pair<>("bottom", "bottom"), new Pair<>(BlockDirection.UP, BlockDirection.NORTH), Blocks.STRUCTURE_VOID), new BPos(4,0,7)),
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,5))
		));
		this.put("structures/ice_box_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.SCULK/* minecraft:bottom -> minecraft:bottom*/, new Pair<>("bottom", "bottom"), new Pair<>(BlockDirection.UP, BlockDirection.WEST), Blocks.STRUCTURE_VOID), new BPos(12,0,6)),
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(18,0,7)),
				new Pair<>(new Quad<>( PoolType.SCULK/* minecraft:bottom -> minecraft:bottom*/, new Pair<>("bottom", "bottom"), new Pair<>(BlockDirection.UP, BlockDirection.WEST), Blocks.STRUCTURE_VOID), new BPos(5,3,1))
		));
		this.put("structures/large_pillar_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(5,0,2))
		));
		this.put("structures/large_ruin_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,7))
		));
		this.put("structures/medium_pillar_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(6,0,3)),
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(6,0,4))
		));
		this.put("structures/medium_ruin_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,6))
		));
		this.put("structures/medium_ruin_2", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,5))
		));
		this.put("structures/sauna_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.SCULK/* minecraft:bottom -> minecraft:bottom*/, new Pair<>("bottom", "bottom"), new Pair<>(BlockDirection.UP, BlockDirection.EAST), Blocks.STRUCTURE_VOID), new BPos(5,0,26)),
				new Pair<>(new Quad<>( PoolType.SCULK/* minecraft:bottom -> minecraft:bottom*/, new Pair<>("bottom", "bottom"), new Pair<>(BlockDirection.UP, BlockDirection.WEST), Blocks.STRUCTURE_VOID), new BPos(8,0,12)),
				new Pair<>(new Quad<>( PoolType.SCULK/* minecraft:bottom -> minecraft:bottom*/, new Pair<>("bottom", "bottom"), new Pair<>(BlockDirection.UP, BlockDirection.WEST), Blocks.STRUCTURE_VOID), new BPos(18,0,33)),
				new Pair<>(new Quad<>( PoolType.SCULK/* minecraft:bottom -> minecraft:bottom*/, new Pair<>("bottom", "bottom"), new Pair<>(BlockDirection.UP, BlockDirection.WEST), Blocks.STRUCTURE_VOID), new BPos(21,0,9)),
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(28,0,21))
		));
		this.put("structures/small_ruin_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,3))
		));
		this.put("structures/small_ruin_2", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,2))
		));
		this.put("structures/small_statue", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,3)),
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,5))
		));
		this.put("structures/tall_ruin_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(8,0,16))
		));
		this.put("structures/tall_ruin_2", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0))
		));
		this.put("structures/tall_ruin_3", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(8,0,16))
		));
		this.put("structures/tall_ruin_4", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.EMPTY/* minecraft:connect_structure -> minecraft:empty*/, new Pair<>("connect_structure", "empty"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0))
		));
		this.put("walls/intact_corner_wall_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(10,0,0)),
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(10,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(20,0,10))
		));
		this.put("walls/intact_horizontal_wall_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(8,0,20)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,0,10))
		));
		this.put("walls/intact_horizontal_wall_2", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(8,0,20)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,0,10))
		));
		this.put("walls/intact_horizontal_wall_bridge", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,20))
		));
		this.put("walls/intact_horizontal_wall_passage_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,0,10))
		));
		this.put("walls/intact_horizontal_wall_stairs_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,0,10))
		));
		this.put("walls/intact_horizontal_wall_stairs_2", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,0,10))
		));
		this.put("walls/intact_horizontal_wall_stairs_3", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(6,0,20)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,0,10))
		));
		this.put("walls/intact_horizontal_wall_stairs_4", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,0,10))
		));
		this.put("walls/intact_horizontal_wall_stairs_5", Arrays.asList(
				// filler for non-existent structure piece
		));
		this.put("walls/intact_intersection_wall_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(10,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(20,0,10))
		));
		this.put("walls/intact_lshape_wall_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(10,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(20,0,10))
		));
		this.put("walls/ruined_corner_wall_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(10,0,0)),
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(10,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(20,0,10))
		));
		this.put("walls/ruined_corner_wall_2", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(10,0,0)),
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(10,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS_NO_CORNERS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(20,0,10))
		));
		this.put("walls/ruined_horizontal_wall_stairs_1", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,0,10))
		));
		this.put("walls/ruined_horizontal_wall_stairs_2", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,0,10))
		));
		this.put("walls/ruined_horizontal_wall_stairs_3", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(6,0,20)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,0,10))
		));
		this.put("walls/ruined_horizontal_wall_stairs_4", Arrays.asList(
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.WEST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(0,0,10)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.NORTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,0)),
				new Pair<>(new Quad<>( PoolType.STRUCTURES/* minecraft:empty -> minecraft:connect_structure*/, new Pair<>("empty", "connect_structure"), new Pair<>(BlockDirection.SOUTH, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(9,0,20)),
				new Pair<>(new Quad<>( PoolType.WALLS/* minecraft:connect_wall -> minecraft:connect_wall*/, new Pair<>("connect_wall", "connect_wall"), new Pair<>(BlockDirection.EAST, BlockDirection.UP), Blocks.STRUCTURE_VOID), new BPos(17,0,10))
		));
		
		this.put("sculk_patch", Collections.singletonList(
                new Pair<>(new Quad<>(PoolType.EMPTY, new Pair<>("bottom", "bottom") , new Pair<>(BlockDirection.DOWN,BlockDirection.SOUTH) ,Blocks.STRUCTURE_VOID),new BPos(0,0,0))
        ));
    }};
}
