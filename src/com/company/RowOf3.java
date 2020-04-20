package com.company;

class RowOf3 {
    public Tile[] tiles;
    public RowOf3(Tile... tiles){
        this.tiles = tiles;
    }

    public boolean isComplete(){
        boolean isFull = false;
         if(tiles[0].getValue().isEmpty()){
             return false;
         }



             return  tiles[0].getValue().equals(tiles[1].getValue()) && tiles[0].getValue().equals(tiles[2].getValue());
    }
}
