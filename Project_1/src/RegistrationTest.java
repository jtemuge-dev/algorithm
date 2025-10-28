import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import org.junit.jupiter.api.*;
import dataStructures.ArrayLinearList;

public class RegistrationTest {

    private File subjectsFile;
    private File majorsFile;
    private File examsFile;

    @BeforeEach
    public void setup() throws IOException {

        subjectsFile = File.createTempFile("Subjects", ".txt");
        try (PrintWriter pw = new PrintWriter(subjectsFile)) {
            pw.println("CS101, Introduction to CS, 3");
            pw.println("MA101, Calculus I, 4");
        }

        majorsFile = File.createTempFile("Professions", ".txt");
        try (PrintWriter pw = new PrintWriter(majorsFile)) {
            pw.println("CS, Computer Science");
            pw.println("MA, Mathematics");
        }

        examsFile = File.createTempFile("Exams", ".txt");
        try (PrintWriter pw = new PrintWriter(examsFile)) {
            pw.println("S001,CS101,95");
            pw.println("S001,MA101,85");
            pw.println("S002,CS101,78");
        }
    }

    @AfterEach
    public void cleanup() {
        subjectsFile.delete();
        majorsFile.delete();
        examsFile.delete();
    }

    @Test
    public void testReadSubjects() {
        ArrayLinearList subjects = Subject.readSubjects(subjectsFile.getAbsolutePath());
        assertEquals(2, subjects.size());

        Subject s1 = (Subject) subjects.get(0);
        assertEquals("CS101", s1.code);
        assertEquals("Introduction to CS", s1.name);
        assertEquals(3.0f, s1.credit);
    }

    @Test
    public void testReadMajors() {
        ArrayLinearList majors = Major.readMajors(majorsFile.getAbsolutePath());
        assertEquals(2, majors.size());

        Major m1 = (Major) majors.get(0);
        assertEquals("CS", m1.code);
        assertEquals("Computer Science", m1.name);
    }

    @Test
    public void testReadExamsAndGPA() {
        ArrayLinearList subjects = Subject.readSubjects(subjectsFile.getAbsolutePath());
        ArrayLinearList students = Exams.readExams(examsFile.getAbsolutePath());

        assertEquals(2, students.size());

        Student s1 = (Student) students.get(0);
        assertEquals("S001", s1.code);
        s1.calculateGPA(subjects);

        assertTrue(s1.GPA > 0.0);

        Student s2 = (Student) students.get(1);
        s2.calculateGPA(subjects);
        assertTrue(s2.GPA > 0.0);
    }

    @Test
    public void testLessonsFromLine() {
        String[] parts = {"S003", "CS101", "88"};
        Lessons l = Lessons.fromLine(parts);

        assertEquals("CS101", l.learned.code);
        assertEquals(88, l.score);
        assertFalse(l.inProgress);
    }

    @Test
    public void testRegistrationLoadAllFiles() {
        Registration reg = new Registration();

        reg.subjectList = Subject.readSubjects(subjectsFile.getAbsolutePath());
        reg.majorList = Major.readMajors(majorsFile.getAbsolutePath());
        reg.studentList = Exams.readExams(examsFile.getAbsolutePath());

        for (int i = 0; i < reg.studentList.size(); i++) {
            Student s = (Student) reg.studentList.get(i);
            s.calculateGPA(reg.subjectList);
        }

        assertEquals(2, reg.subjectList.size());
        assertEquals(2, reg.majorList.size());
        assertEquals(2, reg.studentList.size());
    }

}
