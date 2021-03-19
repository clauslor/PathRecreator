import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PathRecreatorTest {
    private static final String COMMA_DELIMITER = ",";
    public static void main(String[] args) {

        List<CC> list = new ArrayList<>();
        if (args.length == 0) {
            CC cc1 = new CC("A", "1", "B", "1");
            CC cc11 = new CC("B", "1", "A", "1");

            CC cc2 = new CC("A", "2", "B", "2");
            CC cc3 = new CC("B", "2", "C", "2");
            CC cc4 = new CC("B", "2", "C", "2");
            CC cc5 = new CC("C", "2", "D", "2");

            CC cc6 = new CC("D", "2", "D", "2");
            boolean equalHashcode = cc1.hashCode() == cc11.hashCode();
            if (equalHashcode)
                System.out.println(String.format("%s and %s have equal hashcodes", cc1, cc11));
            list = new ArrayList<>();
            list.add(cc1);
            list.add(cc2);
            list.add(cc3);
            list.add(cc4);
            list.add(cc5);
            list.add(cc6);
        } else {
            for (int i = 0; i < args.length; i++) {
                try (Scanner scanner = new Scanner(new File(args[i]));) {
                    int linenum = 1;
                    String line = "";
                    try {
                        while (scanner.hasNextLine()) {
                            line = scanner.nextLine();
                            list.add(getRecordFromLine(linenum++,line ));
                        }
                    }catch (Exception e) {
                        System.err.println(String.format("line %d not parseable '%s', error: '%s'", linenum, line, e.getLocalizedMessage()));
                    }
                } catch (Exception e) {
                    System.err.println("Error: usage: PathRecreatorTest seperator file1.csv file2.csv ...");
                    e.printStackTrace();
                }
                System.out.println(String.format("%s imported", args[i]));
            }
        }
        System.out.println("imported CCs: ");
        for(CC c: list) {
            System.out.println(c);
        }
        List<Path> paths = new ArrayList<>();
        for (CC c : list) {
            Path p = new Path(c);
            //System.out.println(p);
            boolean addedNewNode = false;
            do {
                for (int i = 1; i < list.size(); i++) {
                    CC a = list.get(i);
                    if (c == a) {
                        continue;
                    }
                    addedNewNode = addedNewNode | p.add(a);
                }
                // System.out.println("recurse");
            } while (addedNewNode);
            if (p.size() > 1) {
                System.out.println(String.format("new path %s", p));
            }
            paths.add(p);
        }

    }

    private static CC getRecordFromLine(int linenum, String line) throws Exception{
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
            return new CC("linenum", values.get(0), values.get(1), values.get(2), values.get(3));
        }
    }
}
