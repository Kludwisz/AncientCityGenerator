package kludwisz.ancientcity;

import com.seedfinding.mccore.rand.ChunkRand;

public class Main {
	public static void main(String [] args) {
		AncientCityGenerator gen = new AncientCityGenerator();
		gen.generate(5854194984578675338L, 1263, -1266, new ChunkRand());

		for (AncientCityGenerator.Piece piece : gen.pieces) {
			System.out.println(piece.name + "     MIN: /tp " + piece.box.minX + " " + piece.box.minY + " " + piece.box.minZ
										  + "     MAX: /tp " + piece.box.maxX + " " + piece.box.maxY + " " + piece.box.maxZ);
		}
		
		System.out.println("Total pieces: " + gen.pieces.size());
	}
}
