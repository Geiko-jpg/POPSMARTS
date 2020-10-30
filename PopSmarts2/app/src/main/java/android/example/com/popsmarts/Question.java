package android.example.com.popsmarts;

public class Question {
    private String question;
    private String answer;
    private String C1, C2, C3;
    private String category;

    private Question(){}

    public static class QuestionBuilder{
        private String question;
        private String answer;
        private String C1, C2, C3;
        private String category;

        public QuestionBuilder setQuestion(String question){
            this.question = question;
            return this;
        }

        public QuestionBuilder setAnswer(String answer){
            this.answer = answer;
            return this;
        }

        public QuestionBuilder setChoices(String C1, String C2, String C3){
            this.C1 = C1;
            this.C2 = C2;
            this.C3 = C3;
            return this;
        }

        public QuestionBuilder setCategory (String category){
            this.category = category;
            return this;
        }

        public Question build(){
            Question returnQuestion = new Question();
            returnQuestion.question = this.question;
            returnQuestion.answer = this.answer;
            returnQuestion.C1 = this.C1;
            returnQuestion.C2 = this.C2;
            returnQuestion.C3 = this.C3;
            returnQuestion.category = this.category;

            return returnQuestion;
        }
    }

    // - - > GETTERS
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getC1() {
        return C1;
    }

    public String getC2() {
        return C2;
    }

    public String getC3() {
        return C3;
    }

    public String getCategory(){
        return category;
    }
}
