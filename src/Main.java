
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Object[] Record = null;
    public static Random rnd = new Random(System.currentTimeMillis());
    public static int numberRecord;
    public static Record records[];
    public static int sort1;
    public static int sort2;
    public static int sort3;

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);   //Очищаем файл прошлых записей
        System.out.print("Введите количество записей: ");
        numberRecord = in.nextInt();
        generationRecord();
        displayRecord();

        System.out.print("Введите номера тех полей, по которым будет выполняться сортировка: ");
        sort1 = in.nextInt();
        sort2 = in.nextInt();
        sort3 = in.nextInt();

        QuickSort sort = new QuickSort(records, numberRecord);
        sort.quickSort(sort1, sort2, sort3);

        displayRecord();
    }

    ///Генерация записей
    public static void generationRecord() throws IOException {
        records = new Record[numberRecord];
        for(int i = 0; i < numberRecord; i++) {
            records[i] = new Record(randomLineFile("name.txt"), randomLineFile("surname.txt"), rnd.nextInt(32 - 1),rnd.nextInt(13 - 1),rnd.nextInt(100 - 1));
        }
    }

    ///Получаем рандомную строку файла
    public static String randomLineFile(String nameFile) throws IOException {
        int numberRow = countLinesNew(nameFile);
        try(FileReader fr = new FileReader(nameFile))
        {
            int randRow = rnd.nextInt(numberRow - 0);
            BufferedReader reader = new BufferedReader(fr);
            while(randRow-- > 0) { //Пропускаем
                reader.readLine();
            }
            String buf = reader.readLine();
            reader.close();
            fr.close();
            return buf;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return "";
    }

    //Отображение записей в консоли
    public static void displayRecord() {
        try(FileWriter writer = new FileWriter("Record.txt", false)){writer.flush();}catch(IOException ex){System.out.println(ex.getMessage());}
        for(int i = 0; i < numberRecord; i++) {
            records[i].addInFile();
            System.out.println(records[i].one);
            System.out.println(records[i].two);
            System.out.println(records[i].three);
            System.out.println(records[i].four);
            System.out.println(records[i].five);
            System.out.println();
        }
    }


    ///Получение количества строк файла
    public static int countLinesNew(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                // bail out if nothing to read
                return 0;
            }

            // make it easy for the optimizer to tune this loop
            int count = 0;
            while (readChars == 1024) {
                for (int i=0; i<1024;) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            // count remaining characters
            while (readChars != -1) {
                for (int i=0; i<readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            return count == 0 ? 1 : count;
        } finally {
            is.close();
        }
    }
}
