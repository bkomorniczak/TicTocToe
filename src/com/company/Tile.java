package com.company;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class Tile extends StackPane {
    private final Main main;
    private Text text = new Text();
    public String getValue(){
        return  text.getText();
    }
    public Tile(Main main){
        this.main = main;
        Rectangle border = new Rectangle(200,200);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        text.setFont(Font.font(72));

        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);
        setOnMouseClicked(event -> {
            if(!main.playable){
                return;
            }
            if(main.turnX){
                drawX();
                main.turnX = false;
                main.checkState();

            }else if(!main.turnX){
                drawO();
                main.turnX = true;
                main.checkState();
            }
        });
    }
    public double getCenterX(){
        return getTranslateX() + 100;
    }

    public double getCenterY(){
        return getTranslateY() + 100;
    }
    private void drawX(){
        text.setText("X");
    }
    private void drawO(){
        text.setText("O");
    }
}
