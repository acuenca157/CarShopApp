package org.izv.di.acl.concesionario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "jdbc:mariadb://146.59.237.189/dam208_aclconcesionario";
    private static final String USER = "dam208_acl";
    private static final String PASSWORD = "dam208_acl";

    public Context ctx; //Contexto para pasarlo a la consulta

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_main);
        new InfoAsyncTask().execute();

        //iniciamos la actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.show();
        setTitle("Autos Dominguez");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemWeb:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.autos-dominguez.com"));
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, ArrayList<Car>> {
        @Override
        protected ArrayList<Car> doInBackground(Void... voids) {
            ArrayList<Car> info = new ArrayList<>();

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT * FROM coches";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int carRef = resultSet.getInt("ref");
                    String carTitle = resultSet.getString("title");
                    String carDesc = resultSet.getString("desc");
                    String carPrice = resultSet.getString("precioContado");
                    String carCategories = resultSet.getString("categories");
                    String carImages = resultSet.getString("imagesUrls");

                    Car car = new Car(carRef, carTitle, carDesc, carPrice, carCategories, carImages);
                    info.add(car);
                }
                return info;
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading school information", e);
            }

            return info;
        }

        @Override
        protected void onPostExecute(ArrayList<Car> result) {
            if (!result.isEmpty()) {
                RecyclerView rv = findViewById(R.id.recyclerView);
                rv.setLayoutManager(new LinearLayoutManager(ctx));
                RecyclerAdapter carAdapter = new RecyclerAdapter(ctx);
                rv.setAdapter(carAdapter);
                carAdapter.setList(result);
            }
        }
    }
}