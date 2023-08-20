package kludwisz.ancientcity;

import java.util.List;

import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.util.data.Triplet;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mcfeature.loot.LootContext;
import com.seedfinding.mcfeature.loot.LootTable;
import com.seedfinding.mcfeature.loot.item.ItemStack;

public class Main {
	public static void main(String [] args) {
		AncientCityGenerator gen = new AncientCityGenerator();
		gen.generate(5854194984578675338L, 1263, -1266, new ChunkRand());

		for (AncientCityGenerator.Piece piece : gen.pieces) {
			System.out.println(piece.name + "     MIN: /tp " + piece.box.minX + " " + piece.box.minY + " " + piece.box.minZ
										  + "     MAX: /tp " + piece.box.maxX + " " + piece.box.maxY + " " + piece.box.maxZ);
		}
		
		List<Triplet<BPos, LootTable, Long>> chests = gen.getChestsWithLootSeeds();
		
		for (Triplet<BPos, LootTable, Long> chest : chests) {
			System.out.println("/tp " + chest.getFirst().getX() + " " + chest.getFirst().getY() + " "+  chest.getFirst().getZ());
			LootContext ctx = new LootContext(chest.getThird());
			List <ItemStack> items = chest.getSecond().generate(ctx);
			
			for (ItemStack is : items) {
				System.out.println(is.getItem().getName() + " " + is.getCount());
			}
			System.out.println("----");
		}
		
		System.out.println(chests.size());
	}
}
