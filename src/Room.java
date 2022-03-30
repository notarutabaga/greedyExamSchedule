import java.util.ArrayList;

public class Room {
    ArrayList<Exam> roomExams;
    String name;

    public Room(ArrayList<Exam> roomExams, String name) {
        this.roomExams = roomExams;
        this.name = name;
    }

    @Override
    public String toString() {
        return (roomExams.toString());
    }
}
