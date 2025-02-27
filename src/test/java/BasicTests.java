import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.util.data.Triplet;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.LootTable;
import kludwisz.ancientcity.AncientCity;
import kludwisz.ancientcity.AncientCityGenerator;
import kludwisz.ancientcity.loot.AncientCityLootTables;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BasicTests {
    @Test
    public void functionalitySmall() {
        AncientCity city = new AncientCity(MCVersion.v1_21);
        AncientCityGenerator gen = new AncientCityGenerator();

        for (int i = 0; i < 10; i++) {
            try {
                long worldseed = 123456L + i;
                CPos pos = city.getInRegion(worldseed, 0, 0, new ChunkRand());
                gen.generate(worldseed, pos.getX(), pos.getZ(), new ChunkRand());

                assertTrue(gen.piecesLen > 0);
                System.out.println("Generated " + gen.piecesLen + " pieces.");
            }
            catch (Exception ex) {
                System.err.println(ex.getMessage());
                fail();
            }
        }
    }

    @Test
    public void visualizeError() {
        long worldseed = 61359933871276778L;
        AncientCity city = new AncientCity(MCVersion.v1_21);
        AncientCityGenerator gen = new AncientCityGenerator();
        ChunkRand rand = new ChunkRand();
        gen.generate(worldseed, -33, -12, rand);

        for (AncientCityGenerator.Piece piece : gen.pieces) {
            BPos posMin = new BPos(piece.box.minX, piece.box.minY, piece.box.minZ);
            BPos posMax = new BPos(piece.box.maxX, piece.box.maxY, piece.box.maxZ);
            System.out.println("-- " + piece.getName() + "\nmin: /tp " + posMin.getX() + " " + posMin.getY() + " " + posMin.getZ());
            System.out.println("max: /tp " + posMax.getX() + " " + posMax.getY() + " " + posMax.getZ());
        }
    }

    @Test
    public void correctnessSmall1() {
        // TODO
        long worldseed = 61359933871276778L;
        AncientCity city = new AncientCity(MCVersion.v1_21);
        AncientCityGenerator gen = new AncientCityGenerator();
        ChunkRand rand = new ChunkRand();

        assertEquals(
                city.getInRegion(worldseed, -2, -1, rand),
                new CPos(-33, -12)
        );

        gen.generate(worldseed, -33, -12, rand);
        var loot = gen.getChestsWithLootSeeds();

        // /setblock -500 -44 -93 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:997619888727288672L}
        assertFalse(loot.stream()
                .filter(t -> t.getFirst().equals(new BPos(-500, -44, -93)))
                .noneMatch(t -> true)
        );

        // /setblock -507 -50 -113 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city_ice_box",LootTableSeed:2612999286728812835L}
        assertFalse(loot.stream()
                .filter(t -> t.getFirst().equals(new BPos(-507, -50, -113)))
                .noneMatch(t -> true)
        );

        // /setblock -539 -48 -127 minecraft:chest[facing=north,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-6600788429441983510L}
        assertFalse(loot.stream()
                .filter(t -> t.getFirst().equals(new BPos(-539, -48, -127)))
                .noneMatch(t -> true)
        );

        // /setblock -607 -37 -211 minecraft:chest[facing=south,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:3355793389309942570L}
        assertFalse(loot.stream()
                .filter(t -> t.getFirst().equals(new BPos(-607, -37, -211)))
                .noneMatch(t -> true)
        );

        // TODO find out why this fails
        // FIXME FAILS IN ALL VERSIONS !!!!!!!!!!!!!!!!!
        // /setblock -468 -50 -285 minecraft:chest[facing=north,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-31581764618057220L}
        assertFalse(loot.stream()
                .filter(t -> t.getFirst().equals(new BPos(-468, -50, -285)))
                .noneMatch(t -> true)
        );
    }

    @Test
    public void correctnessSmall2() {
        // TODO
    }
}
