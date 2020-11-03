package android.example.com.popsmarts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class StartActivity extends AppCompatActivity {
    public MainActivity connector = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // - - > INITIALIZE BACKGROUND MUSIC
        Intent intent = new Intent(this, QBBackgroundMSetter.class);
        startService(intent);


        MainActivity test = new MainActivity();
        for(Question testQ:test.questionsArray){
            Log.i("TEST", testQ.getAnswer());
        }
    }

    public void startQuiz(View view){

        int quizCategory = 0;

        switch (view.getId()){
            case R.id.random:
                quizCategory =1;
                break;

            case R.id.generalScience:
                quizCategory=2;
                break;

            case R.id.history:
                quizCategory=3;
                break;

            case R.id.arts:
                quizCategory=4;
                break;

            case R.id.mythology:
                quizCategory=5;
                break;

            case R.id.geography:
                quizCategory=6;
                break;

        }
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("QUIZ_CATEGORY", quizCategory);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MediaPlayer mp = QBBackgroundMSetter.backgroundMusic;
        Log.i("TEST", "ACTIVITY START");
        if(mp != null){
            try{
                if(mp.isPlaying()){
                    mp.stop();
                }
            }catch(IllegalStateException ise){
                ise.printStackTrace();
            }
            mp = MediaPlayer.create(this, R.raw.quizbeeg);
            mp.setLooping(true);
            mp.setVolume(100, 100);
            mp.start();
            QBBackgroundMSetter.backgroundMusic = mp;
        }
    }

    @Override
    protected void onPause() {
        MediaPlayer mp = QBBackgroundMSetter.backgroundMusic;
        if(this.isFinishing()){
            mp.stop();
        }
        Context context = getApplicationContext();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        if(!taskInfo.isEmpty()){
            ComponentName topActivity = taskInfo.get(0).topActivity;
            if(!topActivity.getPackageName().equals(context.getPackageName())){
                try{
                    mp.stop();
                    mp.release();
                }catch(NullPointerException ne){
                    // pass
                }
                Log.i("TEST","EXITS APP");
            }else{
                Log.i("TEST", "SWITCH ACTIVITIES");
            }
        }
        super.onPause();
    }
}