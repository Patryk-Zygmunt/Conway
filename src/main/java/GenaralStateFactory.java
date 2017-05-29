import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Linus on 11.11.2016.
 */
public class GenaralStateFactory implements CellStateFactory {
    private TreeMap<CellCoordinates, CellState> states;
    boolean langton;


    public GenaralStateFactory() {
        states=new TreeMap<>();
    }

    public  void addNew(CellCoordinates coords,CellState state){
        states.put(coords, state);
    }





    public GenaralStateFactory(Set<CellCoordinates> set,CellState state) {
        if(state instanceof LangtonCell)
            langton=true;

        states=new TreeMap<CellCoordinates, CellState>();
        for (CellCoordinates c : set) {
            states.put(c, state);
        }


    }

    ;

    public CellState initialState(CellCoordinates coord) {


                      if(states.containsKey(coord))
                          return (states.get(coord));
                          else {
                          if (langton == true)
                              return new LangtonCell(BinaryState.DEAD, 0, AntState.NONE);
                          else
                          return BinaryState.DEAD;
                      }
}


}