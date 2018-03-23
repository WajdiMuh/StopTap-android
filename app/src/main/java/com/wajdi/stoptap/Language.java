package com.wajdi.stoptap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Language extends ListActivity {
    private ListView lview;
    private ArrayList<String> langsava = new ArrayList<String>() {{
        add("Arabic");
        add("English\nSelected");
    }};
    private ArrayAdapter<String> arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        final SecurePreferences preferences = new SecurePreferences(this, "my-preferences", "Ww654321", true);
        final LTextView lan = findViewById(R.id.langt);
        final LButton back = findViewById(R.id.back);
        lview = getListView();
        switch (preferences.getString("lang")) {
            case "0":
               langsava.clear();
               langsava.add("عربي\nمختار");
               langsava.add("انجليزي");
               lan.setText("اللغة");
               back.setText("عودة");
               back.setPadding(0,0,0,0);
                break;
            case "1":
               // lan.text = "Language"
               // lan.english(size: 26, diffinsize: 14)
              //  back.setTitle("Back", for: UIControlState())
              //  back.english(size: 20, diffinsize: 10,left: 4,top: 3)
                langsava.clear();
                langsava.add("Arabic");
                langsava.add("English\nSelected");
                lan.setText("Language");
                back.setText("Back");
                back.setPadding(this.getResources().getDimensionPixelSize(R.dimen.backleft),this.getResources().getDimensionPixelSize(R.dimen.backtop),0,0);
                break;
            default:
              //  lan.text = "Language"
            //    lan.english(size: 26, diffinsize: 14)
             //   back.setTitle("Back", for: UIControlState())
             //   back.english(size: 20, diffinsize: 10,left: 4,top: 3)
                langsava.clear();
                langsava.add("Arabic");
                langsava.add("English\nSelected");
                lan.setText("Language");
                back.setText("Back");
                back.setPadding(this.getResources().getDimensionPixelSize(R.dimen.backleft),this.getResources().getDimensionPixelSize(R.dimen.backtop),0,0);
                break;
        }
        arr = new ArrayAdapter<String>(this,
                R.layout.centerb, langsava);
        lview.setAdapter(arr);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fadeinintro,R.anim.fadeoutintro);
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        final LTextView lan = findViewById(R.id.langt);
        final LButton back = findViewById(R.id.back);
        final SecurePreferences preferences = new SecurePreferences(this, "my-preferences", "Ww654321", true);
        preferences.put("lang", String.valueOf(position));
        switch (position){
            case 0:
                langsava.clear();
                langsava.add("عربي\nمختار");
                langsava.add("انجليزي");
                lan.setText("اللغة");
                back.setText("عودة");
                back.setPadding(0,0,0,0);
                break;
            case 1:
                langsava.clear();
                langsava.add("Arabic");
                langsava.add("English\nSelected");
                lan.setText("Language");
                back.setText("Back");
                back.setPadding(this.getResources().getDimensionPixelSize(R.dimen.backleft),this.getResources().getDimensionPixelSize(R.dimen.backtop),0,0);
        }
        arr.notifyDataSetChanged();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fadeinintro,R.anim.fadeoutintro);
    }
}
