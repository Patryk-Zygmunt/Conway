import java.util.*;


public abstract class Automaton {
   public Map<CellCoordinates,CellState> cells;

    public Automaton(){}

    public Automaton(Map<CellCoordinates, CellState> cells, CellNeighborhood neighborsStrategy, CellStateFactory stateFactory) {
        this.cells = cells;
        this.neighborsStrategy = neighborsStrategy;
        this.stateFactory = stateFactory;


    }
    protected List<Integer>  liveStayLive  ;
    protected List<Integer>  deadAlive  ;
   public CellNeighborhood neighborsStrategy;
    public CellStateFactory stateFactory;
    class CellIterator{


        private CellCoordinates currentCoords;
        public CellIterator(){
            currentCoords=initialCoordinatesd();
        }
        public boolean hasNext(){return hasNextCoordinates(currentCoords);
        }
        public  Cell next(){
                    currentCoords=nextCoordinatesd(currentCoords);
                    return new Cell(currentCoords,cells.get(currentCoords));

        }
        public void setState(CellState state){

            cells.put(currentCoords,state);

        };
    }
 public CellIterator cellIterator(){
     return new CellIterator();
 }
    public abstract boolean isRight(CellCoordinates cc);
        protected abstract Automaton newInstance (CellStateFactory csf,CellNeighborhood cn);
 protected abstract boolean hasNextCoordinates (CellCoordinates cc);
 protected abstract CellCoordinates initialCoordinatesd();
 protected abstract CellCoordinates nextCoordinatesd(CellCoordinates cc);
 protected abstract CellState nextCellState(Cell cell,Set<Cell> neighborsStates);
  //  public abstract void setSize(int x,int y);
  //  public abstract void setRules(String live,String dead);
    public abstract void nextRules(List<Integer> live,List<Integer> dead);



        public void setNeighborsStrategy(CellNeighborhood neighborsStrategy) {
        this.neighborsStrategy = neighborsStrategy;
    }


      public Set<Cell> mapCoordinates(Set<CellCoordinates> cellCoords) {
            Set<Cell>  tmp = new HashSet<Cell>();
          System.out.print(" ccs"+cellCoords);
            for (CellCoordinates coords : cellCoords) {
                if(isRight(coords))tmp.add(new Cell(coords,cells.get(coords)));
            }

          return tmp;


 }


 public Automaton nextState(){
     Automaton auto=newInstance(stateFactory,neighborsStrategy);

    Map<CellCoordinates,CellState> newCells=new TreeMap<CellCoordinates, CellState>() ;
CellIterator iter =new CellIterator();
auto.cells=newCells;
CellIterator newIter=auto.cellIterator();

     while(iter.hasNext()) {

         Cell c=iter.next();
        newIter.next();
         newIter.setState(nextCellState(c,mapCoordinates(neighborsStrategy.cellNeighbors(c.coords))));

     }

return auto;
     }

 public void insertStructure(Map<? extends CellState,? extends CellCoordinates> structure){}





}
