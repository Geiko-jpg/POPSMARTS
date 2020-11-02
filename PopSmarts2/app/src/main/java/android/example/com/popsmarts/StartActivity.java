package android.example.com.popsmarts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

}