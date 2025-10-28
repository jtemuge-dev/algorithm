import dataStructures.Chain;
import dataStructures.ArrayLinearList;

public class Student {
    public String code;
    public float GPA;
    public Chain lessons = new Chain();

    public String toString() {
        return "Student: " + code + " | GPA: " + String.format("%.2f", GPA);
    }

    public void calculateGPA(ArrayLinearList subjectList) {
        float totalPoints = 0.0f;
        float totalCredits = 0.0f;

        for ( int i = 0 ; i < lessons.size() ; i++ ) {
            Lessons l = (Lessons) lessons.get(i);
            if ( l == null || l.learned == null ) continue;

            Subject subj = findSubject(subjectList, l.learned.code);
            if ( subj == null ) continue;

            float credit = subj.credit;
            float gradePoint = convertPointToGPA((float) l.score);
            totalPoints += gradePoint * credit;
            totalCredits += credit;
        }

        if ( totalCredits > 0.0f ) {
            GPA = totalPoints / totalCredits;
        } else {
            GPA = 0.0f;
        }
    }
    private float convertPointToGPA(float point) {
        if (point >= 95) return 4.0f;
        if (point >= 90) return 3.7f;
        if (point >= 87) return 3.4f;
        if (point >= 83) return 3.0f;
        if (point >= 80) return 2.7f;
        if (point >= 77) return 2.4f;
        if (point >= 73) return 2.0f;
        if (point >= 70) return 1.7f;
        if (point >= 67) return 1.4f;
        if (point >= 63) return 1.0f;
        if (point >= 60) return 0.7f;
        if (point >= 59) return 0.5f;
        return 0.0f;
    }

    private Subject findSubject(ArrayLinearList subjectList, String code) {
        if ( subjectList == null ) return null;
        for ( int i = 0 ; i < subjectList.size() ; i++ ) {
            Subject s = (Subject) subjectList.get(i);
            if ( s != null && s.code != null && s.code.equalsIgnoreCase(code) )
                return s;
        }
        return null;
    }
}











//import java.util.*;
//import dataStructures.Chain;
//
//public class Student {
//    public String code;
//    public float GPA;
//    public ArrayList<Lessons> lessons;
//
//
//
//    public Student( String code ) {
//        this.code = code;
//        this.GPA = 0;
//        this.lessons = new ArrayList<>();
//    }
//
//
//
//    public void addLesson( String subjectCode, float score ) {
//        lessons.add( new Lessons( subjectCode, score ) );
//    }
//
//
//
//    public void calculateGPA( ArrayList<Subject> subjects ) {
//        float totalPoints = 0, totalCredits = 0;
//        for ( Lessons l : lessons ) {
//            Subject subj = findSubject( l.learned, subjects );
//            if ( subj != null ) {
//                totalPoints += convertPointToGPA( l.score ) * subj.credit;
//                totalCredits += subj.credit;
//            }
//        }
//        GPA = totalCredits > 0 ? totalPoints / totalCredits : 0;
//    }
//
//
//
//    private Subject findSubject( String code, ArrayList<Subject> subjects ) {
//        for ( Subject s : subjects ) {
//            if ( s.code.equals(code) ) return s;
//        }
//        return null;
//    }
//
//
//
//    private float convertPointToGPA(float point) {
//        if ( point >= 96 ) return 4.0f;
//        if ( point >= 91 ) return 3.7f;
//        if ( point >= 88 ) return 3.4f;
//        if ( point >= 84 ) return 3.0f;
//        if ( point >= 81 ) return 2.7f;
//        if ( point >= 78 ) return 2.4f;
//        if ( point >= 74 ) return 2.0f;
//        if ( point >= 71 ) return 1.7f;
//        if ( point >= 68 ) return 1.4f;
//        if ( point >= 64 ) return 1.0f;
//        if ( point >= 61 ) return 0.7f;
//        return 0.0f;
//    }
//
//
//
//    @Override
//    public String toString() {
//        return "Student: " + code + ", GPA: " + GPA;
//    }
//}
