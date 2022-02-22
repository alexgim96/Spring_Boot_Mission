package mission.mission2.entity;

public class Board {
    private static int count = 0;
    private String name;
    private int id;

    public Board() {
    }

    public Board(String name) {
        count++;
        this.name = name;
        this.id = count;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Board.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
