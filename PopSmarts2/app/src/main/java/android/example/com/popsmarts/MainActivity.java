package android.example.com.popsmarts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    // - - > COMPONENTS DECLARATION
    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 20;
    public int quizCategory;

    // - - > MYSQLITE DATABASE COMPONENTS
    private DBQuestionHelper myDb;

    // - - > ARRAYLIST QUIZ QUESTIONNAIRE DATABASE
    public ArrayList<Question> questionsArray = new ArrayList<>();
    public ArrayList<Question> transferArray = new ArrayList<>();

    // - - > COLOR DECLARATION
    final int color = Color.parseColor("#a8220a");

    // - - > BUILD QUESTION DATA SET
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLabel = (TextView)findViewById(R.id.countLabel);
        questionLabel = (TextView)findViewById(R.id.questionLabel);
        answerBtn1 = (Button) findViewById(R.id.answerBtn1);
        answerBtn2 = (Button) findViewById(R.id.answerBtn2);
        answerBtn3 = (Button) findViewById(R.id.answerBtn3);
        answerBtn4 = (Button) findViewById(R.id.answerBtn4);

        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Montserrat-Regular.ttf");
        answerBtn1.setTypeface(font);
        answerBtn2.setTypeface(font);
        answerBtn3.setTypeface(font);
        answerBtn4.setTypeface(font);
      
        // - - > INITIATE THE SET OF QUESTIONS
        myDb = new DBQuestionHelper(this);
        myDb.refreshDB();

        questionsArray = myDb.getAllQuestions();
        for(Question test:questionsArray){
            Log.i("TEST", test.getAnswer());
        }

        // - - > FILTERING CATEGORY
        quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);
        if(quizCategory == 1){ // RANDOM
            ArrayList<Integer>transfer = new ArrayList<>(20);
            Random rng = new Random();
            Set<Integer>generated = new HashSet<>();
            while(generated.size() <  20){
                Integer next = rng.nextInt(questionsArray.size());
                generated.add(next);
            }

            for(Integer tmp:generated){
                transfer.add(tmp);
            }
            for(int i = 0; i < 20; i++){
                Random rand = new Random();
                int randNum = rand.nextInt(questionsArray.size());
                transferArray.add(questionsArray.get(transfer.get(i)));
            }
        }else if(quizCategory == 2){ // GENERAL SCIENCE
            for(Question question:questionsArray){
                if(question.getCategory().equals("generalScience") && question != null){
                    transferArray.add(question);
                }
            }
        }else if(quizCategory == 3){ // HISTORY
            for(Question question:questionsArray){
                if(question.getCategory().equals("History") && question != null){
                    transferArray.add(question);
                }
            }
        }else if(quizCategory == 4){ // ARTS
            for(Question question:questionsArray){
                if(question.getCategory().equals("Arts") && question != null){
                    transferArray.add(question);
                }
            }
        }else if(quizCategory == 5){ // MYTHOLOGY
            for(Question question:questionsArray){
                if(question.getCategory().equals("Mythology") && question != null){
                    transferArray.add(question);
                }
            }
        }else{ // GEOGRAPHY
            for(Question question:questionsArray){
                if(question.getCategory().equals("Geography") && question != null){
                    transferArray.add(question);
                }
            }
        }

        showNextQuiz();
    }

    public void showNextQuiz(){

        // - - > UPDATE COUNT LABEL
        countLabel.setText("QUESTION " + quizCount);

        // - - > TEST
        for(Question test:transferArray){
            Log.i("TEST", test.getQuestion() + " | " + test.getAnswer() + " | " + test.getCategory());
        }
        Log.i("COUNT", String.valueOf(Integer.parseInt(String.valueOf(transferArray.size()))));

        // - - > GENERATE SIZE NUMBER FROM SIZE OF ARRAY
        Random random = new Random();
        int randomNum = random.nextInt(transferArray.size());

        // - - > SELECT CHOICE FROM THE SET
        Question displayQuestion = transferArray.get(randomNum);

        // QUESTION AND CORRECT ANSWER
        questionLabel.setText(displayQuestion.getQuestion());
        rightAnswer = displayQuestion.getAnswer();

        // - - > SHUFFLE THE CHOICES
        ArrayList<String>separateChoices = new ArrayList<>();
        separateChoices.add(displayQuestion.getAnswer());
        separateChoices.add(displayQuestion.getC1());
        separateChoices.add(displayQuestion.getC2());
        separateChoices.add(displayQuestion.getC3());
        Collections.shuffle(separateChoices);

        // - - > SET THE CHOICES ON THE BUTTONS
        answerBtn1.setText(separateChoices.get(0));
        answerBtn2.setText(separateChoices.get(1));
        answerBtn3.setText(separateChoices.get(2));
        answerBtn4.setText(separateChoices.get(3));

        // - - > REMOVE FROM TRANSFER ARRAY
        transferArray.remove(randomNum);
    }

    public void checkAnswer(View view){
        // - - > INITIALIZE SOUND EFFECTS ONCLICK
        final MediaPlayer correctMp = MediaPlayer.create(this, R.raw.correctsfx);
        final MediaPlayer wrongMp = MediaPlayer.create(this, R.raw.wrongsfx);

        // - - > BUTTON WHEN CLICKED
        Button answerBtn =(Button)findViewById(view.getId());
        String btnText = answerBtn.getText().toString();
        String alertTitle;

        if (btnText.equals(rightAnswer)) {
            correctMp.start();
            alertTitle = "Good Job!";
            rightAnswerCount++;
        }else{
            wrongMp.start();
            alertTitle = "Oops! That's wrong.";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Correct Answer: " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (quizCount == QUIZ_COUNT) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);
                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
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