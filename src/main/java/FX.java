

import com.sun.javafx.scene.control.skin.ChoiceBoxSkin;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;


import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static java.lang.Integer.parseInt;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;


public class FX extends Application  {
    private Scene scene;
    private GridPane grid;
    private Button bg;
    private Button bm;
    private Button bw;
    private Button startButton;

    private   Timeline time;
    private TextField sizex,sizey;
    private TextField live,dead;
      private boolean stop;
    private HBox hBoxPane;
    private HBox hBoxPaneRight;
    private ChoiceBox  choiceSize;
     private CellNeighborhood cn;

    GenaralStateFactory gnf;
    Automaton gra;
    private boolean initGameOfLife;
    private boolean initLangtonAnt;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game of Life");
         time= new Timeline();
         stop=false;
         initGameOfLife=false;
         initLangtonAnt=false;

cn=new MoorNeighborhood();


            scene=createScene();


        scene.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                           sceneClicked(e.getX(),e.getY());

                    }
                });
        bg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                      {
                          startGameofLife();

                     }


            }
        });


        bm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                {


                        startLangtonAnt();


                }


            }
        });

        choiceSize.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue observable,
                              Object oldValue, Object newValue) -> {
            gnf=new GenaralStateFactory(new Patterns().drawPattern(newValue.toString()),BinaryState.ALIVE);
            gra=gra.newInstance(gnf,cn);



                });


        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                time.play();

            }});


        bw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                time.stop();

            }});






        primaryStage.setScene(scene);

        primaryStage.show();



    }

    private KeyFrame createKeyFrame(int delay)
    {
        return new KeyFrame(Duration.millis(delay), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gra = gra.nextState();
                grid.getChildren().add(play(gra));
                                System.out.println(gra.cells);


            }
        });
    }
    private KeyFrame createKeyFrameLangtonAnt(int delay)
    {
        return new KeyFrame(Duration.millis(delay), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gra = gra.nextState();
                System.out.println(gra.cells);
                grid.getChildren().add(playLangtonAnt(gra));

            }
        });
    }



    private void sceneClicked(double x, double y){
        if(initGameOfLife==true){

            gnf.addNew(clickedCoords(x,y),BinaryState.ALIVE);

            gra=gra.newInstance(gnf,cn);
            grid.getChildren().add(play(gra));}
        if(initLangtonAnt==true){
            gnf.addNew(clickedCoords(x,y),new LangtonCell(BinaryState.ALIVE,0,AntState.NORTH));

            gra=gra.newInstance(gnf,cn);
            grid.getChildren().add(playLangtonAnt(gra));}


    }

    private void startGameofLife(){

            gnf=new GenaralStateFactory();
                cn=new MoorNeighborhood();
                cn.setSize((parseInt(sizex.getText())),parseInt(sizey.getText()));
///
           cn.setRules(live.getText(),dead.getText());

           gra=new GameofLife(cn.getWidth(),cn.getHeight());
            gra=gra.newInstance(gnf, cn);




           initGameOfLife=true;
           initLangtonAnt=false;
           grid.getChildren().add(playLangtonAnt(gra));

        time.setCycleCount(Timeline.INDEFINITE);
        time.getKeyFrames().add(createKeyFrame(500));

    }

