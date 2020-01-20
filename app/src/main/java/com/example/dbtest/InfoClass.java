package com.example.dbtest;


public class InfoClass {
    public String _id;
    public String NAME;
    public String CUP;
    public String INGREDIENT1;
    public String INGREDIENT2;
    public String INGREDIENT3;
    public String INGREDIENT4;
    public String INGREDIENT5;

    public InfoClass(int id, String name, String contact, String email){}

    public InfoClass(String _id , String NAME , String CUP , String INGREDIENT1 , String INGREDIENT2 , String INGREDIENT3 , String INGREDIENT4 , String INGREDIENT5){
        this._id = _id;
        this.NAME = NAME;
        this.CUP = CUP;
        this.INGREDIENT1 = INGREDIENT1;
        this.INGREDIENT2 = INGREDIENT2;
        this.INGREDIENT3 = INGREDIENT3;
        this.INGREDIENT4 = INGREDIENT4;
        this.INGREDIENT5 = INGREDIENT5;
    }

}