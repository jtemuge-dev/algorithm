import dataStructures.ArrayLinearList;
import java.util.*;

public class Registration {
    public ArrayLinearList studentList;
    public ArrayLinearList subjectList;
    public ArrayLinearList majorList;

    public void loadAllFiles() {
        subjectList = Subject.readSubjects("Subjects.txt");
        majorList = Major.readMajors("Professions.txt");
        studentList = Exams.readExams("Exams.txt");

        for ( int i = 0 ; i < studentList.size() ; i++ ) {
            Student s = (Student) studentList.get(i);
            s.calculateGPA(subjectList);
        }
    }

    public void showAllData() {
        System.out.println("\n| SUBJECTS |");
        for ( int i = 0 ; i < subjectList.size() ; i++ )
            System.out.println(subjectList.get(i));

        System.out.println("\n| MAJORS |");
        for ( int i = 0 ; i < majorList.size() ; i++ )
            System.out.println(majorList.get(i));

        System.out.println("\n| STUDENTS' AVEREGE GPAs |");
        for ( int i = 0 ; i < studentList.size() ; i++ ) {
            Student s = (Student) studentList.get(i);
            System.out.println(s);
            for ( int j = 0 ; j < s.lessons.size() ; j++ ) {
                System.out.println("   " + s.lessons.get(j));
            }
        }
    }

    public void showExpelledStudents() {
        System.out.println("\n| STUDENTS TO BE EXPELLED |");
        for ( int i = 0 ; i < studentList.size() ; i++ ) {
            Student s = (Student) studentList.get(i);
            int fCount = 0;

            for ( int j = 0 ; j < s.lessons.size() ; j++ ) {
                Lessons l = (Lessons) s.lessons.get(j);
                if ( l.score < 60 ) fCount++;
            }

            if ( fCount >= 3 ) {
                System.out.println(s.code + " - " + fCount + " Fs, GPA: " +
                        String.format("%.2f", s.GPA));
            }
        }
    }

    public void showGradesByLessons() {
        System.out.println("\nSTUDENT GRADES BY LESSONS:");
        for (int i = 0; i < subjectList.size(); i++) {
            Subject subj = (Subject) subjectList.get(i);
            System.out.println("\n" + subj.code + " / " + subj.name + " (" + subj.credit + " credits)");

            for (int j = 0; j < studentList.size(); j++) {
                Student s = (Student) studentList.get(j);
                for (int k = 0; k < s.lessons.size(); k++) {
                    Lessons l = (Lessons) s.lessons.get(k);
                    if (l.learned.code.equalsIgnoreCase(subj.code)) {
                        if (l.inProgress || l.score < 0)
                            System.out.println(" | " + s.code + " : (Learning)");
                        else
                            System.out.println(" | " + s.code + " : " + l.score + " (" + getGradeLetter(l.score) + ")");
                    }
                }
            }
        }
    }


    public void showGradesByMajors() {
        System.out.println("\nSTUDENT GRADES BY MAJOR:");
        java.util.HashMap<String, java.util.ArrayList<String>> majorGrades = new java.util.HashMap<>();

        for (int i = 0; i < studentList.size(); i++) {
            Student s = (Student) studentList.get(i);
            for (int j = 0; j < s.lessons.size(); j++) {
                Lessons l = (Lessons) s.lessons.get(j);
                String subjCode = l.learned.code;
                String majorPrefix = getPrefix(subjCode);

                if (!majorGrades.containsKey(majorPrefix))
                    majorGrades.put(majorPrefix, new java.util.ArrayList<>());

                String gradeInfo;
                if (l.inProgress || l.score < 0)
                    gradeInfo = s.code + " → " + subjCode + " : (Learning)";
                else
                    gradeInfo = s.code + " → " + subjCode + " : " + l.score + " (" + getGradeLetter(l.score) + ")";

                majorGrades.get(majorPrefix).add(gradeInfo);
            }
        }

        for (int i = 0; i < majorList.size(); i++) {
            Major m = (Major) majorList.get(i);
            System.out.println("\n" + m.code + " / " + m.name);
            java.util.ArrayList<String> list = majorGrades.get(m.code);
            if (list != null) {
                for (String line : list)
                    System.out.println(" | " + line);
            } else {
                System.out.println("   (No grades for this major)");
            }
        }
    }


    private String getGradeLetter(float score) {
        if (score >= 96) return "A";
        if (score >= 91) return "A-";
        if (score >= 88) return "B+";
        if (score >= 84) return "B";
        if (score >= 81) return "B-";
        if (score >= 78) return "C+";
        if (score >= 74) return "C";
        if (score >= 71) return "C-";
        if (score >= 68) return "D+";
        if (score >= 64) return "D";
        if (score >= 61) return "D-";
        return "F";
    }

