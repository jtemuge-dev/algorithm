import java.io.*;
import dataStructures.ArrayLinearList;
import dataStructures.Chain;

public class Exams {

    public static ArrayLinearList readExams(String filename) {
        ArrayLinearList studentList = new ArrayLinearList();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("[,/]");
                if (parts.length < 2) continue;

                String studentCode = parts[0].trim();

                Student student = findOrCreateStudent(studentList, studentCode);

                Lessons l = Lessons.fromLine(parts);

                student.lessons.add(student.lessons.size(), l);
            }
        } catch (Exception e) {
            System.out.println("Error reading exams file: " + e.getMessage());
        }

        return studentList;
    }

    private static Student findOrCreateStudent(ArrayLinearList list, String code) {
        for (int i = 0; i < list.size(); i++) {
            Student s = (Student) list.get(i);
            if (s.code != null && s.code.equalsIgnoreCase(code)) return s;
        }

        Student newS = new Student();
        newS.code = code;
        newS.lessons = new Chain();
        list.add(list.size(), newS);
        return newS;
    }

}