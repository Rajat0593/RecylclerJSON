package com.f.rajat.demotest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EmployeeDetails extends AppCompatActivity {

    ArrayList<EmployeeModel> list;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ProgressDialog progressDialog;

    public static final String URL ="http://squarencube.info/LocationTracking/public/getAllEmployees/101";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        //setting up the recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        //Setting up the layoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //instantiating the List
        list = new ArrayList<>();


        new FetchDetails().execute();
    }
        //using AsyncTask for the asynchronous Background activity with another Thread
    public class FetchDetails extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(EmployeeDetails.this);
            progressDialog.setTitle("Wait");
            progressDialog.setMessage("Fetching the Details !!!");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }



        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler httpHandler = new HttpHandler();
            //getting the URL
         String jsonString = httpHandler.makeServiceCall(URL);
         //if nothing is there
         if(jsonString != null) {
                try {
                    //Creating jsonRoot Object On the URL
                    JSONObject jsonRootObject = new JSONObject(jsonString);

                    //fetching the array of the jsonRootObject
                    JSONArray jsonArray = jsonRootObject.getJSONArray("Employees");
                    //iterating to get the Details
                    for(int i =0 ; i < jsonArray.length() ; i++){
                        JSONObject jsonObject = jsonArray.optJSONObject(i);

                       EmployeeModel mUser = new EmployeeModel();
                       mUser.setMid(jsonObject.getString("Id"));
                       mUser.setMpassword(jsonObject.getString("Password"));
                       mUser.setmMobileNo(jsonObject.getString("MobileNo"));
                       mUser.setmManagerId(jsonObject.getString("ManagerId"));

                       //adding the details to the List
                       list.add(mUser);
                    }

                } catch (JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "json parser error", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
            else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Object Not Found",Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

                //Setting up the adapter
                adapter = new RecyclerAdapter(list,getApplicationContext());
                //Setting up the recyclerView Adapter by passing the adapter Object
                recyclerView.setAdapter(adapter);
            }
    }


}
