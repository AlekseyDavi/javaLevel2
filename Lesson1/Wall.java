package Lesson1;

public class Wall {
    int wallHeight;

    public Wall(int wallHeight) {
        this.wallHeight = wallHeight;
    }

    public boolean checkJump(CanJump jumper) {
        return jumper.jump(wallHeight) > wallHeight;
    }
}
