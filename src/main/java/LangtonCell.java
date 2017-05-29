import java.util.ArrayList;

/**
 * Created by Linus on 09.12.2016.
 */
public class LangtonCell implements CellState {
    public  BinaryState cellState ;
    public int antId;
    public AntState antState;

    public LangtonCell(BinaryState cellState, int antId, AntState antState) {
        this.cellState = cellState;
        this.antId = antId;
        this.antState = antState;
    }

    public LangtonCell() {
    }
    public void nextRules(ArrayList<Integer> live, ArrayList<Integer> dead){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LangtonCell that = (LangtonCell) o;

        return cellState == that.cellState;
    }

    @Override
    public int hashCode() {
        return cellState != null ? cellState.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "LangtonCell{" +
                "cellState=" + cellState +
                ", antId=" + antId +
                ", antState=" + antState +
                '}';
    }
}
