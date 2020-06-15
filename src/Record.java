import java.io.FileWriter;
import java.io.IOException;

public class Record {
    public String one;
    public String two;
    public int three;
    public int four;
    public int five;

    public Record(String one, String two, int three, int four, int five) {
        this.one = one;

        //one.compareTo(two);

        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
    }

    public void addInFile(){
        try(FileWriter writer = new FileWriter("Record.txt", true))
        {
            writer.write(one);
            writer.append('\n');

            writer.write(two);
            writer.append('\n');

            writer.write(Integer.toString(three));
            writer.append('\n');

            writer.write(Integer.toString(four));
            writer.append('\n');

            writer.write(Integer.toString(five));
            writer.append('\n');

            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
