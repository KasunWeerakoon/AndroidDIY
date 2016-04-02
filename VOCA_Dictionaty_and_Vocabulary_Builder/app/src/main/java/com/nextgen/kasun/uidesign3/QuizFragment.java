package com.nextgen.kasun.uidesign3;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Kasun on 4/25/2015.
 */
public class QuizFragment extends Fragment {
    Button quiz;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.quiz_fragment, container, false);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        quiz =(Button)getView().findViewById(R.id.quizBtn);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
