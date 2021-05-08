package com.tanka.iot;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.tanka.iot.pojo.ESPResponse;
import com.tanka.iot.utils.MySingleton;

import org.json.JSONObject;

import pl.pawelkleczkowski.customgauge.CustomGauge;


public class MainActivity extends AppCompatActivity {

    private CustomGauge gauge1;
    private CustomGauge gauge2;
    private CustomGauge gauge3;

    int i;
    private TextView text1;
    private TextView text1_info;
    private TextView text2;

    private Button btnAddWater;
    private Button btnAddWater2;
    private Button btnAddNPK;
    private Button btnAddNPK2;

    TextView txt_N;
    TextView txt_P;
    TextView txt_K;

    TextView txtHumid;
    TextView txtTemp;

    private static final String TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddWater = findViewById(R.id.btn_add_water);
        btnAddWater2 = findViewById(R.id.btn_add_water2);
        btnAddNPK = findViewById(R.id.btn_add_npk);
        btnAddNPK2 = findViewById(R.id.btn_add_npk2);

        txt_N = findViewById(R.id.txt_N);
        txt_P = findViewById(R.id.txt_P);
        txt_K = findViewById(R.id.txt_K);

        txtHumid = findViewById(R.id.txtHumid);
        txtTemp = findViewById(R.id.txtTemp);


        gauge1 = findViewById(R.id.gauge1);
        text1  = findViewById(R.id.textView1);
        text1_info = findViewById(R.id.text1_info);
        text1.setText(String.valueOf(gauge1.getValue()));

        btnAddWater.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Json OBJ", "Btn click");
                requestData( MainActivity.this);
               // generatingDummyData("hi");

            }
        });
    }

    private void loadGaugeValue() {
        new Thread() {
            public void run() {

                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gauge1.setValue(eSPResponse.getSoil_mois());
                            //  gauge1.setValue(80);
                            if(gauge1.getValue() < 20){
                                text1.setText(String.valueOf(gauge1.getValue()) + "%");
                                text1.setTextColor(getColor(R.color.md_brown_700));
                                text1_info.setText("Soil too dry");
                                text1_info.setTextColor(getColor(R.color.md_brown_700));
                            }
                            else if((gauge1.getValue() > 20) && (gauge1.getValue() < 60) ){
                                text1.setText(String.valueOf(gauge1.getValue()) + "%");
                                text1.setTextColor(getColor(R.color.md_blue_900));
                                text1_info.setText("Good soil humidity");
                                text1_info.setTextColor(getColor(R.color.md_blue_900));
                            }
                            else{
                                text1.setText(String.valueOf(gauge1.getValue()) + "%" );
                                text1.setTextColor(getColor(R.color.md_green_900));
                                text1_info.setText("good soil humidity");
                                text1_info.setText("Too High soil humidity");
                                text1_info.setTextColor(getColor(R.color.md_green_900));
                            }
                        }
                    });
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }


    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    String url = "http://192.168.43.170/";


    public void requestData(Activity ctx) {

        Log.d("Json OBJ", "In request Data");

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                MainActivity.this.generatingData(response);
                loadGaugeValue();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);

        ESPResponse eSPResponse;
    private Gson gson;
    public void generatingData(String response) {
        Toast.makeText(getApplicationContext(),"Response :" + response, Toast.LENGTH_LONG).show();//display the response on screen
        eSPResponse = new Gson().fromJson(response, ESPResponse.class);

        Log.d(TAG, "generatingData: "+ eSPResponse.getAir_hum() + eSPResponse.getAir_temp() + eSPResponse.getSoil_K());
        txt_N.setText(Integer.toString(eSPResponse.getSoil_N()));
        txt_P.setText(Integer.toString(eSPResponse.getSoil_P()));
        txt_K.setText(Integer.toString(eSPResponse.getSoil_K()));

        txtHumid.setText(Integer.toString(eSPResponse.getAir_hum()) + "%");
        txtTemp.setText(Integer.toString(eSPResponse.getAir_temp()) + "*C");
    }

    public void generatingDummyData(String response) {

       // Log.d(TAG, "generatingData: "+ eSPResponse.getAir_hum() + eSPResponse.getAir_temp() + eSPResponse.getSoil_K());
        txt_N.setText("50");
        txt_P.setText("50");
        txt_K.setText("50");
    }
}
