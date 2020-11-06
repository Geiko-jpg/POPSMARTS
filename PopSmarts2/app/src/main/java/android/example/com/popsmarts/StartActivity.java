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
import android.view.WindowManager;

import java.util.List;

public class StartActivity extends AppCompatActivity {
    public MainActivity connector = new MainActivity();
    public static final String TEST_KEY = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MediaPlayer mp = QBBackgroundMSetter.backgroundMusic;
        Log.i("TEST", "ACTIVITY START");
        if(mp != null){
            try{
                if(!mp.isPlaying()){
                    mp.start();
                    Log.i(TEST_KEY, "RESUME PLAYING");
                }
            }catch(IllegalStateException ise){
                ise.printStackTrace();
            }
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
                    mp.pause();
                    Log.i(TEST_KEY,"SONG PAUSED");
                }catch(NullPointerException ne){
                    ne.printStackTrace();
                }
                Log.i("TEST","EXITS APP");
            }else{
                Log.i("TEST", "SWITCH ACTIVITIES");
            }
        }
        super.onPause();
    }
}