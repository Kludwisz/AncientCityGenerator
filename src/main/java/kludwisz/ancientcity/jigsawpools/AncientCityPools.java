package kludwisz.ancientcity.jigsawpools;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mccore.util.data.Triplet;

import kludwisz.ancientcity.AncientCityGenerator;


public interface AncientCityPools {
	public static final EnumMap<PoolType, Pair< PoolType, List<Pair<String, Integer>> >> CITY_POOLS = new EnumMap<>(PoolType.class) {{
		put(PoolType.CITY_ENTRANCE, new Pair<>(PoolType.EMPTY, Arrays.asList(
        		new Pair<>("city/entrance/entrance_connector", 1),
        		new Pair<>("city/entrance/entrance_path_1", 1),
        		new Pair<>("city/entrance/entrance_path_2", 1),
        		new Pair<>("city/entrance/entrance_path_3", 1),
        		new Pair<>("city/entrance/entrance_path_4", 1),
        		new Pair<>("city/entrance/entrance_path_5", 1)
        )));
		
		put(PoolType.CITY_CENTER_WALLS, new Pair<>(PoolType.EMPTY, Arrays.asList(
                new Pair<>("city_center/walls/bottom_1", 1),
                new Pair<>("city_center/walls/bottom_2", 1),
                new Pair<>("city_center/walls/bottom_left_corner", 1),
                new Pair<>("city_center/walls/bottom_right_corner_1", 1),
                new Pair<>("city_center/walls/bottom_right_corner_2", 1),
                new Pair<>("city_center/walls/left", 1),
                new Pair<>("city_center/walls/right", 1),
                new Pair<>("city_center/walls/top", 1),
                new Pair<>("city_center/walls/top_right_corner", 1),
                new Pair<>("city_center/walls/top_left_corner", 1)
                
        )));
		
		put(PoolType.WALLS_NO_CORNERS, new Pair<>(PoolType.EMPTY, Arrays.asList(
        		new Pair<>("walls/intact_horizontal_wall_1", 1),
        		new Pair<>("walls/intact_horizontal_wall_2", 1),
        		new Pair<>("walls/intact_horizontal_wall_stairs_1", 1),
        		new Pair<>("walls/intact_horizontal_wall_stairs_2", 1),
        		new Pair<>("walls/intact_horizontal_wall_stairs_3", 1),
        		new Pair<>("walls/intact_horizontal_wall_stairs_4", 1),
        		new Pair<>("walls/intact_horizontal_wall_stairs_5", 1), // missing structure nbt -> error
        		new Pair<>("walls/intact_horizontal_wall_bridge", 1)
        )));
		
		put(PoolType.CITY_CENTER, new Pair<>(PoolType.EMPTY, Arrays.asList(
                new Pair<>("city_center/city_center_1", 1),
                new Pair<>("city_center/city_center_2", 1),
                new Pair<>("city_center/city_center_3", 1)
        )));
        
        put(PoolType.SCULK, new Pair<>(PoolType.EMPTY, Arrays.asList(
        		new Pair<>("sculk_patch", 6),
        		new Pair<>("empty", 1)
        )));

        put(PoolType.STRUCTURES, new Pair<>(PoolType.EMPTY, Arrays.asList(
        		new Pair<>("empty", 7),
        		new Pair<>("structures/barracks", 4),
        		new Pair<>("structures/chamber_1", 4),
        		new Pair<>("structures/chamber_2", 4),
        		new Pair<>("structures/chamber_3", 4),
        		new Pair<>("structures/sauna_1", 4),
        		new Pair<>("structures/small_statue", 4),
        		new Pair<>("structures/large_ruin_1", 1),
        		new Pair<>("structures/tall_ruin_1", 1),
        		new Pair<>("structures/tall_ruin_2", 1),
        		new Pair<>("structures/tall_ruin_3", 2),
        		new Pair<>("structures/tall_ruin_4", 2),
        		new Pair<>("structures/camp_1", 1),
        		new Pair<>("structures/medium_ruin_1", 1),
        		new Pair<>("structures/medium_ruin_2", 1),
        		new Pair<>("structures/small_ruin_1", 1),
        		new Pair<>("structures/small_ruin_2", 1),
        		new Pair<>("structures/large_pillar_1", 1),
        		new Pair<>("structures/medium_pillar_1", 1),
        		new Pair<>("structures/ice_box_1", 1)
        		
        )));

        put(PoolType.WALLS, new Pair<>(PoolType.EMPTY, Arrays.asList(
        		new Pair<>("walls/intact_corner_wall_1", 1),
        		new Pair<>("walls/intact_intersection_wall_1", 1),
        		new Pair<>("walls/intact_lshape_wall_1", 1),
        		new Pair<>("walls/intact_horizontal_wall_1", 1),
        		new Pair<>("walls/intact_horizontal_wall_2", 1),
        		new Pair<>("walls/intact_horizontal_wall_stairs_1", 1),
        		new Pair<>("walls/intact_horizontal_wall_stairs_2", 1),
        		new Pair<>("walls/intact_horizontal_wall_stairs_3", 1),
        		new Pair<>("walls/intact_horizontal_wall_stairs_4", 4),
        		new Pair<>("walls/intact_horizontal_wall_passage_1", 3),
        		new Pair<>("walls/ruined_corner_wall_1", 1),
        		new Pair<>("walls/ruined_corner_wall_2", 1),
        		new Pair<>("walls/ruined_horizontal_wall_stairs_1", 2),
        		new Pair<>("walls/ruined_horizontal_wall_stairs_2", 2),
        		new Pair<>("walls/ruined_horizontal_wall_stairs_3", 3),
        		new Pair<>("walls/ruined_horizontal_wall_stairs_4", 3)
        )));
        
        put(PoolType.EMPTY, new Pair<>(PoolType.EMPTY, Arrays.asList(
                new Pair<>("empty", 0)
        )));
    }};
}
