import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Linus on 12.11.2016.
 */
public class VonNeumanNeighborhoodTest {
    @Test
    public void cellNeighbors2D() {
       Coords2D c=new Coords2D();

        c.x=3;c.y=3;
        Coords2D cTest=new Coords2D();
        cTest.x=2;cTest.y=3;

        VonNeumanNeighborhood von=new VonNeumanNeighborhood();
       Set<CellCoordinates> vonCoords= new HashSet<CellCoordinates>();
       Set<CellCoordinates> testCoords= new HashSet<CellCoordinates>();
        vonCoords.addAll(von.cellNeighbors(c));

     Assert.assertTrue(vonCoords.contains());

    }



    }

