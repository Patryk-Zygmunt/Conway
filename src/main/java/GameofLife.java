import java.util.*;

/**
 * Created by Linus on 12.11.2016.
 */
public class GameofLife extends Automation2Dim {
    private List<Integer>  liveStayLive  ;
private List<Integer>  deadAlive  ;


    public GameofLife(){


    }


    public GameofLife(int x,int y){
            super(x,y);
        liveStayLive=new ArrayList<Integer>();
        deadAlive=new ArrayList<Integer>();
        liveStayLive.add(3);
      deadAlive.add(3);
      liveStayLive.add(2);
    }


    protected CellState nextCellState(Cell cell, Set<Cell> neighborsStates) {
        int liveNeighbors = 0,deadNeighbors=0;
        int toLive=3,toDead=3;

       System.out.println("neighbours>>"+neighborsStates);
             for(Cell cc: neighborsStates){
            if(cc.state==(BinaryState.ALIVE))
                ++liveNeighbors;
            if(cc.state==(BinaryState.DEAD))
                ++deadNeighbors;}
        if(cell.state==BinaryState.ALIVE){
            if((liveNeighbors==3)||(liveNeighbors==2)){
                return cell.state;
            }
           else{
            return BinaryState.DEAD;
           }
       }
        if(cell.state==(BinaryState.DEAD)) {
            if (liveNeighbors==3) {
                return BinaryState.ALIVE;
            } else {
                return cell.state;
            }
        }

        return null;
        }

        public void nextRules(List<Integer> live,List<Integer> dead){

          //  System.out.println("11 live="+live);
            liveStayLive=new ArrayList<>(live);
            deadAlive=new ArrayList<>(dead);
           // System.out.println("22 live="+liveStayLive);
        }



private boolean liveisLive(int n){
  for(Integer i: liveStayLive){
      if(i.equals(n))
          return true;
  }
  return false;
}

    private boolean deadisLive(int n){
        for(Integer i: deadAlive){
            if(i.equals(n))
                return true;
        }
        return false;
    }



    protected  Automaton newInstance(CellStateFactory csf,CellNeighborhood cn){
        Automaton game=new GameofLife(cn.getWidth(),cn.getHeight());

        game.stateFactory=csf;
        game.neighborsStrategy=cn;


        game.cells=new TreeMap<CellCoordinates, CellState>();

      CellCoordinates c1= game.initialCoordinatesd();
        while(game.hasNextCoordinates(c1)){
            game.cells.put(nextCoordinatesd(c1),game.stateFactory.initialState(nextCoordinatesd(c1)));
            c1=game.nextCoordinatesd(c1);
        }//System.out.print(cells);

return game;
}
}