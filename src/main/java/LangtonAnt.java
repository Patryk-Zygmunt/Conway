import java.util.*;

/**
 * Created by Linus on 09.12.2016.
 */
public class LangtonAnt extends Automation2Dim {
    private List<Integer> liveStayLive  ;
    private List<Integer>  deadAlive  ;
    @Override
    protected Automaton newInstance(CellStateFactory csf, CellNeighborhood cn) {
        Automaton game=new LangtonAnt(cn.getWidth(),cn.getHeight());

        game.stateFactory=csf;
        game.neighborsStrategy=cn;
        game.cells=new TreeMap<CellCoordinates, CellState>();
        CellCoordinates c1= game.initialCoordinatesd();
        int i=0;
        while(game.hasNextCoordinates(c1)){

            game.cells.put(nextCoordinatesd(c1),game.stateFactory.initialState(nextCoordinatesd(c1)));
            c1=nextCoordinatesd(c1);
        }//

        return game;
    }
LangtonAnt(int x,int y){
        super(x,y);
}


    @Override
    protected CellState nextCellState(Cell cell, Set<Cell> neighborsStates) {
                        cell.state=((LangtonCell)cell.state);

                         LangtonCell antState=new LangtonCell(BinaryState.DEAD,0,AntState.NONE);
                         LangtonCell nextState=new LangtonCell();

                         Coords2D antCoords=new Coords2D(0,0);
        for(Cell cc: neighborsStates){
            if((((LangtonCell)cc.state).antState!=AntState.NONE)){

                antState=(LangtonCell)cc.state;

                antCoords=(Coords2D)cc.coords;

            }}
            if((antState.antState!=AntState.NONE)&&((antCome(antCoords,(Coords2D)cell.coords)).equals(antState.antState))){
                nextState.antId=antState.antId;

                 if((((LangtonCell) cell.state).cellState)==BinaryState.ALIVE){
                     nextState.cellState=BinaryState.DEAD;
                      nextState.antState=turnRight(antState.antState);
                 }
                if((((LangtonCell) cell.state).cellState)==BinaryState.DEAD){
                    nextState.cellState=BinaryState.ALIVE;
                    nextState.antState=turnLeft(antState.antState);
                }
        return nextState;
        }
                else {

                 nextState.antState=AntState.NONE;
                 nextState.cellState=((LangtonCell) cell.state).cellState;
                 nextState.antId=((LangtonCell) cell.state).antId;
                 return nextState;
                 }
            }

    @Override
    public void nextRules(List<Integer> live, List<Integer> dead) {

    }


    private AntState antCome(Coords2D antCoords,Coords2D cellCoords){
           if((antCoords.x==cellCoords.x)&&(antCoords.y>cellCoords.y))
               return AntState.NORTH;
        if((antCoords.x==cellCoords.x)&&(antCoords.y<cellCoords.y))
            return AntState.SOUTH;
        if((antCoords.x>cellCoords.x)&&(antCoords.y==cellCoords.y))
            return AntState.WEST;
        if((antCoords.x<cellCoords.x)&&(antCoords.y==cellCoords.y))
            return AntState.EAST;
        return AntState.NONE;
    }

    private AntState turnRight(AntState antState){
        if(antState==AntState.NORTH)
            return AntState.WEST;
        if(antState==AntState.SOUTH)
            return AntState.EAST;
        if(antState==AntState.EAST)
            return AntState.NORTH;
        if(antState==AntState.WEST)
            return AntState.SOUTH;
        System.out.println("NIe ustalono turn right");
        return AntState.NONE;
    }


    private AntState turnLeft(AntState antState){
        if(antState==AntState.NORTH)
            return AntState.EAST;
        if(antState==AntState.SOUTH)
            return AntState.WEST;
        if(antState==AntState.EAST)
            return AntState.SOUTH;
        if(antState==AntState.WEST)
            return AntState.NORTH;
        System.out.println("NIe ustalono turn left");
        return AntState.NONE;
    }


}




