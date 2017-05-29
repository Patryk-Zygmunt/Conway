import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Linus on 10.12.2016.
 */
public class Patterns {








    private Set<CellCoordinates> blinker;
    private Set<CellCoordinates> glider;
    private Set<CellCoordinates> boat;
    private Set<CellCoordinates> gun;


    public Set<CellCoordinates>  drawPattern(String s){
                 if(s.equals("Blinker"))
                return createBlinker();
        if(s.equals("Boat"))
            return createBoat();
        if(s.equals("Glider"))
            return createGlider();

        return null;
        }







public  Set<CellCoordinates> createBlinker(){
    blinker=new TreeSet<>();
    blinker.add(new Coords2D(5,4));
    blinker.add(new Coords2D(5,5));
    blinker.add(new Coords2D(5,6));
return blinker;
}
public Set<CellCoordinates> createGlider(){
    glider=new TreeSet<>();
    glider.add(new Coords2D(5,4));
    glider.add(new Coords2D(6,4));
    glider.add(new Coords2D(4,4));
    glider.add(new Coords2D(4,5));
    glider.add(new Coords2D(5,6));;
return glider;
}

public Set<CellCoordinates> createBoat(){
    boat=new TreeSet<>();
    boat.add(new Coords2D(5,4));
    boat.add(new Coords2D(4,4));
    boat.add(new Coords2D(4,5));
    boat.add(new Coords2D(6,5));
    boat.add(new Coords2D(5,6));
    return boat;
}


}
