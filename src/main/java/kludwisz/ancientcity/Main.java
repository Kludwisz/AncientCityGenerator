package kludwisz.ancientcity;

import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.LootContext;

public class Main {
	public static void main(String[] args) {
		testTime();
	}
	
	static final AncientCity CITY = new AncientCity(MCVersion.v1_19_2);
	static final ChunkRand rand = new ChunkRand();
	static final LootContext ctx = new LootContext(0L, MCVersion.v1_19_2);
	
	public static void testTime() {
		long point1 = System.nanoTime();
		for (int i=0; i<1000; i++) {
			CPos chunk = CITY.getInRegion(i, 0, 0, rand);
			AncientCityGenerator gen = new AncientCityGenerator();
			gen.generate(i, chunk.getX(), chunk.getZ(), rand);
			//List<Triplet<BPos, LootTable, Long>> chests = gen.getChestsWithLootSeeds();
		}
		long point2 = System.nanoTime();
		
		System.out.println((point2-point1)/1000000 + " ms");
	}
}
