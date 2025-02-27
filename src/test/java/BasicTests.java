import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import kludwisz.ancientcity.AncientCity;
import kludwisz.ancientcity.AncientCityGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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

    // TODO correctness test #1, #2
    @Test
    public void correctnessSmall1() {

    }

    @Test
    public void correctnessSmall2() {

    }
}
