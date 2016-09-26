package com.wearable.whatsfordinner;

import android.graphics.Color;
import android.os.Bundle;
import android.app.ListActivity;

import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


public class AddActivity extends ListActivity {
    private ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add);
        Intent intent= getIntent();
        names = new ArrayList<>();
        names = intent.getStringArrayListExtra("selecteddishes");
        final int length = names.size();
        String[] adapternames= new String[length+1];
        for(int a= 0 ;a<length  ;a++ ){
            adapternames[a]= names.get(a);
        }

        TextView title = new TextView(this);
        title.setGravity(0x11);
        title.setText("Available Dishes");
        title.setTextSize(40);
        title.setTextColor(Color.BLACK);
        title.setBackgroundColor(Color.WHITE);
        ListView listview = getListView();
        listview.setTextFilterEnabled(true);
        listview.addHeaderView(title);
        adapternames[length]="----------------------Done-----------------------";
        setListAdapter(new ArrayAdapter<String>(this, R.layout.content_grocery, adapternames));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                }else if(position==length+1){
                    finish();

                }else{
                    Intent intent = new Intent(AddActivity.this, MealsActivity.class);
                    intent.putExtra("clickedlocation", position);
                    setResult(RESULT_OK,intent);
                    finish();
                }


            }
        });



    }

}
