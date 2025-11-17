import java.io.*;
import dataStructures.ArrayLinearList;

public class Major {
    public String code;
    public String name;

    public String toString() {
        return code + " / " + name;
    }

    public static ArrayLinearList readMajors( String fileName ) {
        ArrayLinearList majors = new ArrayLinearList();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ( (line = br.readLine()) != null ) {
                line = line.trim();
                if ( line.isEmpty() ) continue;

                String[] p = line.split("[,/]");
                if ( p.length < 2 ) continue;

                Major m = new Major();
                m.code = p[0].trim();
                m.name = p[1].trim();

                majors.add(majors.size(), m);
            }
        } catch (Exception e) {
            System.out.println("Error reading majors: " + e.getMessage());
        }
        return majors;
    }
}