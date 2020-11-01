package android.example.com.popsmarts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DBQuestionHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "popsmarts.db";
    public static final String QUESTIONS_TABLE_NAME = "questions_table";
    public static final String QUESTIONS_INQUIRY = "question";
    public static final String QUESTIONS_ANSWERS = "answer";
    public static final String QUESTIONS_C1 = "c1";
    public static final String QUESTIONS_C2 = "c2";
    public static final String QUESTIONS_C3 = "c3";
    public static final String QUESTIONS_CATEGORY = "category";
    public static final String KEY_ID = "keyid";
    private HashMap hp;

    public DBQuestionHelper(Context context){
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS " + QUESTIONS_TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QUESTIONS_INQUIRY + " TEXT, " +
                QUESTIONS_ANSWERS + " TEXT, " +
                QUESTIONS_C1 + " TEXT, " +
                QUESTIONS_C2 + " TEXT, " +
                QUESTIONS_C3 + " TEXT, " +
                QUESTIONS_CATEGORY + " TEXT" + ");";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertQuestion(String question, String answer, String C1, String C2, String C3, String category){
        SQLiteDatabase db = this.getWritableDatabase();
        Question questionStruct = new Question.QuestionBuilder().setQuestion(question)
                .setAnswer(answer)
                .setChoices(C1, C2, C3)
                .setCategory(category)
                .build();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, (Integer) null);
        contentValues.put(QUESTIONS_INQUIRY, questionStruct.getQuestion());
        contentValues.put(QUESTIONS_ANSWERS, questionStruct.getAnswer());
        contentValues.put(QUESTIONS_C1, questionStruct.getC1());
        contentValues.put(QUESTIONS_C2, questionStruct.getC2());
        contentValues.put(QUESTIONS_C3, questionStruct.getC3());
        contentValues.put(QUESTIONS_CATEGORY, questionStruct.getCategory());

        db.insert(QUESTIONS_TABLE_NAME, null, contentValues);
    }

    public void refreshDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + QUESTIONS_TABLE_NAME;
        db.execSQL(query);
    }

    public void qdbPopulate(){
        // - - > GENERAL SCIENCE
        insertQuestion("Which of these elements has an atomic number of 8?", "Oxygen", "Hydrogen", "Silicon", "Helium", "generalScience");
        insertQuestion("Which of these numbers has an Alpha Numeric value of D?", "500", "100", "1000", "5000", "generalScience");
        insertQuestion("Melittology is a branch of Entomology which studies _____.", "Bees", "Spiders", "Ants", "Roaches", "generalScience");
        insertQuestion("What is the first element in the Periodic Table?", "Hydrogen", "Oxygen", "Lithium", "Helium", "generalScience");
        insertQuestion("Where was Albert Einstein born?", "Germany", "France", "USA", "Poland", "generalScience");
        insertQuestion("What is the most abundant element in the Earth’s crust?", "Oxygen", "Titanium", "Silicon", "Helium", "generalScience");
        insertQuestion("What is the square root of 9?", "3", "4.5", "6", "3.5", "generalScience");
        insertQuestion("How many poles do all magnets have?", "2", "4", "6", "8", "generalScience");
        insertQuestion("What is the second lightest element?", "Helium", "Hydrogen", "Oxygen", "Silicon", "generalScience");
        insertQuestion("Which of the following scientists was born first?", "Copernicus", "Galileo", "Kepler", "Einstein", "generalScience");
        insertQuestion("Marie Curie is known for discovering what element _____.", "Radium", "Titanium", "Magnesium", "Manganese", "generalScience");
        insertQuestion("What is the name of Marie Curie’s husband?", "Pierre", "Philip", "James", "Howard", "generalScience");
        insertQuestion("Who is the father of Taxonomy?", "Linnaeus", "Burbank", "Gauss", "Darwin", "generalScience");
        insertQuestion("Which of the following categories does not belong to the official categories of the Nobel Prize?", "Biology", "Physics", "Chemistry", "Medicine", "generalScience");
        insertQuestion("How long does it take for the Earth to spin once on its axis?", "1 Day", "1 Hour", "1 Year", "1 Century", "generalScience");
        insertQuestion("What planet in our Solar System has the most gravity?", "Jupiter", "Mercury", "Mars", "Earth", "generalScience");
        insertQuestion("In Geology, how long is an eon?", "Billion Years", "Hundred Years", "Thousand Years", "Million Years", "generalScience");
        insertQuestion("The Big Bang Theory was first introduced by _____ in the 1920s.", "Georges Lemaitre", "Bill Prady", "Chuck Lorre", "Charles Darwin", "generalScience");
        insertQuestion("Marie Curie was _____ by birth.", "Polish", "French", "German", "Swedish", "generalScience");
        insertQuestion("Which scientist discovered Selenium and Cerium?", "Berzelius", "Lawrence", "Mendeleev", "Burbank", "generalScience");

        // - - > HISTORY QUESTIONS
        insertQuestion("Who was the mother of Mary I of England?", "Catherine of Aragon", "Jane Seymour", "Anne of Cleves", "Anne Boleyn", "History");
        insertQuestion("Where did Napoleon die?", "St. Helena Island", "Elba Island", "Paris", "Rome", "History");
        insertQuestion("What does A in NATO stand for?", "Atlantic", "American", "Area", "Archipelago", "History");
        insertQuestion("Which of these events happened first in American history?", "Declaration of Independence", "Civil War", "9/11 Attack", "Cluster's Last Stand", "History");
        insertQuestion("When did the Cold War start?", "1945", "1941", "1991", "1987", "History");
        insertQuestion("When did Martin Luther King Jr. die?", "1968", "1967", "1969", "1970", "History");
        insertQuestion("Which female pharaoh had the longest reign?", "Hatsheput", "Nitocris", "Merneith", "Twosret", "History");
        insertQuestion("Who is the only US president to serve more than 2 terms?", "Roosevelt", "Trump", "Washington", "Obama", "History");
        insertQuestion("Which one of the 7 ancient wonders of the world is still standing today?", "Great Pyramid of Giza", "Hanging Gardens of Babylon", "Temple of Artemis at Ephesus", "Statue of Zeus at Olympia", "History");
        insertQuestion("The attack on Pearl Harbor took place at which month?", "December", "September", "October", "November", "History");
        insertQuestion("What tower in New York City was the target for the 9/11 attack?", "World Trade Center", "Empire State Building", "Chrysler Building", "Flatiron Building", "History");
        insertQuestion("US Airways flight 1549 had an emergency landing at the _____ river.", "Atlantic", "American", "Area", "Archipelago", "History");
        insertQuestion("US Airways flight 1549 had an emergency landing because of _____.", "Bird Attack", "Engine Malfunction", "Medical Emergency", "Aircraft out of Gas", "History");
        insertQuestion("Osama Bin Laden was killed under the order of President _____.", "Obama", "Clinton", "Bush", "Trump", "History");
        insertQuestion("St. Patrick’s day was originally associated with what color?", "Blue", "Green", "Red", "Yellow", "History");
        insertQuestion("_____ is considered as the “Cradle of Civilization”", "mesopotamia", "indus", "yangtze", "Babylon", "History");
        insertQuestion("What is the longest river in Asia?", "yangtze", "amazon", "Tigris", "Euphrates", "History");
        insertQuestion("The Mandate of Heaven was important to the rulers of which ancient place?", "china", "india", "persia", "crete", "History");
        insertQuestion("Mao Zedong is considered to be the founding father of:", "modern china", "ancient japan", "modern iran", "ottoman empire", "History");
        insertQuestion("The Opium War was caused by ____________ introducing opium to China.", "brittain", "india", "russia", "france", "History");

        // - - > GEOGRAPHY QUESTIONS
        insertQuestion("How many stars are there in the US flag?", "50", "48", "47", "46", "Geography");
        insertQuestion("Managua is the capital of which country?", "nicaragua", "south africa", "nigeria", "uganda", "Geography");
        insertQuestion("Where is the deepest point in the Meditteranean sea?", "ionian sea", "cilician sea", "aegan sea", "balearic sea", "Geography");
        insertQuestion("The Black Forest is located in what European country?", "germany", "france", "netherlands", "italy", "Geography");
        insertQuestion("Canada is made up of how many provinces?", "10", "16", "9", "7", "Geography");
        insertQuestion("Which country has the longest coastline in the world?", "canada", "australia", "austria", "usa", "Geography");
        insertQuestion("What is the longest river in South America?", "amazon", "nile", "yellow", "mississippi", "Geography");
        insertQuestion("What city is the capital of China?", "beijing", "wuhan", "shanghai", "guangzhou", "Geography");
        insertQuestion("In what mountain range is Mount Everest located?", "himalayas", "andes", "rocky mountains", "cascade range", "Geography");
        insertQuestion("What is the largest lake in Africa?", "lake victoria", "lake malawi", "lake turkana", "lake kivu", "Geography");
        insertQuestion("What is the largest country based on surface area?", "russia", "china", "canada", "usa", "Geography");
        insertQuestion("What is the largest island in the Caribbean sea?", "cuba", "jamaica", "puerto rico", "guam", "Geography");
        insertQuestion("How many time zones are in Canada?", "6", "2", "4", "8", "Geography");
        insertQuestion("Which city is both located in Asia and Europe?", "istanbul", "stockholm", "amsterdam", "berlin", "Geography");
        insertQuestion("What is Earth’s largest ocean by surface area?", "pacific", "atlantic", "indian", "arctic", "Geography");
        insertQuestion("What is the smallest country in the world?", "vatican city", "republic of san marino", "principality of monaco", "tuvalu", "Geography");
        insertQuestion("What is the capital of Poland?", "warsaw", "torun", "lublin", "katowice", "Geography");
        insertQuestion("What is the largest desert in the world?", "antarctic", "sahara", "arabian", "arctic", "Geography");
        insertQuestion("What is Earth’s largest continent?", "asia", "africa", "europe", "antartica", "Geography");
        insertQuestion("Which country has the most natural lakes?", "canada", "india", "usa", "china", "Geography");

        // - - > ARTS QUESTION
        insertQuestion("Which French author wrote the novel “La dame aux camelias”?", "alexandre dumas", "emile zola", "jules verne", "victor hugo", "Arts");
        insertQuestion("Who is the author of “Da Davinci Code”?", "dan brown", "c.s. lewis", "rick riordan", "j.k. rowling", "Arts");
        insertQuestion("What was Dr. Frankenstein’s first name in the famous novel of Mary Shelly?", "victor", "hugo", "james", "dolly", "Arts");
        insertQuestion("In which city is the famous Prado museum located?", "madrid", "malaga", "pisa", "shanghai", "Arts");
        insertQuestion("Who is responsible for more than 1000 english words that is used today?", "william shakespear", "ernest hemingway", "mark twain", "george orwell", "Arts");
        insertQuestion("How many paintings did Vincent Van Gogh sell during his lifetime?", "1", "800", "193", "89", "Arts");
        insertQuestion("Who painted The Sistine Chapel?", "michelangelo", "vincent van gough", "leonardo da vnci", "calude monet", "Arts");
        insertQuestion("Who painted The Scream?", "edvard munch", "vincent van gough", "otto meuler", "pablo picasso", "Arts");
        insertQuestion("What painter was summoned to Rome in 1481 to decorate the walls of the Sistine Chapel?", "sandro boticelli", "raphael", "leonardo da vinci", "claude monet", "Arts");
        insertQuestion("Who is the art critic who coined the term \"impressionism\"?", "louis leroy", "jacob burckhardt", "john ruskin", "roger fry", "Arts");
        insertQuestion("Ludwig Van Beethoven is a famous _____ composer and pianist.", "german", "french", "italian", "polish", "Arts");
        insertQuestion("Who composed “Fur elise”?", "beethoven", "mozart", "vivaldi", "debussy", "Arts");
        insertQuestion("Which of the following is a composition of Antonio Vivaldi?", "four seasons", "turkish march", "claire de lune", "fur elise", "Arts");
        insertQuestion("Who wrote “Oliver Twist”?", "charles dickens", "mark twain", "oscar wilde", "ernest hemingway", "Arts");
        insertQuestion("In the book, Percy Jackson and the Olympians, Percy is the son of _____.", "poseidon", "zeus", "hades", "apollo", "Arts");
        insertQuestion("Which house does Hermione from Harry Potter belong to?", "gryffindor", "ravenclaw", "hufflepuff", "slytherin", "Arts");
        insertQuestion("What is the first name of Dumbledore?", "albus", "snape", "harry", "ron", "Arts");
        insertQuestion("Who wrote Percy Jackson and the Olympians?", "rick riordan", "james kinney", "j.k. rowling", "mark twain", "Arts");
        insertQuestion("Which of the following novels were written by George Orwell?", "animal farm", "for whom the bell tolls", "the pearl", "a tramp abroad", "Arts");
        insertQuestion("What is the main visual element in Impressionist painting?", "color", "lines", "texture", "shapes", "Arts");

        // - - > MYTHOLOGY QUESTIONS
        insertQuestion("In Greek mythology, who is regarded as the god of the sea?", "poseidon", "zeus", "hermes", "dionysus", "Mythology");
        insertQuestion("Who is the Roman equivalent of the Greek goddess Enyo?", "bellona", "minerva", "juno", "vesta", "Mythology");
        insertQuestion("In Greek mythology, who is the son of Zeus and Maia?", "hermes", "apollo", "poseidon", "dionysus", "Mythology");
        insertQuestion("In Greek mythology, what is Medusa’s hair made of?", "snakes", "stone", "insects", "fire", "Mythology");
        insertQuestion("Which mythical creature grows back two heads after a head has been cut off?", "hydra", "chimera", "satyr", "minotaur", "Mythology");
        insertQuestion("In Greek mythology, who is the goddess of the rainbow?", "iris", "athena", "aphrodite", "persephone", "Mythology");
        insertQuestion("Which mythical creature is half-man and half-bull?", "minotaur", "centaur", "satyr", "hydra", "Mythology");
        insertQuestion("What was the Greek hero Achilles’ only vulnerable part?", "heel", "neck", "head", "eye", "Mythology");
        insertQuestion("In Egyptian mythology, Osiris is a god of?", "justice", "sun", "moon", "thieves", "Mythology");
        insertQuestion("What was the first god called in Egyptian mythology?", "atum", "osiris", "horus", "ra", "Mythology");
        insertQuestion("In Egyptian mythology, which god rules over the underworld?", "anubis", "seth", "horus", "isis", "Mythology");
        insertQuestion("What form did the gods and goddesses of ancient Egypt typically take?", "animals", "plants", "humans", "statues", "Mythology");
        insertQuestion("Which Egyptian god was considered the most powerful and important?", "ra", "thoth", "horus", "atum", "Mythology");
        insertQuestion("Who is the primary, father god in Norse mythology?", "odin", "frey", "thor", "omega", "Mythology");
        insertQuestion("In Norse mythology, who is the god of thunder and lightning?", "thor", "odin", "loki", "frey", "Mythology");
        insertQuestion("Which day of the week is named for the Norse god Odin?", "wednesday", "thursday", "monday", "tuesday", "Mythology");
        insertQuestion("What is the name of Thor’s magical hammer?", "mjolnir", "orcsbane", "sleipnir", "gungnir", "Mythology");
        insertQuestion("According to Norse mythology, where do the heroes who die in battle go to rest?", "valhalla", "asgard", "midgard", "niffleheim", "Mythology");
        insertQuestion("In Norse mythology, who is the god of justice and war?", "tyr", "fafnir", "frey", "heimdall", "Mythology");
        insertQuestion("In Norse mythology, who was the faithful steed of Odin?", "sleipnir", "kelnir", "valnyr", "fystnir", "Mythology");
    }

    public ArrayList<Question>getAllQuestions(){
        ArrayList<Question>questions_array = new ArrayList<>();

        // - - > INITIATE QUESTIONS
        qdbPopulate();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from questions_table", null);
        // - - >
            while(res.moveToNext()){
                String question = res.getString(res.getColumnIndex(QUESTIONS_INQUIRY));
                String answer = res.getString(res.getColumnIndex(QUESTIONS_ANSWERS));
                String C1 = res.getString(res.getColumnIndex(QUESTIONS_C1));
                String C2 = res.getString(res.getColumnIndex(QUESTIONS_C2));
                String C3 = res.getString(res.getColumnIndex(QUESTIONS_C3));
                String category = res.getString(res.getColumnIndex(QUESTIONS_CATEGORY));

                Question temp = new Question.QuestionBuilder().setQuestion(question)
                        .setAnswer(answer)
                        .setChoices(C1, C2, C3)
                        .setCategory(category)
                        .build();
                questions_array.add(temp);
            }
        res.close();

        return questions_array;
    }
}
