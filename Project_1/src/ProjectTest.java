import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import dataStructures.ArrayLinearList;

public class ProjectTest {

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
            pw.println("S002,MA101,-1"); // in progress
        }
    }

    @AfterEach
    public void cleanup() {
        subjectsFile.delete();
        majorsFile.delete();
        examsFile.delete();
    }

    @Test
    public void testSubjects() {
        ArrayLinearList subjects = Subject.readSubjects(subjectsFile.getAbsolutePath());
        assertEquals(2, subjects.size());

        Subject s = (Subject) subjects.get(0);
        assertEquals("CS101", s.code);
        assertEquals("Introduction to CS", s.name);
        assertEquals(3.0f, s.credit);
    }

    @Test
    public void testMajors() {
        ArrayLinearList majors = Major.readMajors(majorsFile.getAbsolutePath());
        assertEquals(2, majors.size());

        Major m = (Major) majors.get(1);
        assertEquals("MA", m.code);
        assertEquals("Mathematics", m.name);
    }

    @Test
    public void testLessonsFromLine() {
        String[] parts = {"S003", "CS101", "88"};
        Lessons l = Lessons.fromLine(parts);
        assertEquals("CS101", l.learned.code);
        assertEquals(88, l.score);
        assertFalse(l.inProgress);

        String[] parts2 = {"S003", "MA101"}; // no score → inProgress
        Lessons l2 = Lessons.fromLine(parts2);
        assertTrue(l2.inProgress);
        assertEquals(-1, l2.score);
    }

    @Test
    public void testExams() {
        ArrayLinearList students = Exams.readExams(examsFile.getAbsolutePath());
        assertEquals(2, students.size());

        Student s1 = (Student) students.get(0);
        assertEquals("S001", s1.code);
        assertEquals(2, s1.lessons.size());

        Lessons l1 = (Lessons) s1.lessons.get(0);
        assertEquals("CS101", l1.learned.code);
        assertEquals(95, l1.score);
        assertFalse(l1.inProgress);

        Lessons l2 = (Lessons) s1.lessons.get(1);
        assertEquals("MA101", l2.learned.code);
        assertEquals(85, l2.score);
        assertFalse(l2.inProgress);

        Student s2 = (Student) students.get(1);
        assertEquals("S002", s2.code);
        assertEquals(2, s2.lessons.size());

        Lessons l3 = (Lessons) s2.lessons.get(0);
        assertEquals("CS101", l3.learned.code);
        assertEquals(78, l3.score);
        assertFalse(l3.inProgress);

        Lessons l4 = (Lessons) s2.lessons.get(1);
        assertEquals("MA101", l4.learned.code);
        assertEquals(-1, l4.score);
        assertTrue(l4.inProgress);
    }


    @Test
    public void testRegistration() {
        Registration reg = new Registration();

        // Use temporary files directly
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
