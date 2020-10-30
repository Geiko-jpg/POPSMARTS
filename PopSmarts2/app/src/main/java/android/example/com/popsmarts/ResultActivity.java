package android.example.com.popsmarts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultLabel = (TextView)findViewById(R.id.resultLabel);
        TextView totalScoreLabel = (TextView)findViewById(R.id.totalScoreLabel);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        SharedPreferences settings = getSharedPreferences("Pop Smarts", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore", 0);
        totalScore += score;

        resultLabel.setText(score + " / 20");
        totalScoreLabel.setText("Total Score: "+ totalScore);

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("totalScore", totalScore);
        editor.commit();

    }

    public void returnTop(View view){

        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        startActivity(intent);

    }

}


