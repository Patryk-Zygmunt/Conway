import java.util.*;

/**
 * Created by Linus on 11.11.2016.
 */
public class VonNeumanNeighborhood implements CellNeighborhood {
    private int r=1;
    private int width;
    private int height;
    private List<Integer> liveStayLive  ;
    private List<Integer>  deadAlive  ;

    public void setSize(int x, int y) {
        if ((x > 2) && (y > 2)) {
            width = x;
            height = y;

        }
        else{
            width=10;
            height=10;
        }
    }
    public VonNeumanNeighborhood(){
        liveStayLive=new ArrayList<>();
        deadAlive=new ArrayList<>();
    }

    public void setRules(String live,String dead){
        if((((live.isEmpty())||(dead.isEmpty()))==false)){
            for(int i =0;i<live.length();i++){
                liveStayLive.add( Character.getNumericValue(live.charAt(i)));
            }
            for(int i =0;i<dead.length();i++){
                deadAlive.add( Character.getNumericValue(dead.charAt(i)));
            }}
        else{
            liveStayLive.add(3);
            deadAlive.add(3);
            liveStayLive.add(2);
        }

    }
    public List<Integer> getLiveStayLive() {
        return liveStayLive;
    }

    public List<Integer> getDeadAlive() {
        return deadAlive;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    public Set<CellCoordinates> cellNeighbors(CellCoordinates cell) {

 if(cell instanceof Coords2D){
     Set<CellCoordinates> neighbors=new LinkedHashSet<CellCoordinates>();
     int x = ((Coords2D) cell).x;
     int y = ((Coords2D) cell).y;
     Coords2D[] neighbours2D=new Coords2D[4];
     for(int i=0;i<4;i++)
         neighbours2D[i]=new Coords2D();
     for(int i=0;i<2;i++){
         neighbours2D[i].x=x;
         neighbours2D[i+2].y=y;
     }
     neighbours2D[0].y=y-r;
     neighbours2D[1].y=y+r;
     neighbours2D[2].x=x+r;
     neighbours2D[3].x=x-r;
     for(int i=0;i<4;i++)
     {neighbors.add(neighbours2D[i]);

     }
     return neighbors;
 }

  return null;
    }
}
