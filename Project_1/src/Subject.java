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