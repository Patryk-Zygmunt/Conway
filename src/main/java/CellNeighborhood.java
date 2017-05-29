import java.util.List;
import java.util.Set;

/**
 * Created by Linus on 11.11.2016.
 */
public interface CellNeighborhood {
    Set<CellCoordinates> cellNeighbors (CellCoordinates cell);
    public int getHeight() ;


    public int getWidth() ;

    public void setSize(int x, int y);
    public void setRules(String live,String dead);
    public List<Integer> getLiveStayLive() ;


    public List<Integer> getDeadAlive();


    }