private void startLangtonAnt(){

            Set<CellCoordinates> s=new TreeSet<>();
            gnf=new GenaralStateFactory (s,new LangtonCell(BinaryState.DEAD,0,AntState.NONE));
            cn=new VonNeumanNeighborhood();
            cn.setSize((Integer.valueOf(sizex.getText())),Integer.valueOf(sizey.getText()));
            gra=new LangtonAnt(cn.getWidth(),cn.getHeight());
            gra=gra.newInstance(gnf,cn);
            initLangtonAnt=true;
            initGameOfLife=false;
            grid.getChildren().add(playLangtonAnt(gra));

    time.setCycleCount(Timeline.INDEFINITE);
    time.getKeyFrames().add(createKeyFrameLangtonAnt(500));
    //time.play();

}




    private Group play(Automaton auto) {
        Automaton.CellIterator iter = auto.cellIterator();
        Group circle = new Group();
        while (iter.hasNext()) {

            Cell c = iter.next();
            int x = ((Coords2D) c.coords).x;
            int y = ((Coords2D) c.coords).y;

            Ellipse ellipse = new Ellipse();
            ellipse.setCenterX(x * 32);
            ellipse.setCenterY(y * 32);
            ellipse.setRadiusX(15.0f);

            ellipse.setRadiusY(15.0f);
            if (c.state==(BinaryState.ALIVE))
                ellipse.setFill(Color.AQUA);
            if (c.state==(BinaryState.DEAD))
                ellipse.setFill(Color.DARKBLUE);
            circle.getChildren().add(ellipse);
        }
        return circle;

    }

    private Group playLangtonAnt(Automaton auto) {
        Automaton.CellIterator iter = auto.cellIterator();
        Group circle = new Group();

        while (iter.hasNext()) {

            Cell c = iter.next();
            if(c.state instanceof  LangtonCell){
            int x = ((Coords2D) c.coords).x;
            int y = ((Coords2D) c.coords).y;


            Ellipse ellipse = new Ellipse();
            ellipse.setCenterX(x * 32);
            ellipse.setCenterY(y * 32);
            ellipse.setRadiusX(15.0f);

            ellipse.setRadiusY(15.0f);
            if (((LangtonCell)c.state).cellState ==(BinaryState.ALIVE))
                ellipse.setFill(Color.AQUA);
            if (((LangtonCell)c.state).cellState==(BinaryState.DEAD))
                ellipse.setFill(Color.DARKBLUE);
            if(((LangtonCell)c.state).antState!=AntState.NONE)
                ellipse.setFill(Color.DEEPPINK);

            circle.getChildren().add(ellipse);
        }}
        return circle;

    }

    public int getInteger(String no) {
        if (no != null) {
            return Integer.parseInt(no);
        } else {
            return 0;
        }
    }





  private  Scene createScene(){

      grid = new GridPane();
      grid.setAlignment(Pos.CENTER);
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(25, 25, 25, 25));
      bg = new Button("Game of Life");
      bm = new Button("Langton's Ant");
      bw = new Button("Stop");

      sizex=new TextField("width");
      sizey=new TextField("height");
      live=new TextField("live");
      dead=new TextField("dead");
      choiceSize = new ChoiceBox(FXCollections.observableArrayList("Glider","Blinker","Boat","Gun"));
      startButton = new Button("Start");
      hBoxPane = new HBox(10);
      hBoxPaneRight = new HBox(10);
      hBoxPane.setAlignment(Pos.BOTTOM_CENTER);
      hBoxPaneRight.setAlignment(Pos.CENTER_RIGHT);
      hBoxPaneRight.getChildren().add(choiceSize);
      hBoxPaneRight.getChildren().add(live);
      hBoxPaneRight.getChildren().add(dead);
      hBoxPaneRight.getChildren().add(sizex);
      hBoxPaneRight.getChildren().add(sizey);
      hBoxPane.getChildren().add(bm);
      hBoxPane.getChildren().add(bg);
      hBoxPane.getChildren().add(bw);
      hBoxPane.getChildren().add(startButton);

      grid.add(hBoxPane, 1, 6);
      grid.add(hBoxPaneRight,1,8);
      return new Scene(grid, 1300, 900, Color.DARKSLATEBLUE);


  }


    public CellCoordinates clickedCoords(double mx,double my){
        int x= (int)((mx-43)/32);
        int y= (int)((my-132)/32);
        return new Coords2D(x,y);
    }





    public static void main(String[] args) {
        launch(args);
    }

  /*  @Override
    public void changed(ObservableValue<? extends Toggle> ov, Toggle oldRB, Toggle currentRB)
    {
        RadioButton rb = (RadioButton)currentRB;



        public void handle(Event event)
        {

        }
}*/
}