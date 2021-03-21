package Lesson1;

public class Robot implements CanRun, CanJump {
    String name;
    int maxRunLength;
    int maxJumpHeight;

    public Robot(String name, int maxRunLength, int maxJumpHeight) {
        this.name = name;
        this.maxRunLength = maxRunLength;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public int jump(int height) {
        return maxJumpHeight;
    }

    @Override
    public int run(int length) {
        return maxRunLength;
    }
}
