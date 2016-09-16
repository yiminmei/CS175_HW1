package com.wearable.whatsfordinner;

import android.os.Parcelable;

/**
 * Created by yiminmei on 9/9/16.
 */

public class Dish {
    private String name;
    private String[] ingredients= new String[10];;
    private double[] ingredientsAmount=new double[10];;
    private String directions="Directions to be entered";
    private String[] empty1={"", "" , "","" ,"","", "","","",""};
    private double[] empty2={0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

    public Dish(String name1, String[] ingredients1,double[] ingredientsAmount1){
        setIngredients(ingredients1);
        setIngredientsAmount(ingredientsAmount1);
        name= name1;
    }
    public Dish(){
        ingredients=  new String[10];
        ingredientsAmount= new double[10];
        setIngredientsAmount(empty2);
        setIngredients(empty1);
    }

    public String getName(){
        return name;
    }
    public void setName(String newname){
        name=newname;
    }

    public void setIngredients(String[] newingredients ) {
        for (int a = 0; a < 10; a++) {
            ingredients[a] = newingredients[a];
        }
    }
    public void setIngredientsAmount(double[] ingredientsAmount1){
        for (int a = 0; a < 10; a++) {
            ingredientsAmount[a] = ingredientsAmount1[a];
        }
    }
    public String[] getIngredients(){
        return ingredients;
    }
    public void setDirections(String newdirections){
       directions = newdirections;
    }
    public String getDirections(){
        return directions;
    }


    public double[] getIngredientsAmount(){
        return ingredientsAmount;

    }
    public String toString(){
        String result="";
        result+=name;
        result+="ingredients: ";
        for(int a =0;a<=9 ;a++ ){
           result+= ""+ingredientsAmount[a]+ingredients[a]+" ";
        }

        return result;

    }









}
