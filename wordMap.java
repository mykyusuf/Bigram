import java.util.*;

public class wordMap implements Map {

    private ArrayList<container> c;
    private int size;
    CustomIterator it=new CustomIterator();


    public wordMap(){
        size=0;
        c = new ArrayList<container>();
    }
    private static class container{
        String word;
        fileMap ref=new fileMap();
    }

    class CustomIterator implements Iterator {

        private int counter=0;

        public CustomIterator() {
        }

        // Checks if the next element exists
        public boolean hasNext() {
            if(counter<size)
                return true;
            return false;
        }

        // moves the cursor/iterator to next element
        public String next() {
            if(hasNext()){
                return c.get(counter++).word;
            }
            return null;
        }

        // Used to remove an element. Implement only if needed
        public void remove() {
            c.remove(c.get(counter));
            size--;
        }
    }

    public List<String> bigramPrint(String x){
        List<String> str= new ArrayList<>();
        System.out.print("[");
        for(int i=0;i<size;i++){
            if(c.get(i).word.equals(x)){
                for(int j=0;j<c.get(i).ref.size();j++) {
                    for(int m=0;m<c.get(i).ref.getPlace(j).size();m++) {
                        System.out.print(c.get(i).word +  " " );

                        for (int n=0;n<size;n++){
                            for (int p=0;p<c.get(n).ref.size();p++){
                                for(int y=0;y<c.get(n).ref.getPlace(p).size();y++){

                                    if((c.get(i).ref.getPlace(j).get(m)+1)==c.get(n).ref.getPlace(p).get(y) && c.get(i).ref.getTxt(j).equals(c.get(n).ref.getTxt(p)) ){
                                        System.out.print(c.get(n).word+", ");

                                        str.add(c.get(i).word +  " "+c.get(n).word);
                                    }

                                }
                            }
                        }

                    }
                }
            }

        }
        System.out.println("]");

        return str;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size==0)
            return true;

        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        for(int i=0;i<size;i++){
            if(c.get(i).word.equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(int i=0;i<size;i++){
            if(value==c.get(i).ref){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        for(int i=0;i<size;i++){
            if(c.get(i).word.equals(key)){
                return c.get(i).ref;

            }
        }
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        container tmp=new container();

        for(int i=0;i<size;i++){
            if(c.get(i).word.equals(key)){
                tmp.ref=(fileMap) value;
                c.get(i).ref.put(tmp.ref.getTxt(0),tmp.ref.getPlace(0).get(0));
                return null;
            }
        }

        tmp.word=(String)key;
        tmp.ref= (fileMap) value;
        c.add(tmp);
        size++;
        return null;
    }

    @Override
    public Object remove(Object key) {
        container t=new container();
        for(int i=0;i<size;i++){
            if(c.get(i).word.equals(key)){
                t=c.get(i);
                c.remove(c.get(i));
                size--;
            }
        }

        return t;
    }

    @Override
    public void putAll(Map m) {
        for(int i=0;i<m.keySet().size();i++){
            put(m.keySet().toArray()[i],m.get(m.keySet().toArray()[i]));
        }
    }

    @Override
    public void clear() {
        c.clear();
        size=0;
    }

    @Override
    public Set keySet() {
        Set<String> st = new HashSet<>();
        for(int i=0;i<size;i++) {
            st.add(c.get(i).word);
        }
        return st;
    }

    @Override
    public Collection values() {
        Collection<fileMap> coll = new ArrayList<>();
        for(int i=0;i<size;i++) {
            coll.add(c.get(i).ref);
        }
        return coll;
    }

    @Override
    public Set<Entry> entrySet() {
        HashMap<String,fileMap> st = new HashMap<>();
        for(int i=0;i<size;i++) {
            st.put(c.get(i).word,c.get(i).ref);
        }
        Set st2 = st.entrySet();

        return st2;
    }

}
