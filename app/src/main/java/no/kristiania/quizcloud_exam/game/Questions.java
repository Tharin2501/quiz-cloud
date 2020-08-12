package no.kristiania.quizcloud_exam.game;

public class Questions {

    public String questions[] = {
            "Polo G Feat. Lil Tjay: _________",
            "A$AP Rocky Feat. Skepta: _________",
            "Lil Nas X Feat. Billy Ray Cyrus: _________",
            "Final Question",

    };

    // Array of choices
    private String myChoices[][] = {
            {"Animosity", "Pop Out", "Polo", "Battle Cry"},
            {"Praise The Lord", "Conquer", "I Came I Saw", "Respect"},
            {"Horses", "Road", "Old Town Road", "Ridding On My Horse"},
            {"Vampire In The Moonlight", "Eyes Closed", "Love A Vampire", "Your Favorite Dress"},

    };

    // Array of correct answers
    private String correctAnswers[] = {"Pop Out", "Praise The Lord", "Old Town Road", "Your Favorite Dress"};

    public String getQuestion(int a) {
        String question = questions[a];
        return question;
    }

    // getter for different answers we can choose
    public String getChoice1(int a) {
        String choice = myChoices[a][0];
        return choice;
    }
    public String getChoice2(int a) {
        String choice = myChoices[a][1];
        return choice;
    }
    public String getChoice3(int a) {
        String choice = myChoices[a][2];
        return choice;
    }
    public String getChoice4(int a) {
        String choice = myChoices[a][3];
        return choice;
    }


    public String getCorrectAnswer(int a) {
        String answer = correctAnswers[a];
        return answer;
    }

}