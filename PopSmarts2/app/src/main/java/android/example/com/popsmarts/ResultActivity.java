package android.example.com.popsmarts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

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

    @Override
    protected void onStart() {
        super.onStart();
        MediaPlayer mp = QBBackgroundMSetter.backgroundMusic;
        if(mp != null){
            try{
                if(!mp.isPlaying()){
                    mp.start();
                    Log.i(StartActivity.TEST_KEY, "SONG RESUMED");
                }
            }catch(IllegalStateException ise){
                ise.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaPlayer mp = QBBackgroundMSetter.backgroundMusic;
        Context context = getApplicationContext();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        if(!taskInfo.isEmpty()){
            ComponentName topActivity = taskInfo.get(0).topActivity;
            if(!topActivity.getPackageName().equals(context.getPackageName())){
                try{
                    Log.i("TEST", "APP EXIT");
                    mp.pause();
                    Log.i("TEST", "SONG PAUSED");
                }catch(NullPointerException npe){
                    npe.printStackTrace();
                }
            }
        }
    }
}


