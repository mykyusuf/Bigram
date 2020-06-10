import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        NLP n = new NLP();
        String current = new java.io.File( "." ).getCanonicalPath();
        n.readDataset(current);

        Scanner sc3 = new Scanner(new File(current+"/src/"+"input.txt"));
        String w2,w3,s;
        while (sc3.hasNextLine()) {
            Scanner s3 = new Scanner(sc3.nextLine());
            while (s3.hasNext()) {
                s = s3.next();
                w2 = s.trim().replaceAll("\\p{Punct}", "");
                if(w2.equals("bigram")){
                    s = s3.next();
                    w2 = s.trim().replaceAll("\\p{Punct}", "");
                    n.bigrams(w2);

                }
                else if(w2.equals("tfidf")){
                    s = s3.next();
                    w2 = s.trim().replaceAll("\\p{Punct}", "");
                    s = s3.next();
                    w3 = s.trim().replaceAll("\\p{Punct}", "");

                    System.out.println(n.tfIDF(w2,w3));
                }

            }
        }


    }

}
