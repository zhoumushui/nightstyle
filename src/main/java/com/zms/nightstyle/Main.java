package com.zms.nightstyle;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
    private boolean isNight = false;
    private Button btnSet;
    private Button btnGet;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("UsingStyle", Context.MODE_WORLD_READABLE);
        isNight = sharedPreferences.getBoolean("isNight", false);
        if (isNight) {
            this.setTheme(R.style.MyThemeNight);
        } else {
            this.setTheme(R.style.MyThemeDefault);
        }
        setContentView(R.layout.main);
        btnSet = (Button) findViewById(R.id.btnSet);
        btnGet = (Button) findViewById(R.id.btnGet);
        btnSet.setOnClickListener(new onClickListenerImp());
        btnGet.setOnClickListener(new onClickListenerImp());
    }

    class onClickListenerImp implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == btnSet) {
                Editor editor = sharedPreferences.edit();
                if (isNight) {
                    setTheme(R.style.MyThemeDefault);
                    isNight = false;
                } else {
                    setTheme(R.style.MyThemeNight);
                    isNight = true;
                }
                editor.putBoolean("isNight", isNight);
                editor.commit();

                setContentView(R.layout.main);
                btnSet = (Button) findViewById(R.id.btnSet);
                btnGet = (Button) findViewById(R.id.btnGet);
                btnSet.setOnClickListener(new onClickListenerImp());
                btnGet.setOnClickListener(new onClickListenerImp());
            } else if (v == btnGet) {
                Toast.makeText(Main.this, "isNight: " + isNight, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
