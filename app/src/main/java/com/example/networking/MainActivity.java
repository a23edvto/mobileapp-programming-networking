package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";

    ArrayList<Mountain> mountain = new ArrayList<Mountain>();

    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mountain.add(new Mountain("hej"));

        new JsonFile(this, this).execute(JSON_FILE);
        //new JsonTask(this).execute(JSON_URL);
        
        for(Mountain m : mountain){
            Log.d("MainActivity===>", "" + m.toString());
        }
        Log.d("MainActivity===>", "adapter");

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mountain, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Mountain item) {
                Toast.makeText(MainActivity.this, item.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);



        //adapter.notifyDataSetChanged();
    }

    @Override
    public void onPostExecute(String json) {
        Gson gson = new Gson();

        Type type = new TypeToken<List<Mountain>>() {}.getType();
        mountain = gson.fromJson(json, type);

        for (Mountain m : mountain) {
            Log.d ("===>", "" + m.toString());
        }
        Log.d("MainActivity", json);
    }
}
