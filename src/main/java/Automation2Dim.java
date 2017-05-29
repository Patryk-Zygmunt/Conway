/**
 * Created by Linus on 12.11.2016.
 */
public abstract class Automation2Dim extends Automaton {
    public int width ;
    public int height ;

    public Automation2Dim() {


    }

    public Automation2Dim(int x,int y) {
        width = x;
        height = y;

    }


    protected boolean hasNextCoordinates(CellCoordinates cc) {
        if ((((Coords2D) cc).x == width) && (((Coords2D) cc).y == height)) return false;
        else return true;


    }

    protected CellCoordinates initialCoordinatesd() {
        return new Coords2D(-1, 0);

    }

    public boolean isRight(CellCoordinates cc) {
        int y = ((Coords2D) cc).y;
        int x = ((Coords2D) cc).x;
        if ((x < 0) || (y < 0)) return false;
        if (x > width) return false;
        if (y > height) return false;
        return true;
    }


    protected CellCoordinates nextCoordinatesd(CellCoordinates cc) {
        int y = ((Coords2D) cc).y;
        int x = ((Coords2D) cc).x;
        if (hasNextCoordinates(cc)) {
            if (x >= width) {
                return new Coords2D(0, y + 1);
            }
            else if (x < width) {

                return new Coords2D(x + 1, y);
            }

        }
        return null;

    }



}