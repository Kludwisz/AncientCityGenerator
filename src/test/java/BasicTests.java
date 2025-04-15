import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.util.data.Triplet;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.loot.LootTable;
import kludwisz.ancientcity.AncientCity;
import kludwisz.ancientcity.AncientCityGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    public void correctnessSmall1() {
        // random ancient city chest positions
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

        // /setblock -468 -50 -285 minecraft:chest[facing=north,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-31581764618057220L}
        assertFalse(loot.stream()
                .filter(t -> t.getFirst().equals(new BPos(-468, -50, -285)))
                .noneMatch(t -> true)
        );
    }

    @Test
    public void correctnessSmall2() {
        // random ancient city chest positions + loot seeds
        long worldseed = 5000697870809672599L;
        AncientCity city = new AncientCity(MCVersion.v1_21);
        AncientCityGenerator gen = new AncientCityGenerator();
        ChunkRand rand = new ChunkRand();

        assertEquals(
                city.getInRegion(worldseed, 0, -2, rand),
                new CPos(1, -41)
        );

        gen.generate(worldseed, 1, -41, rand);
        var loot = gen.getChestsWithLootSeeds();

        // /setblock 7 -50 -577 minecraft:chest[facing=west,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-7184813891189711778L}
        assertChest(loot, new BPos(7, -50, -577), -7184813891189711778L);
        // /setblock 33 -49 -755 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:1132600046979793898L}
        assertChest(loot, new BPos(33, -49, -755), 1132600046979793898L);
        // /setblock -55 -47 -632 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-1173594451207670982L}
        assertChest(loot, new BPos(-55, -47, -632), -1173594451207670982L);
        // /setblock -55 -47 -640 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-5373701976689819811L}
        assertChest(loot, new BPos(-55, -47, -640), -5373701976689819811L);
        // /setblock -62 -44 -635 minecraft:chest[facing=west,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-568790081121112006L}
        assertChest(loot, new BPos(-62, -44, -635), -568790081121112006L);
    }

    @Test
    public void correctnessSmall3() {
        // all chests in one ancient city
        long worldseed = -8694405306540134269L;
        AncientCity city = new AncientCity(MCVersion.v1_21);
        AncientCityGenerator gen = new AncientCityGenerator();
        ChunkRand rand = new ChunkRand();

        assertEquals(
                city.getInRegion(worldseed, -1, 0, rand),
                new CPos(-19, 4)
        );

        gen.generate(worldseed, -19, 4, rand);
        var loot = gen.getChestsWithLootSeeds();

        // /setblock -202 -50 76 minecraft:chest[facing=south,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:8218315167881478583L}
        assertChest(loot, new BPos(-202, -50, 76), 8218315167881478583L);
        // /setblock -219 -37 46 minecraft:chest[facing=west,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:588768277069284392L}
        assertChest(loot, new BPos(-219, -37, 46), 588768277069284392L);
        // /setblock -227 -49 83 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-8103229697490328365L}
        assertChest(loot, new BPos(-227, -49, 83), -8103229697490328365L);
        // /setblock -254 -48 105 minecraft:chest[facing=north,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-624049944036942089L}
        assertChest(loot, new BPos(-254, -48, 105), -624049944036942089L);
        // /setblock -278 -49 75 minecraft:chest[facing=south,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-3549612927538781450L}
        assertChest(loot, new BPos(-278, -49, 75), -3549612927538781450L);
        // /setblock -227 -47 4 minecraft:chest[facing=west,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:4196694653020924750L}
        assertChest(loot, new BPos(-227, -47, 4), 4196694653020924750L);
        // /setblock -227 -47 -4 minecraft:chest[facing=west,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:8639380539575688203L}
        assertChest(loot, new BPos(-227, -47, -4), 8639380539575688203L);
        // /setblock -250 -37 -1 minecraft:chest[facing=south,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:8058777421422835120L}
        assertChest(loot, new BPos(-250, -37, -1), 8058777421422835120L);
        // /setblock -287 -37 -1 minecraft:chest[facing=south,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-7774574220331383887L}
        assertChest(loot, new BPos(-287, -37, -1), -7774574220331383887L);
        // /setblock -295 -50 -18 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-7361936212358648578L}
        assertChest(loot, new BPos(-295, -50, -18), -7361936212358648578L);
        // /setblock -296 -49 -35 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:7577032192559292379L}
        assertChest(loot, new BPos(-296, -49, -35), 7577032192559292379L);
        // /setblock -362 -44 0 minecraft:chest[facing=north,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-7876892907112283635L}
        assertChest(loot, new BPos(-362, -44, 0), -7876892907112283635L);
        // /setblock -358 -50 -6 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-7613132446751699081L}
        assertChest(loot, new BPos(-358, -50, -6), -7613132446751699081L);
        // /setblock -357 -48 96 minecraft:chest[facing=north,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:7137616919584478034L}
        assertChest(loot, new BPos(-357, -48, 96), 7137616919584478034L);
        // /setblock -357 -48 114 minecraft:chest[facing=north,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-5355161875460436202L}
        assertChest(loot, new BPos(-357, -48, 114), -5355161875460436202L);
        // /setblock -397 -47 104 minecraft:chest[facing=south,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:762479875250931657L}
        assertChest(loot, new BPos(-397, -47, 104), 762479875250931657L);
        // /setblock -405 -47 104 minecraft:chest[facing=south,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-5062882202272870894L}
        assertChest(loot, new BPos(-405, -47, 104), -5062882202272870894L);
        // /setblock -399 -50 92 minecraft:chest[facing=west,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city_ice_box",LootTableSeed:2951677318240774366L}
        assertChest(loot, new BPos(-399, -50, 92), 2951677318240774366L);
        // /setblock -321 -49 124 minecraft:chest[facing=west,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:672618942715945606L}
        assertChest(loot, new BPos(-321, -49, 124), 672618942715945606L);
        // /setblock -324 -48 148 minecraft:chest[facing=north,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-8471459563858417963L}
        assertChest(loot, new BPos(-324, -48, 148), -8471459563858417963L);
        // /setblock -326 -50 161 minecraft:chest[facing=west,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city_ice_box",LootTableSeed:-3394910108882609242L}
        assertChest(loot, new BPos(-326, -50, 161), -3394910108882609242L);
        // /setblock -285 -44 109 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-2316816250189487402L}
        assertChest(loot, new BPos(-285, -44, 109), -2316816250189487402L);
    }

    @Test
    public void correctnessSmall4() {
        // all chests in one ancient city
        long worldseed = 5629499605952483L;
        AncientCity city = new AncientCity(MCVersion.v1_21);
        AncientCityGenerator gen = new AncientCityGenerator();
        ChunkRand rand = new ChunkRand();

        assertEquals(
                city.getInRegion(worldseed, 0, 0, rand),
                new CPos(11, 15)
        );

        gen.generate(worldseed, 11, 15, rand);
        var loot = gen.getChestsWithLootSeeds();

        // /setblock 200 -48 187 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:3355977973470222518L}
        assertChest(loot, new BPos(200, -48, 187), 3355977973470222518L);
        // /setblock 144 -48 147 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:8188004064560376794L}
        assertChest(loot, new BPos(144, -48, 147), 8188004064560376794L);
        // /setblock 112 -49 150 minecraft:chest[facing=north,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-8065620230336892132L}
        assertChest(loot, new BPos(112, -49, 150), -8065620230336892132L);
        // /setblock 108 -37 180 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-3029017634404009459L}
        assertChest(loot, new BPos(108, -37, 180), -3029017634404009459L);
        // /setblock 116 -49 223 minecraft:chest[facing=north,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-448443806436091715L}
        assertChest(loot, new BPos(116, -49, 223), -448443806436091715L);
        // /setblock 133 -50 222 minecraft:chest[facing=north,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:2817044650666382246L}
        assertChest(loot, new BPos(133, -50, 222), 2817044650666382246L);
        // /setblock 100 -48 250 minecraft:chest[facing=west,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:3733255357618154214L}
        assertChest(loot, new BPos(100, -48, 250), 3733255357618154214L);
        // /setblock 82 -48 250 minecraft:chest[facing=west,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:7521299613044481075L}
        assertChest(loot, new BPos(82, -48, 250), 7521299613044481075L);
        // /setblock 120 -49 281 minecraft:chest[facing=south,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:5807208324500262700L}
        assertChest(loot, new BPos(120, -49, 281), 5807208324500262700L);
        // /setblock 191 -50 318 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-8359398520650183157L}
        assertChest(loot, new BPos(191, -50, 318), -8359398520650183157L);
        // /setblock 195 -50 310 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-7622827582828302096L}
        assertChest(loot, new BPos(195, -50, 310), -7622827582828302096L);
        // /setblock 204 -50 309 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:8500817487952519723L}
        assertChest(loot, new BPos(204, -50, 309), 8500817487952519723L);
        // /setblock 233 -50 285 minecraft:chest[facing=north,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-9081806113123411631L}
        assertChest(loot, new BPos(233, -50, 285), -9081806113123411631L);
        // /setblock 239 -44 289 minecraft:chest[facing=west,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-8846764236100975304L}
        assertChest(loot, new BPos(239, -44, 289), -8846764236100975304L);
        // /setblock 270 -50 273 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:1080795490753163248L}
        assertChest(loot, new BPos(270, -50, 273), 1080795490753163248L);
        // /setblock 269 -47 259 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:4317814274566571275L}
        assertChest(loot, new BPos(269, -47, 259), 4317814274566571275L);
        // /setblock 269 -47 251 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-3681187241076557748L}
        assertChest(loot, new BPos(269, -47, 251), -3681187241076557748L);
        // /setblock 270 -50 199 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-2204578251156812784L}
        assertChest(loot, new BPos(270, -50, 199), -2204578251156812784L);
        // /setblock 234 -48 221 minecraft:chest[facing=east,type=single,waterlogged=false]{LootTable:"minecraft:chests/ancient_city",LootTableSeed:-5389396639414360631L}
        assertChest(loot, new BPos(234, -48, 221), -5389396639414360631L);
    }

    private void assertChest(List<Triplet<BPos, LootTable, Long>> loot, BPos pos, long lootseed) {
        assertFalse(loot.stream()
                .filter(t -> t.getFirst().equals(pos))
                .filter(t -> t.getThird() == lootseed)
                .noneMatch(t -> true)
        );
    }
}
