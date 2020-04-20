package com.company;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Main extends Application {
    public boolean turnX = true;
    public boolean playable = true;
    private Tile[][] board = new Tile[3][3];
    private List<RowOf3> rowOf3List = new ArrayList<>();
    private  Pane pane = new Pane();
    private Tile tile;
    private RowOf3 rowOf3;
    Stage window;

    public static void main(String[] args) { launch(args);
    }

    public void checkState(){
        boolean boardFull = true;
        for(RowOf3  rowOf3 : rowOf3List) {
            if (rowOf3.isComplete()) {
                playable = false;
                playEndGame(rowOf3);
                gameOver();
            }
        }

            for(int y =0; y <3; y++){
                for(int x =0; x<3; x++){
                    Tile tile = board[x][y];
                    if(tile.getValue().isEmpty()){
                        boardFull = false;
                    }
                }
        }
        if(boardFull){
            gameOver();

        }

    }

    private void gameOver(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Again?");
        alert.setHeaderText("GAME OVER!");
        alert.setHeaderText("Do you wanna play again?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){


        }else {
            window.close();
        }

    }

    private void playEndGame(RowOf3 rowOf3){
        Line line = new Line();
        line.setStartX(rowOf3.tiles[0].getCenterX());
        line.setStartY(rowOf3.tiles[0].getCenterY());
        line.setEndX(rowOf3.tiles[0].getCenterY());
        line.setEndY(rowOf3.tiles[0].getCenterY());
        line.setStroke(Color.RED);
        line.setStrokeWidth(3);
        pane.getChildren().add(line);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new KeyValue(line.endXProperty(), rowOf3.tiles[2].getCenterX()),
                new KeyValue(line.endYProperty(), rowOf3.tiles[2].getCenterY())));
        timeline.play();



    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setScene(new Scene(createContent()));
        window.show();
    }
    private Parent createContent(){

        pane.setPrefSize(600,600);

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                Tile tile = new Tile(this);
                tile.setTranslateX(j*200);
                tile.setTranslateY(i*200);

                pane.getChildren().add(tile);
                board[j][i] = tile;
            }
        }
        for(int y = 0; y<3; y++){
            rowOf3List.add(new RowOf3(board[0][y],board[1][y],board[2][y]));
        }
        for(int x = 0; x<3; x++){
            rowOf3List.add(new RowOf3(board[x][0],board[x][1],board[x][2]));
        }
        rowOf3List.add(new RowOf3(board[0][0],board[1][1], board[2][2]));
        rowOf3List.add(new RowOf3(board[2][0],board[1][1], board[0][2]));

        return pane;
    }

}
