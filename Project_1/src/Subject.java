import java.io.*;
import dataStructures.ArrayLinearList;

public class Subject {
    public String code;
    public String name;
    public float credit;

    public String toString() {
        return code + " / " + name + " (" + credit + ")";
    }

    public static ArrayLinearList readSubjects( String fileName ) {
        ArrayLinearList subjects = new ArrayLinearList();
        try ( BufferedReader br = new BufferedReader( new FileReader( fileName ) ) ) {
            String line;
            while ( ( line = br.readLine() ) != null ) {
                line = line.trim();
                if ( line.isEmpty() ) continue;

                String[] p = line.split("[,/]");
                if ( p.length < 3 ) continue;

                Subject s = new Subject();
                s.code = p[0].trim();
                s.name = p[1].trim();
                s.credit = Float.parseFloat( p[2].trim() );

                subjects.add(subjects.size(), s);
            }
        } catch (Exception e) {
            System.out.println("Error reading subjects: " + e.getMessage());
        }
        return subjects;
    }
}










//import java.io.*;
//import java.util.*;
//
//public class Subject {
//
//    public String code;
//    public String name;
//    public float credit;
//
//
//
//    public Subject(String code, String name, float credit) {
//        this.code = code;
//        this.name = name;
//        this.credit = credit;
//    }
//
//
//
//    @Override
//    public String toString() {
//        return code + " / " + name + " / " + credit;
//    }
//
//
//
//    public static ArrayList<Subject> readSubjects( String fileName ) {
//        ArrayList<Subject> list = new ArrayList<>();
//        try ( BufferedReader br = new BufferedReader( new FileReader(fileName) ) ) {
//            String line;
//            while ( ( line = br.readLine() ) != null ) {
//                String[] v = line.split( "/" );
//                if ( v.length == 3 ) {
//                    String code = v[0];
//                    String name = v[1];
//                    float credit = Float.parseFloat( v[2] );
//                    list.add( new Subject( code, name, credit ) );
//                }
//            }
//        } catch ( IOException e ) {
//            System.out.println( "Error reading subjects file: " + fileName );
//        }
//        return list;
//    }
//}
