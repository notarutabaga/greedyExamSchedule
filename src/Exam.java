import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class Exam implements Comparable<Exam> {
    String courseName;
    int startTime, endTime; //military time

    public Exam(String courseName, int startTime, int endTime) {
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int compareTo(Exam rhs) {
        if (startTime == rhs.startTime) {
            return Integer.compare(endTime, rhs.endTime);
        }

        return Integer.compare(startTime, rhs.startTime);
    }

    @Override
    public String toString() {
        return (courseName + " " + startTime + " " + endTime);
    }
}
