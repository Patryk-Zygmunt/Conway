import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Linus on 11.11.2016.
 */
public class MoorNeighborhood implements CellNeighborhood {
    private int r=1;
    private List<Integer> liveStayLive  ;
    private List<Integer>  deadAlive  ;
      public int width;
      public int height;

      public MoorNeighborhood(){
          liveStayLive=new ArrayList<>();
          deadAlive=new ArrayList<>();
      }




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
        Set<CellCoordinates> neighbors=new HashSet<CellCoordinates>();
        if(cell instanceof Coords2D){
            int x = ((Coords2D) cell).x;
            int y = ((Coords2D) cell).y;
            Coords2D n=new Coords2D();
            n.x=x;

            Coords2D[] neighbours2D=new Coords2D[8];

            for(int i=0;i<8;i++)
            neighbours2D[i]=new Coords2D();

            for(int i=0;i<3;i++){
                neighbours2D[i].x=x-r;
                neighbours2D[i+5].x=x+r;
            }
            neighbours2D[0].y=y-r;
            neighbours2D[1].y=y;
            neighbours2D[2].y=y+r;
            neighbours2D[3].x=x;
            neighbours2D[3].y=y+r;
            neighbours2D[4].x=x;
            neighbours2D[4].y=y-r;
            neighbours2D[5].y=y-r;
            neighbours2D[6].y=y;
            neighbours2D[7].y=y+r;
            for(int i=0;i<8;i++)
            {neighbors.add(neighbours2D[i]);
            }
            return neighbors;
        }

        return null;
    }




}