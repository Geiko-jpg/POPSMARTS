package android.example.com.popsmarts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

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

    // - - > ARRAYLIST QUIZ QUESTIONNAIRE DATABASE
    public ArrayList<Question> questionsArray = new ArrayList<>();
    public ArrayList<Question> transferArray = new ArrayList<>();

    String quizData[][] = {
            {"St. Patrick’s day was originally associated with what color?", "Blue", "Green", "Red", "Yellow"},
            {"_____ is considered as the “Cradle of Civilization”", "mesopotamia", "indus", "yangtze", "Babylon"},
            {"What is the longest river in Asia?", "yangtze", "amazon", "Tigris", "Euphrates"},
            {"The Mandate of Heaven was important to the rulers of which ancient place?", "china", "india", "persia", "crete"},
            {"Mao Zedong is considered to be the founding father of:", "modern china", "ancient japan", "modern iran", "ottoman empire"},
            {"The Opium War was caused by ____________ introducing opium to China.", "brittain", "india", "russia", "france"},
            {"How many stars are there in the US flag?", "50", "48", "47", "46"},
            {"Managua is the capital of which country?", "nicaragua", "south africa", "nigeria", "uganda"},
            {"Where is the deepest point in the Meditteranean sea?", "ionian sea", "cilician sea", "aegan sea", "balearic sea"},
            {"The Black Forest is located in what European country?", "germany", "france", "netherlands", "italy"},
            {"Canada is made up of how many provinces?", "10", "16", "9", "7"},
            {"Which country has the longest coastline in the world?", "canada", "australia", "austria", "usa"},
            {"What is the longest river in South America?", "amazon", "nile", "yellow", "mississippi"},
            {"What city is the capital of China?", "beijing", "wuhan", "shanghai", "guangzhou"},
            {"In what mountain range is Mount Everest located?", "himalayas", "andes", "rocky mountains", "cascade range"},
            {"What is the largest lake in Africa?", "lake victoria", "lake malawi", "lake turkana", "lake kivu"},
            {"What is the largest country based on surface area?", "russia", "china", "canada", "usa"},
            {"What is the largest island in the Caribbean sea?", "cuba", "jamaica", "puerto rico", "guam"},
            {"How many time zones are in Canada?", "6", "2", "4", "8"},
            {"Which city is both located in Asia and Europe?", "istanbul", "stockholm", "amsterdam", "berlin"},
            {"What is Earth’s largest ocean by surface area?", "pacific", "atlantic", "indian", "arctic"},
            {"What is the smallest country in the world?", "vatican city", "republic of san marino", "principality of monaco", "tuvalu"},
            {"What is the capital of Poland?", "warsaw", "torun", "lublin", "katowice"},
            {"What is the largest desert in the world?", "antarctic", "sahara", "arabian", "arctic"},
            {"What is Earth’s largest continent?", "asia", "africa", "europe", "antartica"},
            {"Which country has the most natural lakes?", "canada", "india", "usa", "china"},
            {"Which French author wrote the novel “La dame aux camelias”?", "alexandre dumas", "emile zola", "jules verne", "victor hugo"},
            {"Who is the author of “Da Davinci Code”?", "dan brown", "c.s. lewis", "rick riordan", "j.k. rowling"},
            {"What was Dr. Frankenstein’s first name in the famous novel of Mary Shelly?", "victor", "hugo", "james", "dolly"},
            {"In which city is the famous Prado museum located?", "madrid", "malaga", "pisa", "shanghai"},
            {"Who is responsible for more than 1000 english words that is used today?", "william shakespear", "ernest hemingway", "mark twain", "george orwell"},
            {"How many paintings did Vincent Van Gogh sell during his lifetime?", "1", "800", "193", "89"},
            {"Who painted The Sistine Chapel?", "michelangelo", "vincent van gough", "leonardo da vnci", "calude monet"},
            {"Who painted The Scream?", "edvard munch", "vincent van gough", "otto meuler", "pablo picasso"},
            {"What painter was summoned to Rome in 1481 to decorate the walls of the Sistine Chapel?", "sandro boticelli", "raphael", "leonardo da vinci", "claude monet"},
            {"Who is the art critic who coined the term \"impressionism\"?", "louis leroy", "jacob burckhardt", "john ruskin", "roger fry"},
            {"Ludwig Van Beethoven is a famous _____ composer and pianist.", "german", "french", "italian", "polish"},
            {"Who composed “Fur elise”?", "beethoven", "mozart", "vivaldi", "debussy"},
            {"Which of the following is a composition of Antonio Vivaldi?", "four seasons", "turkish march", "claire de lune", "fur elise"},
            {"Who wrote “Oliver Twist”?", "charles dickens", "mark twain", "oscar wilde", "ernest hemingway"},
            {"In the book, Percy Jackson and the Olympians, Percy is the son of _____.", "poseidon", "zeus", "hades", "apollo"},
            {"Which house does Hermione from Harry Potter belong to?", "gryffindor", "ravenclaw", "hufflepuff", "slytherin"},
            {"What is the first name of Dumbledore?", "albus", "snape", "harry", "ron"},
            {"Who wrote Percy Jackson and the Olympians?", "rick riordan", "james kinney", "j.k. rowling", "mark twain"},
            {"Which of the following novels were written by George Orwell?", "animal farm", "for whom the bell tolls", "the pearl", "a tramp abroad"},
            {"What is the main visual element in Impressionist painting?", "color", "lines", "texture", "shapes"},
            {"In Greek mythology, who is regarded as the god of the sea?", "poseidon", "zeus", "hermes", "dionysus"},
            {"Who is the Roman equivalent of the Greek goddess Enyo?", "bellona", "minerva", "juno", "vesta"},
            {"In Greek mythology, who is the son of Zeus and Maia?", "hermes", "apollo", "poseidon", "dionysus"},
            {"In Greek mythology, what is Medusa’s hair made of?", "snakes", "stone", "insects", "fire"},
            {"Which mythical creature grows back two heads after a head has been cut off?", "hydra", "chimera", "satyr", "minotaur"},
            {"In Greek mythology, who is the goddess of the rainbow?", "iris", "athena", "aphrodite", "persephone"},
            {"Which mythical creature is half-man and half-bull?", "minotaur", "centaur", "satyr", "hydra"},
            {"What was the Greek hero Achilles’ only vulnerable part?", "heel", "neck", "head", "eye"},
            {"In Egyptian mythology, Osiris is a god of?", "justice", "sun", "moon", "thieves"},
            {"What was the first god called in Egyptian mythology?", "atum", "osiris", "horus", "ra"},
            {"In Egyptian mythology, which god rules over the underworld?", "anubis", "seth", "horus", "isis"},
            {"What form did the gods and goddesses of ancient Egypt typically take?", "animals", "plants", "humans", "statues"},
            {"Which Egyptian god was considered the most powerful and important?", "ra", "thoth", "horus", "atum"},
            {"Who is the primary, father god in Norse mythology?", "odin", "frey", "thor", "omega"},
            {"In Norse mythology, who is the god of thunder and lightning?", "thor", "odin", "loki", "frey"},
            {"Which day of the week is named for the Norse god Odin?", "wednesday", "thursday", "monday", "tuesday"},
            {"What is the name of Thor’s magical hammer?", "mjolnir", "orcsbane", "sleipnir", "gungnir"},
            {"According to Norse mythology, where do the heroes who die in battle go to rest?", "valhalla", "asgard", "midgard", "niffleheim"},
            {"In Norse mythology, who is the god of justice and war?", "tyr", "fafnir", "frey", "heimdall"},
            {"In Norse mythology, who was the faithful steed of Odin?", "sleipnir", "kelnir", "valnyr", "fystnir"},
    };

    // - - > BUILD QUESTION DATA SET
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // - - > INITIATE THE SET OF QUESTIONS
        for(Question test:questionsArray){
            Log.i("TEST", test.getAnswer());
        }
        initiateQuestions();

        countLabel = (TextView)findViewById(R.id.countLabel);
        questionLabel = (TextView)findViewById(R.id.questionLabel);
        answerBtn1 = (Button) findViewById(R.id.answerBtn1);
        answerBtn2 = (Button) findViewById(R.id.answerBtn2);
        answerBtn3 = (Button) findViewById(R.id.answerBtn3);
        answerBtn4 = (Button) findViewById(R.id.answerBtn4);

        quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);

        // - - > FILTERING CATEGORY
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
        }else if(quizCategory == 3){
            for(Question question:questionsArray){
                if(question.getCategory().equals("History") && question != null){
                    transferArray.add(question);
                }
            }
        }else if(quizCategory == 4){
            for(Question question:questionsArray){
                if(question.getCategory().equals("Arts") && question != null){
                    transferArray.add(question);
                }
            }
        }else if(quizCategory == 5){
            for(Question question:questionsArray){
                if(question.getCategory().equals("Mythology") && question != null){
                    transferArray.add(question);
                }
            }
        }else{
            for(Question question:questionsArray){
                if(question.getCategory().equals("Geography") && question != null){
                    transferArray.add(question);
                }
            }
        }

        showNextQuiz();
    }

    public void initiateQuestions(){
        Question xQ;

        xQ = new Question.QuestionBuilder().setQuestion("Which of these elements has an atomic number of 8?").setAnswer("Oxygen")
                .setChoices("Hydrogen", "Silicon", "Helium").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which of these numbers has an Alpha Numeric value of D?").setAnswer("500")
                .setChoices("100", "1000",  "5000").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Melittology is a branch of Entomology which studies _____.").setAnswer("Bees")
                .setChoices("Spiders", "Ants", "Roaches").setCategory("generalScience").build();
        questionsArray.add(xQ);


        xQ = new Question.QuestionBuilder().setQuestion("What is the first element in the Periodic Table?").setAnswer("Hydrogen")
                .setChoices("Oxygen", "Lithium", "Helium").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Where was Albert Einstein born?").setAnswer("Germany")
                .setChoices("France", "USA", "Poland").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the most abundant element in the Earth’s crust?").setAnswer("Oxygen")
                .setChoices("Titanium", "Silicon", "Helium").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the square root of 9?").setAnswer("3")
                .setChoices("4.5", "6", "3.5").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("How many poles do all magnets have?").setAnswer("2")
                .setChoices("4", "6", "8").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the second lightest element?").setAnswer("Helium")
                .setChoices("Hydrogen", "Oxygen", "Silicon").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which of the following scientists was born first?").setAnswer("Copernicus")
                .setChoices("Galileo", "Kepler", "Einstein").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Marie Curie is known for discovering what element _____.").setAnswer("Radium")
                .setChoices("Titanium", "Magnesium", "Manganese").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the name of Marie Curie’s husband?").setAnswer("Pierre")
                .setChoices("Philip", "James", "Howard").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who is the father of Taxonomy?").setAnswer("Linnaeus")
                .setChoices("Burbank", "Gauss", "Darwin").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which of the following categories does not belong to the official categories of the Nobel Prize?").setAnswer("Biology")
                .setChoices("Physics", "Chemistry", "Medicine").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("How long does it take for the Earth to spin once on its axis?").setAnswer("1 Day")
                .setChoices("1 Hour", "1 Year", "1 Century").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What planet in our Solar System has the most gravity?").setAnswer("Jupiter")
                .setChoices("Mercury", "Mars", "Earth").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("In Geology, how long is an eon?").setAnswer("Billion Years")
                .setChoices("Hundred Years", "Thousand Years", "Million Years").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("The Big Bang Theory was first introduced by _____ in the 1920s.").setAnswer("Georges Lemaitre")
                .setChoices("Bill Prady", "Chuck Lorre", "Charles Darwin").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Marie Curie was _____ by birth.").setAnswer("Polish")
                .setChoices("French", "German", "Swedish").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which scientist discovered Selenium and Cerium?").setAnswer("Berzelius")
                .setChoices("Lawrence", "Mendeleev", "Burbank").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who was the mother of Mary I of England?").setAnswer("Catherine of Aragon")
                .setChoices("Jane Seymour", "Anne of Cleves", "Anne Boleyn").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Where did Napoleon die?").setAnswer("St. Helena Island")
                .setChoices("Elba Island", "Paris", "Rome").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What does A in NATO stand for?").setAnswer("Atlantic")
                .setChoices("American", "Area", "Archipelago").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which of these events happened first in American history?").setAnswer("Declaration of Independence")
                .setChoices("Civil War", "9/11 Attack", "Cluster's Last Stand").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("When did the Cold War start?").setAnswer("1945")
                .setChoices("1941", "1991", "1987").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("When did Martin Luther King Jr. die?").setAnswer("1968")
                .setChoices("1967", "1969", "1970").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which female pharaoh had the longest reign?").setAnswer("Hatsheput")
                .setChoices("Nitocris", "Merneith", "Twosret").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who is the only US president to serve more than 2 terms?").setAnswer("Roosevelt")
                .setChoices("Trump", "Washington", "Obama").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which one of the 7 ancient wonders of the world is still standing today?").setAnswer("Great Pyramid of Giza")
                .setChoices("Hanging Gardens of Babylon", "Temple of Artemis at Ephesus", "Statue of Zeus at Olympia").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("The attack on Pearl Harbor took place at which month?").setAnswer("December")
                .setChoices("September", "October", "November").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What tower in New York City was the target for the 9/11 attack?").setAnswer("World Trade Center")
                .setChoices("Empire State Building", "Chrysler Building", "Flatiron Building").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("US Airways flight 1549 had an emergency landing at the _____ river.").setAnswer("Atlantic")
                .setChoices("American", "Area", "Archipelago").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("US Airways flight 1549 had an emergency landing because of _____.").setAnswer("Bird Attack")
                .setChoices("Engine Malfunction", "Medical Emergency", "Aircraft out of Gas").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Osama Bin Laden was killed under the order of President _____.").setAnswer("Obama")
                .setChoices("Clinton", "Bush", "Trump").setCategory("History").build();
        questionsArray.add(xQ);
    }

    public void showNextQuiz(){

        // - - > UPDATE COUNT LABEL
        countLabel.setText("Q" + quizCount);

        // - - > TEST
        for(Question test:transferArray){
            Log.i("TEST", test.getQuestion() + " | " + test.getAnswer());
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

        //button when clicked
        Button answerBtn =(Button)findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)) {
            alertTitle = "Good Job!";
            rightAnswerCount++;
        }
        else{
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

}