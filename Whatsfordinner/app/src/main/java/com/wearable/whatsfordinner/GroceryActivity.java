package com.wearable.whatsfordinner;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class GroceryActivity extends ListActivity {
    private ArrayList<Dish> selecteddishes;
    private ArrayList<String> allIngredients;
    private String[] needtobuy;
    private int changedlocation=-1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==9999){
            if(resultCode==RESULT_OK){
                needtobuy[changedlocation]=data.getStringExtra("info");
                setListAdapter(new ArrayAdapter<String>(this, R.layout.content_grocery, needtobuy));
            }


        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        //setContentView(R.layout.activity_grocery);
        Intent intent = getIntent();
        TextView title = new TextView(this);
        title.setGravity(0x11);
        title.setText("Grocery List");
        title.setTextSize(40);
        title.setTextColor(Color.BLACK);
        title.setBackgroundColor(Color.WHITE);
        ListView listview = getListView();
        listview.setTextFilterEnabled(true);
        listview.addHeaderView(title);

        final int length = intent.getIntExtra("selectedlength", 0);
        selecteddishes = new ArrayList<>();
        ArrayList<String> needtobuylist = new ArrayList<>();

        for (int a = 0; a < length; a++) {
            String name = intent.getStringExtra("name" + a);
            String[] ingredientslist = intent.getStringArrayExtra("ingredientslist" + a);
            double[] amountofingredients = intent.getDoubleArrayExtra("amountofingredients" + a);
            selecteddishes.add(new Dish(name, ingredientslist, amountofingredients));

            for (int d = 0; d < 10; d++) {
                double amount= amountofingredients[d];

                if (amount > 0.0 && !ingredientslist[d].contains("Choose")) {
                    needtobuylist.add(""+ amountofingredients[d] +" " +ingredientslist[d]);

                 }
            }

        }

        ArrayList<String> uniqueset= new ArrayList<>();
        for(int a =1 ;a<16;a++){
            String ingredient= allIngredients.get(a);
            int length2= needtobuylist.size();
            int totalamount=0;
            for(int b = 0  ; b<length2;b++){

                if(needtobuylist.get(b).contains(ingredient)){
                    int index= needtobuylist.get(b).indexOf(" ");

                    totalamount+= Double.parseDouble(needtobuylist.get(b).substring(0,index));
                }
            }
            if(totalamount!=0)
                uniqueset.add(""+totalamount+" "+ingredient);
        }



        final int length1=uniqueset.size();
         needtobuy= new String[length1+1];
        for(int a =0;a<length1;a++){
            needtobuy[a]= uniqueset.get(a);
        }
        needtobuy[length1]="<------------Done------------->";
        setListAdapter(new ArrayAdapter<String>(this, R.layout.content_grocery, needtobuy));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                }
                else if(position==length1+1){
                    Intent intent= new Intent(GroceryActivity.this, MenuActivity.class  );
                    intent.putExtra("length",needtobuy.length);
                    intent.putExtra("ingredientstobuy", needtobuy);
                    setResult(GroceryActivity.RESULT_OK,intent);
                    finish();
                }else{
                    Intent intent = new Intent(GroceryActivity.this, EditGroceryActivity.class );
                    intent.putExtra("info",needtobuy[position-1]);
                    changedlocation=position-1;
                    startActivityForResult(intent,9999);
                }

            }
        });




    }
    public void initialize(){
        allIngredients= new ArrayList<>();
        allIngredients.add("Choose");   //0
        allIngredients.add("Fish (lbs)");  //1
        allIngredients.add("chicken (lbs)");  //2
        allIngredients.add("pork (lbs)");  //3
        allIngredients.add("beef (lbs)");  //4
        allIngredients.add("shrimp (lbs)");  //5
        allIngredients.add("duck (lbs)");   //6
        allIngredients.add("garlic (cloves)"); //7
        allIngredients.add("onions (cups) ");  //8
        allIngredients.add("rice (cup)");  //9
        allIngredients.add("green pepper(cups)"); //10
        allIngredients.add("wasabi (teaspoons)"); //11
        allIngredients.add("ginger (cups)");  //12
        allIngredients.add("lettuce (lb)"); //13
        allIngredients.add("brocolli (lb)"); //14
        allIngredients.add("salt (teaspoon)"); //15


    }
}
