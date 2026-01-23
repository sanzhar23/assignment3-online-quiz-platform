package model;

public class EasyQuiz extends Quiz {

    public EasyQuiz(String name) {
        super(0, name, "EASY");
    }

    public EasyQuiz(int id, String name) {
        super(id, name, "EASY");
    }

    @Override
    public int difficultyWeight() {
        return 1;
    }
}
