public class Lessons {
    public Subject learned;
    public int score;
    public boolean inProgress;



    public Lessons() {
        this.learned = null;
        this.score = -1;
        this.inProgress = false;
    }
    public Lessons(Subject learned, int score) {
        this.learned = learned;
        this.score = score;
        this.inProgress = (score < 0);
    }
    public static Lessons fromLine(String[] parts) {
        Lessons l = new Lessons();
        l.learned = new Subject();
        l.learned.code = parts[1].trim();

        if (parts.length >= 3 && !parts[2].trim().isEmpty()) {
            try {
                l.score = Integer.parseInt(parts[2].trim());
                l.inProgress = (l.score < 0);
            } catch (NumberFormatException e) {
                l.score = -1;
                l.inProgress = true;
            }
        } else {
            l.score = -1;
            l.inProgress = true;
        }
        return l;
    }


    public String toString() {
        if (inProgress)
            return learned.code + " : (Learning)";
        else
            return learned.code + " : " + score;
    }
}