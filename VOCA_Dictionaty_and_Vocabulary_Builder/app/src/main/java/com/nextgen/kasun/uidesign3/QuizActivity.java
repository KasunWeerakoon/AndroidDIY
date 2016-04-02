package com.nextgen.kasun.uidesign3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QuizActivity extends ActionBarActivity {
    ArrayList<String> asd;
    int count;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);



        Map<String,String> hmap=new HashMap<String,String>();
        hmap.put("Hot spot","In the world of IT this term refers to places that have wireless Internet connections");
        hmap.put("Huge pipes","Slang for a high-bandwidth Internet connection");
        hmap.put("Interface","In a general sense, it is the portion of a program that interacts between a user and an application");
        hmap.put("legacy media", "Media that is considered \"old,\" such as radio, television, and especially newspapers");
        hmap.put("multitasking","The simultaneous execution of more than one task");
        hmap.put("404","Originally a technical term for Not Found 404");
        hmap.put("Bug","A defect or fault in a program that prevents it from working correctly");
        hmap.put("Adware","A software application which displays unwanted pop-up advertisements on your computer while in use");



        Button finish = (Button) findViewById(R.id.finishBtn);
        finish.setOnClickListener(new View.OnClickListener() {
            RadioGroup rg1=(RadioGroup)findViewById(R.id.radG1);
            RadioGroup rg2=(RadioGroup)findViewById(R.id.radG2);
            RadioGroup rg3=(RadioGroup)findViewById(R.id.radG3);
            RadioGroup rg4=(RadioGroup)findViewById(R.id.radG4);
            final RadioButton rb1=(RadioButton)findViewById(rg1.getCheckedRadioButtonId());
            final RadioButton rb2=(RadioButton)findViewById(rg2.getCheckedRadioButtonId());
            final RadioButton rb3=(RadioButton)findViewById(rg3.getCheckedRadioButtonId());
            final RadioButton rb4=(RadioButton)findViewById(rg4.getCheckedRadioButtonId());
            @Override
            public void onClick(View v) {
                count = 4;
//                if (rb1.getText() == "Bug")
//                    count++;
//                if (rb2.getText() == "Multitasking")
//                    count++;
//                if (rb3.getText() == "Interactive")
//                    count++;
//                if (rb4.getText() == "Manipulative")
//                    count++;
                Intent intent=new Intent(QuizActivity.this,ScoreActivity.class);
                intent.putExtra("Count",count);
                startActivity(intent);


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
