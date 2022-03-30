import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();

        Exam[] exams = m.fillExams();
        int numExams = exams.length;
        for (int i = 0; i < numExams; i++) {
            System.out.println(exams[i].toString());
        }

        ArrayList<Room> schedule = m.makeSchedule(exams);

        m.displaySchedule(schedule);
    }

    public Exam[] fillExams() {
        File file = new File("in.txt");
        try {
            Scanner scanner = new Scanner(file);
            int numExams = Integer.parseInt(scanner.nextLine());
            Exam[] exams = new Exam[numExams];

            for (int i = 0; i < numExams; i++) {
                String[] line = scanner.nextLine().split(" ");
                int length = line.length;

                int startTime = Integer.parseInt(line[length - 2]);
                int endTime = Integer.parseInt(line[length - 1]);

                String courseName = line[0];
                for (int j = 1; j < length - 2; j++) {
                    courseName = courseName.concat(" " + line[j]);
                }

                exams[i] = new Exam(courseName, startTime, endTime);
            }

            Arrays.sort(exams);
            return exams;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Room> makeSchedule(Exam[] exams) {
        ArrayList<Room> schedule = new ArrayList<>();
        int numExams = exams.length;

        for (int i = 0; i < numExams; i++) {
            Exam currentExam = exams[i];
            int currentStartTime = currentExam.startTime;

            int numRooms = schedule.size();
            int j = 0;
            while (j < numRooms) {
                Room compareRoom = schedule.get(j);
                int k = compareRoom.roomExams.size() - 1;
                Exam compareExam = compareRoom.roomExams.get(k);
                int compareEndTime = compareExam.endTime;

                if (currentStartTime >= compareEndTime) {
                    schedule.get(j).roomExams.add(currentExam);
                    j++;
                    break;
                }
                j++;
            }

            if (j == 0 || j == numRooms) {
                ArrayList<Exam> newRoomExam = new ArrayList<>();
                newRoomExam.add(currentExam);
                Room newRoom = new Room(newRoomExam, "room " + (j + 1));
                schedule.add(newRoom);
            }
        }

        return schedule;
    }

    public void displaySchedule(ArrayList<Room> schedule) {
        int numRooms = schedule.size();

        for (int i = 0; i < numRooms; i++) {
            System.out.print(schedule.get(i).name + " ");
            System.out.println(schedule.get(i));
        }
    }
}
