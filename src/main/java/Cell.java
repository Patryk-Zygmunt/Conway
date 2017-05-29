/**
 * Created by Linus on 11.11.2016.
 */
public class Cell {
    public CellState state;
    public CellCoordinates coords;
    Cell(CellCoordinates coords,CellState state){
        this.state=state;
        this.coords=coords;
    }
    Cell(){}

    @Override
    public String toString() {
        return "Cell{" +
                "state=" + state +
                ", coords=" + coords +
                '}';
    }




}
