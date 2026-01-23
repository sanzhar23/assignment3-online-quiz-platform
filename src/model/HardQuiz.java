package model;

public class HardQuiz extends Quiz {

    public HardQuiz(String name) {
        super(0, name, "HARD");
    }

    public HardQuiz(int id, String name) {
        super(id, name, "HARD");
    }

    @Override
    public int difficultyWeight() {
        return 2;
    }
}
