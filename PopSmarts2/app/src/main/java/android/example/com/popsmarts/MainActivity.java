package android.example.com.popsmarts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
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

    public void initiateQuestions(){
        Question xQ;

        // - - > SCIENCE QUESTIONS
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
                .setChoices("France", "USA", "Poland").setCategory("generalScience").build();
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
                .setChoices("Galileo", "Kepler", "Einstein").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Marie Curie is known for discovering what element _____.").setAnswer("Radium")
                .setChoices("Titanium", "Magnesium", "Manganese").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the name of Marie Curie’s husband?").setAnswer("Pierre")
                .setChoices("Philip", "James", "Howard").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who is the father of Taxonomy?").setAnswer("Linnaeus")
                .setChoices("Burbank", "Gauss", "Darwin").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which of the following categories does not belong to the official categories of the Nobel Prize?").setAnswer("Biology")
                .setChoices("Physics", "Chemistry", "Medicine").setCategory("generalScience").build();
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
                .setChoices("Bill Prady", "Chuck Lorre", "Charles Darwin").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Marie Curie was _____ by birth.").setAnswer("Polish")
                .setChoices("French", "German", "Swedish").setCategory("generalScience").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which scientist discovered Selenium and Cerium?").setAnswer("Berzelius")
                .setChoices("Lawrence", "Mendeleev", "Burbank").setCategory("generalScience").build();
        questionsArray.add(xQ);

        // - - > HISTORY QUESTIONS
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

        xQ = new Question.QuestionBuilder().setQuestion("St. Patrick’s day was originally associated with what color?").setAnswer("Blue")
                .setChoices("Green", "Red", "Yellow").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("_____ is considered as the “Cradle of Civilization”").setAnswer("Mesopotamia")
                .setChoices("Indus", "Yangtze", "Babylon").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the longest river in Asia?").setAnswer("Yangtze")
                .setChoices("Amazon", "Tigris", "Euphrates").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("The Mandate of Heaven was important to the rulers of which ancient place?").setAnswer("China")
                .setChoices("India", "Persia", "Crete").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Mao Zedong is considered to be the founding father of:").setAnswer("Modern China")
                .setChoices("Ancient Japan", "Modern Iran", "Ottoman Empire").setCategory("History").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("The Opium War was caused by ____________ introducing opium to China.").setAnswer("Britain")
                .setChoices("India", "Russia", "France").setCategory("History").build();
        questionsArray.add(xQ);

        // - - > GEOLOGY QUESTIONS
        xQ = new Question.QuestionBuilder().setQuestion("How many stars are there in the US flag?").setAnswer("50")
                .setChoices("48", "47", "46").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Managua is the capital of which country?").setAnswer("Nicaragua")
                .setChoices("South Africa", "Nigeria", "Uganda").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Where is the deepest point in the Mediterranean sea?").setAnswer("Ionian Sea")
                .setChoices("Cilician Sea", "Aegean Sea", "Balearic Sea").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("The Black Forest is located in what European country?").setAnswer("Germany")
                .setChoices("France", "Netherlands", "Italy").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Canada is made up of how many provinces?").setAnswer("10")
                .setChoices("16", "9", "7").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which country has the longest coastline in the world?").setAnswer("Canada")
                .setChoices("Australia", "Austria", "USA").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the longest river in South America?").setAnswer("Amazon")
                .setChoices("Nile", "Yellow", "Mississippi").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What city is the capital of China?").setAnswer("Beijing")
                .setChoices("Wuhan", "Shanghai", "Guangzhou").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("In what mountain range is Mount Everest located?").setAnswer("Himalayas")
                .setChoices("Andes", "Rocky Mountains", "Cascade Range").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the largest lake in Africa?").setAnswer("Lake Victoria")
                .setChoices("Lake Malawi", "Lake Turkana", "Lake Kivu").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the largest country based on surface area?").setAnswer("Russia")
                .setChoices("China", "Canada", "USA").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the largest island in the Caribbean sea?").setAnswer("Cuba")
                .setChoices("Jamaica", "Puerto Rico", "Guam").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("How many time zones are in Canada?").setAnswer("6")
                .setChoices("2", "4", "8").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which city is both located in Asia and Europe?").setAnswer("Istanbul")
                .setChoices("Stockholm", "Amsterdam", "Berlin").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is Earth’s largest ocean by surface area?").setAnswer("Pacific")
                .setChoices("Atlantic", "Indian", "Arctic").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the smallest country in the world?").setAnswer("Vatican City")
                .setChoices("Republic of San Marino", "Principality of Monaco", "Tuvalu").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the capital of Poland?").setAnswer("Warsaw")
                .setChoices("Torun", "Lublin", "Katowice").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the largest desert in the world?").setAnswer("Antarctic")
                .setChoices("Sahara", "Arabian", "Arctic").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is Earth’s largest continent?").setAnswer("Asia")
                .setChoices("Africa", "Europe", "Antartica").setCategory("Geography").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which country has the most natural lakes?").setAnswer("Canada")
                .setChoices("India", "USA", "China").setCategory("Geography").build();
        questionsArray.add(xQ);

        // - - > ARTS QUESTIONS
        xQ = new Question.QuestionBuilder().setQuestion("Which French author wrote the novel “La dame aux camelias”").setAnswer("Alexandre Dumas")
                .setChoices("Emile Zola", "Jules Verne", "Victor Hugo").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who is the author of “Da Davinci Code”?").setAnswer("Dan Brown")
                .setChoices("C.S. Lewis", "Rick Riordan", "J.K. Rowling").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What was Dr. Frankenstein’s first name in the famous novel of Mary Shelly?").setAnswer("Victor")
                .setChoices("Hugo", "James", "Dolly").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("In which city is the famous Prado museum located?").setAnswer("Madrid")
                .setChoices("Malaga", "Pisa", "Shanghai").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who is responsible for more than 1000 english words that is used today?").setAnswer("William Shakespeare")
                .setChoices("Ernest Hemingway", "Mark Twain", "George Orwell").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("How many paintings did Vincent Van Gogh sell during his lifetime?").setAnswer("1")
                .setChoices("800", "193", "89").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who painted The Sistine Chapel?").setAnswer("Michelangelo")
                .setChoices("Vincent van Gogh", "Leonardo da Vinci", "Calude Monet").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who painted The Scream?").setAnswer("Edvard Munch")
                .setChoices("Vincent van Gogh", "Otto Meuler", "Pablo Picasso").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What painter was summoned to Rome in 1481 to decorate the walls of the Sistine Chapel?").setAnswer("Sandro Boticelli")
                .setChoices("Raphael", "Leonardo da Vinci", "Claude Monet").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who is the art critic who coined the term \"impressionism\"").setAnswer("Louis Leroy")
                .setChoices("Jacob Burckhardt", "John Ruskin", "Roger Fry").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Ludwig Van Beethoven is a famous _____ composer and pianist.").setAnswer("German")
                .setChoices("French", "Italian", "Polish").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who composed “Fur elise”?").setAnswer("Beethoven")
                .setChoices("Mozart", "Vivaldi", "Debussy").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("WWhich of the following is a composition of Antonio Vivaldi?").setAnswer("Four Seasons")
                .setChoices("Turkish March", "Claire de Lune", "Fur Elise").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who wrote “Oliver Twist”?").setAnswer("Charles Dickens")
                .setChoices("Mark Twain", "Oscar Wilde", "Ernest Hemingway").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("In the book, Percy Jackson and the Olympians, Percy is the son of _____.").setAnswer("Poseidon")
                .setChoices("Zeus", "Hades", "Apollo").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which house does Hermione from Harry Potter belong to?").setAnswer("Gryffindor")
                .setChoices("Ravenclaw", "Hufflepuff", "Slytherin").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the first name of Dumbledore?").setAnswer("Albus")
                .setChoices("Snape", "Harry", "Ron").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who wrote Percy Jackson and the Olympians?").setAnswer("Rick Riordan")
                .setChoices("James Kinney", "J.K. Rowling", "Mark Twain").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which of the following novels were written by George Orwell?").setAnswer("Animal Farm")
                .setChoices("For whom the bell tolls", "The Pearl", "A Tramp Abroad").setCategory("Arts").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the main visual element in Impressionist painting?").setAnswer("Color")
                .setChoices("Lines", "Texture", "Shapes").setCategory("Arts").build();
        questionsArray.add(xQ);

        // - - > MYTHOLOGY QUESTIONS
        xQ = new Question.QuestionBuilder().setQuestion("In Greek mythology, who is regarded as the god of the sea?").setAnswer("Poseidon")
                .setChoices("Zeus", "Hermes", "Dionysus").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who is the Roman equivalent of the Greek goddess Enyo?").setAnswer("Bellona")
                .setChoices("Minerva", "Juno", "Vesta").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("In Greek mythology, who is the son of Zeus and Maia?").setAnswer("Hermes")
                .setChoices("Apollo", "Poseidon", "Dionysus").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("In Greek mythology, what is Medusa’s hair made of?").setAnswer("Snakes")
                .setChoices("Stone", "Insects", "Fire").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which mythical creature grows back two heads after a head has been cut off?").setAnswer("Hydra")
                .setChoices("Chimera", "Satyr", "Minotaur").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("In Greek mythology, who is the goddess of the rainbow?").setAnswer("Iris")
                .setChoices("Athena", "Aphrodite", "Persephone").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which mythical creature is half-man and half-bull?").setAnswer("Minotaur")
                .setChoices("Centaur", "Satyr", "Hydra").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What was the Greek hero Achilles’ only vulnerable part?").setAnswer("Heel")
                .setChoices("Neck", "Head", "Eye").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("In Egyptian mythology, Osiris is a god of?").setAnswer("Justice")
                .setChoices("Sun", "Moon", "Thieves").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What was the first god called in Egyptian mythology?").setAnswer("Atum")
                .setChoices("Osiris", "Horus", "Ra").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("In Egyptian mythology, which god rules over the underworld?").setAnswer("Anubis")
                .setChoices("Seth", "Horus", "Isis").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What form did the gods and goddesses of ancient Egypt typically take?").setAnswer("Animals")
                .setChoices("Plants", "Humans", "Statues").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which Egyptian god was considered the most powerful and important?").setAnswer("Ra")
                .setChoices("Thoth", "Horus", "Atum").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Who is the primary, father god in Norse mythology?").setAnswer("Odin")
                .setChoices("Frey", "Thor", "Omega").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("In Norse mythology, who is the god of thunder and lightning?").setAnswer("Thor")
                .setChoices("Odin", "Loki", "Frey").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("Which day of the week is named for the Norse god Odin?").setAnswer("Wednesday")
                .setChoices("Thursday", "Monday", "Tuesday").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("What is the name of Thor’s magical hammer?").setAnswer("Mjolnir")
                .setChoices("Orcsbane", "Sleipnir", "Gungnir").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("According to Norse mythology, where do the heroes who die in battle go to rest?").setAnswer("Valhalla")
                .setChoices("Asgard", "Midgard", "Niffleheim").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("In Norse mythology, who is the god of justice and war?").setAnswer("Tyr")
                .setChoices("Fafnir", "Frey", "Heimdall").setCategory("Mythology").build();
        questionsArray.add(xQ);

        xQ = new Question.QuestionBuilder().setQuestion("In Norse mythology, who was the faithful steed of Odin?").setAnswer("Sleipnir")
                .setChoices("Kelnir", "Valnyr", "Fystnir").setCategory("Mythology").build();
        questionsArray.add(xQ);
    }

    public void showNextQuiz(){

        // - - > UPDATE COUNT LABEL
        countLabel.setText("Q" + quizCount);

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

}