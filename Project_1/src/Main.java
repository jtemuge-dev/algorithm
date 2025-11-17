public class Main {
    public static void main(String[] args) {

        Registration reg = new Registration();
        reg.loadAllFiles();
        reg.showAllData();
        reg.showExpelledStudents();
        reg.showGradesByLessons();
        reg.showGradesByMajors();
    }
}
