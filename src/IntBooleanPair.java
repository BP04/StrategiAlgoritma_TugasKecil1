public class IntBooleanPair {
    private int first;
    private boolean second;

    public IntBooleanPair(int first, boolean second){
        this.first = first;
        this.second = second;
    }

    public void set_first(int first){
        this.first = first;
    }

    public void set_second(boolean second){
        this.second = second;
    }

    public int get_first(){
        return first;
    }

    public boolean get_second(){
        return second;
    }
}
