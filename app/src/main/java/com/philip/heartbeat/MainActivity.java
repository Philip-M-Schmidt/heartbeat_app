package com.philip.heartbeat;
/**
 * Code from: https://github.com/ManuRS/GetPulse
 * @author Manuel Reyes-Sanchez
 */
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onResume(){
        super.onResume();

        String number = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("LAST_MEASURE", "0");
        if (number!="0") {

            TextView tv = (TextView) findViewById(R.id.number);
            tv.setText(number + " bpm");

            RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
            tv = (TextView) findViewById(R.id.text);
            if (Double.parseDouble(number) > 90) {
                rb.setRating(0);
                tv.setText("Your heart rate is too high");
            } else {
                rb.setRating(1);
                tv.setText("Your heart rate is beautiful");
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
        }

        final Button button = (Button) findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), Measure.class);
                startActivity(intent);
            }
        });
    }
}
