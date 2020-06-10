import java.util.*;

public class fileMap implements Map {

    private int size;
    private ArrayList<container2> c2=new ArrayList<>();

    private static class container2{
        private String textFile;
        private List<Integer> place=new ArrayList<>();
    }


    public fileMap(){

        size=0;

    }

    public String getTxt(int i){
        return c2.get(i).textFile;
    }
    public List<Integer> getPlace(int i){
        return c2.get(i).place;
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
            if(c2.get(i).textFile.equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(int i=0;i<size;i++){
            if(value==c2.get(i).place){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        for(int i=0;i<size;i++){
            if(c2.get(i).textFile.equals(key)){
                return c2.get(i);
            }
        }
            return null;
    }

    @Override
    public Object put(Object key, Object value) {

        container2 tmp=new container2();

        for(int i=0;i<size;i++){
            if(c2.get(i).textFile.equals(key)){
                c2.get(i).place.add((Integer)value);
                return null;
            }
        }

        tmp.textFile=(String) key;
        tmp.place.add((Integer) value);
        c2.add(tmp);
        size++;
        return null;
    }

    @Override
    public Object remove(Object key) {
        container2 t=new container2();
        for(int i=0;i<size;i++){
            if(c2.get(i).textFile.equals(key)){
                t=c2.get(i);
                c2.remove(c2.get(i));
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
        c2.clear();
        size=0;
    }

    @Override
    public Set keySet() {
        Set<String> st = new HashSet<>();
        for(int i=0;i<size;i++) {
            st.add(c2.get(i).textFile);
        }
        return st;
    }

    @Override
    public Collection values() {
        Collection<List<Integer>> coll = new ArrayList<>();
        for(int i=0;i<size;i++) {
            coll.add(c2.get(i).place);
        }
        return coll;
    }

    @Override
    public Set<Entry> entrySet() {
        HashMap<String,List<Integer>> st = new HashMap<>();
        for(int i=0;i<size;i++) {
            st.put(c2.get(i).textFile,c2.get(i).place);
        }
        Set st2 = st.entrySet();

        return st2;
    }
}