    private String getPrefix(String code) {
        if (code == null || code.length() < 2)
            return "??";
        return code.substring(0, 2).toUpperCase();
    }
}










//import java.util.*;
//import dataStructures.ArrayLinearList;
//
//public class Registration {
//    public ArrayList<Student> studentList;
//    public ArrayList<Subject> subjectList;
//    public ArrayList<Major> majorList;
//
//
//
//    public void loadAllFiles() {
//        subjectList = Subject.readSubjects("Subjects.txt");
//        majorList = Major.readMajors("Professions.txt");
//        studentList = Exams.readExams("Exams.txt");
//
//        for ( Student s : studentList )
//            s.calculateGPA(subjectList);
//    }
//
//
//
//    public void showAllData() {
//        System.out.println( "\nSUBJECTS:" );
//        for ( Subject s : subjectList ) System.out.println(s);
//
//        System.out.println( "\nMAJORS:" );
//        for ( Major m : majorList ) System.out.println(m);
//
//        System.out.println( "\nSTUDENTS:" );
//        for ( Student s : studentList ) {
//            System.out.println(s);
//            for ( Lessons l : s.lessons )
//                System.out.println( "   " + l );
//        }
//    }
//
//
//
//    public void showExpelledStudents() {
//        System.out.println( "\nSTUDENTS TO BE EXPELLED:" );
//        for ( Student s : studentList ) {
//            int fCount = 0;
//            for ( Lessons l : s.lessons ) {
//                if ( l.score < 60 ) fCount++;
//            }
//            if ( fCount >= 3 ) {
//                System.out.println( s.code + " - " + fCount + " Fs" +", GPA: " +
//                        String.format( "%.2f", s.GPA ) );
//            }
//        }
//    }
//
//
//
//    public void showGradesByLessons() {
//        System.out.println("\nSTUDENT GRADES BY LESSONS:");
//        for ( Subject subj : subjectList ) {
//            System.out.println( "\n" +  subj.code + " / " + subj.name + " (" + subj.credit + " credits)" );
//            for ( Student s : studentList ) {
//                for ( Lessons l : s.lessons ) {
//                    if ( l.learned.equals( subj.code ) ) {
//                        System.out.println( " | " + s.code + " : " + l.score + " (" + getGradeLetter(l.score) + ")" );
//                    }
//                }
//            }
//        }
//    }
//
//
//
//    public void showGradesByMajors() {
//        System.out.println("\nSTUDENT GRADES BY MAJOR:");
//
//        HashMap<String, ArrayList<String>> majorGrades = new HashMap<>();
//
//        for ( Student s : studentList ) {
//            for ( Lessons l : s.lessons ) {
//                String subjCode = l.learned;
//                String majorPrefix = getPrefix( subjCode );
//                String majorName = getMajorNameFromCode( majorPrefix );
//
//                if ( !majorGrades.containsKey( majorPrefix ) )
//                    majorGrades.put( majorPrefix, new ArrayList<>() );
//
//                String gradeInfo = s.code + " → " + subjCode + " : " + l.score + " (" + getGradeLetter(l.score) + ")";
//                majorGrades.get(majorPrefix).add(gradeInfo);
//            }
//        }
//
//        for (Major m : majorList) {
//            System.out.println("\n" + m.code + " / " + m.name);
//            ArrayList<String> list = majorGrades.get(m.code);
//            if (list != null) {
//                for (String line : list)
//                    System.out.println( " | " + line);
//            } else {
//                System.out.println("   (No grades for this major)");
//            }
//        }
//    }
//
//
//
//    private String getGradeLetter( float score ) {
//        if ( score >= 96 ) return "A";
//        if ( score >= 91 ) return "A-";
//        if ( score >= 88 ) return "B+";
//        if ( score >= 84 ) return "B";
//        if ( score >= 81 ) return "B-";
//        if ( score >= 78 ) return "C+";
//        if ( score >= 74 ) return "C";
//        if ( score >= 71 ) return "C-";
//        if ( score >= 68 ) return "D+";
//        if ( score >= 64 ) return "D";
//        if ( score >= 61 ) return "D-";
//        return "F";
//    }
//
//
//
//    private String getPrefix( String code ) {
//        if ( code == null || code.length() < 2 )
//            return "??";
//        return code.substring(0, 2).toUpperCase();
//    }
//
//
//
//    private String getMajorNameFromCode( String prefix ) {
//        for ( Major m : majorList ) {
//            if ( m.code.equalsIgnoreCase( prefix ) )
//                return m.name;
//        }
//        return "Unknown";
//    }
//}
