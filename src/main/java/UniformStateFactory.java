/**
 * Created by Linus on 14.11.2016.
 */
public class UniformStateFactory implements CellStateFactory {
    private CellState state;

    public UniformStateFactory(CellState state) {
        this.state = state;
    }

    public CellState initialState(CellCoordinates coords){
        return state;

    }


}
