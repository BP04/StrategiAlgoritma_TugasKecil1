public class State {
    public int placement_row, placement_column, piece_index, orientation_index, current_index;
    public boolean remove;

    public State(int placement_row, int placement_column, int piece_index, int orientation_index, int current_index, boolean remove){
        this.placement_row = placement_row;
        this.placement_column = placement_column;
        this.piece_index = piece_index;
        this.orientation_index = orientation_index;
        this.current_index = current_index;
        this.remove = remove;
    }
}
