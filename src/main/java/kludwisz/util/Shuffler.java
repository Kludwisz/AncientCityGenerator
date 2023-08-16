package kludwisz.util;

import java.util.ArrayList;
import java.util.List;

import com.seedfinding.mccore.rand.ChunkRand;

public class Shuffler {
	public static <T> List<T> shuffledCopy(List<T> list, ChunkRand rand) {
		ArrayList<T> copy = new ArrayList<>(list);
		shuffle(copy, rand);
		return copy;
	}
	
	public static <T> void shuffle(List<T> list, ChunkRand rand) {
		int size = list.size();

	      for(int var3 = size; var3 > 1; --var3) {
	         int ix = rand.nextInt(var3);
	         list.set(var3 - 1, list.set(ix, list.get(var3 - 1)));
	      }
	}
}
