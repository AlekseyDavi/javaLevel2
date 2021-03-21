package Lesson1;

public class HW1 {
    public static void main(String[] args) {
        Cat cat = new Cat("Gor", 10, 10);
        Person person = new Person("Roc", 6, 14);
        Robot robot = new Robot("Tep", 14, 7);
        Wall[] walls = {
                new Wall(1),
                new Wall(2),
                new Wall(6),
                new Wall(10),
                new Wall(12)};
        Road[] roads = {
                new Road(1),
                new Road(2),
                new Road(6),
                new Road(10),
                new Road(12)};
        for (int i = 0; i < walls.length; i++) {
            walls[i].checkJump(cat);
            if (!walls[i].checkJump(cat)) {
                break;
            }
        }
        for (int i = 0; i < roads.length; i++) {
            roads[i].checkRun(cat);
            if (!roads[i].checkRun(cat)) {
                break;
            }

        }
    }
}
