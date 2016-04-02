package com.nextgen.kasun.uidesign3;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kasun on 4/25/2015.
 */
public class VListFragment extends Fragment {
    String[] wordslist;
    String[] words;
    List listadapter;
    private String[] wordList;
    SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View rootView = inflater.inflate(R.layout.vlist_fragment, container, false);
//       swipeRefreshLayout=(SwipeRefreshLayout)getView().findViewById(R.id.refresh_layout);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Toast.makeText(getActivity(),"Refreshing",Toast.LENGTH_LONG).show();
//            }
//        });



        ParseQuery<ParseObject> query = ParseQuery.getQuery("vocabulary");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    ArrayList<String> asd=new ArrayList<String>();

                    for (int i = 0; i < scoreList.size(); i++) {

                        ParseObject object = scoreList.get(i);
                        String name = ((ParseObject) object).get("word").toString();
                        asd.add(i,name);



                        setWordList(name,scoreList.size(),i);




                    }
                    final ListView listView = (ListView) getView().findViewById(R.id.listView);
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, asd);
                    listView.setAdapter(arrayAdapter);



                    ;

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }


        });

        return rootView;



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        final ListView listView = (ListView) getView().findViewById(R.id.listView);
        Resources resources = getResources();
        String[] data = resources.getStringArray(R.array.dummyData);

        // Log.d("score", "aaaaaaaa " + words[3] + " scores");

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
//        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = listView.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(),"You selected : " + item,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),DefinitionActivity.class);
                intent.putExtra("wSelected",item);
                startActivity(intent);
            }
        });


    }

    public void setWordList(String wordList,int size,int i) {
        //zthis.wordList=new String[size];
        words = new String[size];
        words[i] = wordList;
        //this.wordList = wordList.clone();
        Log.d("score", "Retrieved " + words[i] + " scores");

    }
}
