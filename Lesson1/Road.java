package Lesson1;

public class Road {
    int roadLength;

    public Road(int roadLength) {
        this.roadLength = roadLength;
    }

    public boolean checkRun(CanRun runner) {
        return runner.run(roadLength) > roadLength;
    }
}
