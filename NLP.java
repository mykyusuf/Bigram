import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NLP {

    public wordMap w=new wordMap();
    public List<Integer> l=new ArrayList<>();


    public NLP(){
    }

    public void readDataset(String dir) throws FileNotFoundException {

        File folder = new File(dir+"/dataset/");
        File[] listOfFiles = folder.listFiles();
        fileMap f;

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {

                Scanner sc2 = new Scanner(new File(dir+"/dataset/"+listOfFiles[i].getName()));
                int k=0;
                while (sc2.hasNextLine()) {
                    Scanner s2 = new Scanner(sc2.nextLine());
                    while (s2.hasNext()) {
                        String s = s2.next();
                        String word = s.trim().replaceAll("\\p{Punct}", "");
                        f=new fileMap();
                        f.put(listOfFiles[i].getName(),k);
                        w.put(word,f);

                        k++;
                    }
                }

            }

        }

    }

    public List<String> bigrams(String word){

        List<String> str;

        str=w.bigramPrint(word);

        return str;
    }

    public  void printWordMap()
    {
        for(int i=0;i<w.size();i++) {
            System.out.println((w.it.next()));
        }
    }

    public float tfIDF(String word, String fileName) throws IOException {
        String current = new java.io.File( "." ).getCanonicalPath();
        Scanner sc2 = new Scanner(new File(current+"/dataset/"+fileName));
        float tot=0;
        float TF=0,IDF=0;
        float term=0;
        while (sc2.hasNextLine()) {
            Scanner s2 = new Scanner(sc2.nextLine());
            while (s2.hasNext()) {
                String s = s2.next();
                String w = s.trim().replaceAll("\\p{Punct}", "");
                if(w.equals(word)){
                    term++;
                }

                tot++;
            }
        }
        TF=term/tot;

        File folder = new File(current+"/dataset/");
        File[] listOfFiles = folder.listFiles();
        float tut=0;
        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                int var=0;

                Scanner sc3 = new Scanner(new File(current+"/dataset/"+listOfFiles[i].getName()));
                while (sc3.hasNextLine()) {
                    Scanner s3 = new Scanner(sc3.nextLine());

                    while (s3.hasNext()) {
                        String s = s3.next();
                        String w2 = s.trim().replaceAll("\\p{Punct}", "");
                        if(w2.equals(word)){
                            var=1;
                        }
                    }
                }
                if (var==1) {
                    tut++;
                }

            }

        }

        IDF=(float) Math.log((float)listOfFiles.length/tut);

        //System.out.println(tot+" "+term+" "+tut+" "+listOfFiles.length);

        return (TF*IDF);
    }

}
