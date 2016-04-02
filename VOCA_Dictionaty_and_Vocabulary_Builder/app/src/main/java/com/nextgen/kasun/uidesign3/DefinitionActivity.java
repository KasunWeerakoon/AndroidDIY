package com.nextgen.kasun.uidesign3;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DefinitionActivity extends ActionBarActivity {
    String wSelected;
    JSONObject jdata;
    TextView definitionTxt,titleTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);
        definitionTxt=(TextView)findViewById(R.id.definitionTxt);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            wSelected = extras.getString("wSelected");
        }
        titleTxt=(TextView)findViewById(R.id.txtTitle);
        titleTxt.setText(wSelected);

        if(networkIsAvailable()) {
            GetBlogData blogData = new GetBlogData();
            blogData.execute();
        }else {
            Toast.makeText(this, "Check, mobile data is activated", Toast.LENGTH_SHORT).show();
        }

    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent=new Intent(DefinitionActivity.this,MainActivity.class);
//        startActivity(intent);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_definition, menu);
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
    private boolean networkIsAvailable() {
        ConnectivityManager manager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        boolean isAvailable=false;
        if(networkInfo!=null && networkInfo.isConnected())
            isAvailable=true;
        return isAvailable;
    }
    private class GetBlogData extends AsyncTask<Object,Void,JSONObject> {
        @Override
        protected JSONObject doInBackground(Object[] params) {
            int responseCode=-1;
            try {
                //http://pipes.yahoo.com/pipes/pipe.run?_id=e68a2745299ca345b0faa35970f0fa87&_render=json
                //"http://blog.teamtreehouse.com/api/get_recent_summary/?count=20"
                URL url=new URL("https://api.pearson.com/v2/dictionaries/ldoce5/entries?headword="+wSelected);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();

                responseCode = connection.getResponseCode();
                if(responseCode==HttpURLConnection.HTTP_OK){
                    InputStream inputStream=connection.getInputStream();
                    String s = convertInputStreamToString(inputStream);
//                    Reader reader=new InputStreamReader(inputStream);
//                    int lenth=connection.getContentLength();
//                    char[] streamData=new char[lenth];
//                    reader.read(streamData);
//                    String responseData=new String(streamData);
                    jdata=new JSONObject(s);


                }else{
                    Log.i("DefinitionActiviy","Unresponsive code"+responseCode);
                }





            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return jdata;
        }

        @Override
        protected void onPostExecute(JSONObject jdata) {
            super.onPostExecute(jdata);
            try {
                String status = jdata.getString("status");
                Log.v("DefinitionActiviy", status);
                JSONArray results = jdata.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject resltOb = (JSONObject) results.get(i);
                    String headword = resltOb.optString("headword");
                    Log.v("DefinitionActiviy", headword);
                    JSONArray senses = resltOb.optJSONArray("senses");
                    for (int j = 0; i < senses.length(); i++) {
                        JSONObject senseOb = (JSONObject) senses.get(j);
                        String lexicalUnit = senseOb.optString("lexical_unit");
                        String definition = senseOb.optString("definition");
                        definitionTxt.setText(definition);
                        Log.v("DefinitionActiviy", lexicalUnit);
                        Log.v("DefinitionActiviy", definition);
                        MainActivity.wordsDefinition[MainActivity.wordcount][0]=headword;
                        MainActivity.wordsDefinition[MainActivity.wordcount++][0]=lexicalUnit;
                    }

//                        try {
//                            JSONObject oneObject = results.getJSONObject(""+i);
//                            // Pulling items from the array
//                            JSONObject oneObjectsItem = oneObject.getJSONObject("senses");
//                            String gameobct = oneObject.getString("headword");
//                            Log.v("DefinitionActiviy", gameobct);
//                            for (int j=0; i < results.length(); i++){
//                                JSONObject twoObject = results.getJSONObject(""+i);
//                            }
//
//                         } catch (JSONException e) {
//                            // Oops
//                        }
                }


                //Log.v("DefinitionActiviy", s);
                //Log.v(TAG,"Lenth"+lenth);
            }catch (Exception e){

            }

        }

        private String convertInputStreamToString(InputStream inputStream) throws IOException{
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();
            return result;

        }
    }
}
