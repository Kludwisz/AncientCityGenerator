package kludwisz.ancientcity.structures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mcfeature.loot.LootTable;

import kludwisz.ancientcity.loot.AncientCityLootTables;

public class AncientCityStructureLoot {
	public static final HashMap<String, List<Pair<BPos, LootTable> >> STRUCTURE_LOOT = new HashMap<>() {{
		this.put("city/entrance/entrance_connector", Arrays.asList());
		this.put("city/entrance/entrance_path_1", Arrays.asList());
		this.put("city/entrance/entrance_path_2", Arrays.asList());
		this.put("city/entrance/entrance_path_3", Arrays.asList());
		this.put("city/entrance/entrance_path_4", Arrays.asList());
		this.put("city/entrance/entrance_path_5", Arrays.asList());
		this.put("city_center/city_center_1", Arrays.asList());
		
		this.put("city_center/city_center_2", Arrays.asList(
			// ignoring the single golden apple here
			// new Pair<>(new BPos(9,8,20), AncientCityLootTables.NULL.get()) 
		));
		
		this.put("city_center/city_center_3", Arrays.asList());
		this.put("city_center/walls/bottom_1", Arrays.asList());
		this.put("city_center/walls/bottom_2", Arrays.asList());
		this.put("city_center/walls/bottom_left_corner", Arrays.asList());
		this.put("city_center/walls/bottom_right_corner", Arrays.asList());
		this.put("city_center/walls/bottom_right_corner_1", Arrays.asList());
		this.put("city_center/walls/bottom_right_corner_2", Arrays.asList());
		this.put("city_center/walls/left", Arrays.asList());
		this.put("city_center/walls/right", Arrays.asList());
		this.put("city_center/walls/top", Arrays.asList());
		this.put("city_center/walls/top_left_corner", Arrays.asList());
		this.put("city_center/walls/top_right_corner", Arrays.asList());
		
		this.put("structures/barracks", Arrays.asList(
			new Pair<>(new BPos(4,4,15), AncientCityLootTables.TREASURE_CHEST.get()),
			new Pair<>(new BPos(12,4,15), AncientCityLootTables.TREASURE_CHEST.get())
		));
		
		this.put("structures/camp_1", Arrays.asList());
		this.put("structures/camp_2", Arrays.asList());
		this.put("structures/camp_3", Arrays.asList());
		
		this.put("structures/chamber_1", Arrays.asList(
			new Pair<>(new BPos(14,3,11), AncientCityLootTables.TREASURE_CHEST.get())
		));
		this.put("structures/chamber_2", Arrays.asList(
			new Pair<>(new BPos(10,2,5), AncientCityLootTables.TREASURE_CHEST.get())
		));
		this.put("structures/chamber_3", Arrays.asList(
			new Pair<>(new BPos(7,1,5), AncientCityLootTables.TREASURE_CHEST.get())
		));
		this.put("structures/ice_box_1", Arrays.asList(
			new Pair<>(new BPos(12,1,7), AncientCityLootTables.ICE_BOX.get())
		));
		
		this.put("structures/large_pillar_1", Arrays.asList());
		this.put("structures/large_ruin_1", Arrays.asList());
		this.put("structures/medium_pillar_1", Arrays.asList());
		this.put("structures/medium_ruin_1", Arrays.asList());
		this.put("structures/medium_ruin_2", Arrays.asList());
		
		this.put("structures/sauna_1", Arrays.asList(
			new Pair<>(new BPos(12,1,19), AncientCityLootTables.TREASURE_CHEST.get()),
			new Pair<>(new BPos(21,1,18), AncientCityLootTables.TREASURE_CHEST.get()),
			new Pair<>(new BPos(25,1,10), AncientCityLootTables.TREASURE_CHEST.get())
		));
		
		this.put("structures/small_ruin_1", Arrays.asList());
		this.put("structures/small_ruin_2", Arrays.asList());
		this.put("structures/small_statue", Arrays.asList());
		
		this.put("structures/tall_ruin_1", Arrays.asList(
			new Pair<>(new BPos(8,16,8), AncientCityLootTables.TREASURE_CHEST.get())
		));
		this.put("structures/tall_ruin_2", Arrays.asList(
			new Pair<>(new BPos(7,1,5), AncientCityLootTables.TREASURE_CHEST.get()),
			new Pair<>(new BPos(13,7,9), AncientCityLootTables.TREASURE_CHEST.get())
		));
		this.put("structures/tall_ruin_3", Arrays.asList(
			new Pair<>(new BPos(10,14,7), AncientCityLootTables.TREASURE_CHEST.get())
		));
		this.put("structures/tall_ruin_4", Arrays.asList(
			new Pair<>(new BPos(8,7,13), AncientCityLootTables.TREASURE_CHEST.get())
		));
		
		this.put("walls/intact_corner_wall_1", Arrays.asList());
		this.put("walls/intact_horizontal_wall_1", Arrays.asList());
		this.put("walls/intact_horizontal_wall_2", Arrays.asList());
		this.put("walls/intact_horizontal_wall_bridge", Arrays.asList());
		this.put("walls/intact_horizontal_wall_passage_1", Arrays.asList());
		this.put("walls/intact_horizontal_wall_stairs_1", Arrays.asList());
		this.put("walls/intact_horizontal_wall_stairs_2", Arrays.asList());
		this.put("walls/intact_horizontal_wall_stairs_3", Arrays.asList());
		this.put("walls/intact_horizontal_wall_stairs_4", Arrays.asList());
		this.put("walls/intact_intersection_wall_1", Arrays.asList());
		this.put("walls/intact_lshape_wall_1", Arrays.asList());
		this.put("walls/ruined_corner_wall_1", Arrays.asList());
		this.put("walls/ruined_corner_wall_2", Arrays.asList());
		this.put("walls/ruined_horizontal_wall_stairs_1", Arrays.asList());
		this.put("walls/ruined_horizontal_wall_stairs_2", Arrays.asList());
		this.put("walls/ruined_horizontal_wall_stairs_3", Arrays.asList());
		this.put("walls/ruined_horizontal_wall_stairs_4", Arrays.asList());
	}};
}
