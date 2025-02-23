import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.time.Duration;

public class Solver {
    ArrayList<Piece2D> pieces = new ArrayList<>();
    Board board;
    int N, M, P;

    public Solver(int N, int M, int P, char[][] board_info, ArrayList<char[][]> pieces_info){
        this.N = N;
        this.M = M;
        this.P = P;
        board = new Board(N, M, board_info);

        for(char[][] piece_info : pieces_info){
            Piece2D piece = new Piece2D(piece_info.length, piece_info[0].length, piece_info);
            piece.generate_orientations();
            pieces.add(piece);
        }
    }

    public boolean solve(){

        Instant start = Instant.now();

        // IntBooleanPair total = solve_recursive(0);
        IntBooleanPair total = solve_iterative();
        
        Instant end = Instant.now();

        System.out.println();
        
        if(total.second){
            board.print_board();
        }
        else{
            System.out.println("Tidak ada solusi yang ditemukan");
        }

        System.out.println();
        System.out.println("Waktu pencarian: " + Duration.between(start, end).toMillis() + " ms");
        System.out.println();
        System.out.println("Banyak kasus yang ditinjau: " + total.first);
        System.out.println();

        return total.second;
    }

    private IntBooleanPair solve_recursive(int index){
        if(index >= P){
            boolean possible = (!board.empty_spaces()) && (board.count_unique() == P);
            return new IntBooleanPair(1, possible);
        }

        IntBooleanPair total = new IntBooleanPair(0, false);

        ArrayList<char[][]> orientations = pieces.get(index).orientations;

        for(int r = 0; r < N; ++r){
            for(int c = 0; c < M; ++c){
                for(char[][] current : orientations){

                    int current_row = current.length;
                    int current_col = current[0].length;

                    if(can_put_piece(current, current_row, current_col, r, c)){
                        put_piece(current, current_row, current_col, r, c);

                        IntBooleanPair result = solve_recursive(index + 1);

                        total.first = total.first + result.first;
                        total.second = total.second || result.second;

                        if(total.second){
                            return total;
                        }

                        remove_piece(current, current_row, current_col, r, c);
                    }
                }
            }
            
        }

        if(total.first == 0){
            total.first = 1;
        }

        return total;
    }

    private IntBooleanPair solve_iterative(){
        IntBooleanPair total = new IntBooleanPair(0, false);

        ArrayDeque<State> stack = new ArrayDeque<>();
        
        State start = new State(0, 0, 0, 0, 0, false);
        stack.push(start);

        while(!stack.isEmpty()){
            State now = stack.pop();

            if(!now.remove){
                if(now.piece_index != now.current_index){
                    char[][] to_place = pieces.get(now.piece_index).orientations.get(now.orientation_index);

                    int to_place_row = to_place.length;
                    int to_place_col = to_place[0].length;

                    put_piece(to_place, to_place_row, to_place_col, now.placement_row, now.placement_column);
                }

                if(now.current_index >= P){
                    boolean possible = (!board.empty_spaces()) && (board.count_unique() == P);
                    if(possible){
                        total.first++;
                        total.second = true;
                        return total;
                    }
                    continue;
                }

                ArrayList<char[][]> orientations = pieces.get(now.current_index).orientations;

                boolean leaf = true;
                for(int r = 0; r < N; ++r){
                    for(int c = 0; c < M; ++c){

                        int iter = -1;
                        for(char[][] current : orientations){

                            iter++;
                            int current_row = current.length;
                            int current_col = current[0].length;

                            if(can_put_piece(current, current_row, current_col, r, c)){
                                leaf = false;
                                
                                State rem = new State(r, c, now.current_index, iter, now.current_index, true);
                                stack.push(rem);

                                State next = new State(r, c, now.current_index, iter, now.current_index + 1, false);
                                stack.push(next);
                            }
                        }
                    }
                }

                if(leaf){
                    total.first++;
                }
            }
            else{
                char[][] to_remove = pieces.get(now.piece_index).orientations.get(now.orientation_index);

                int to_remove_row = to_remove.length;
                int to_remove_col = to_remove[0].length;

                remove_piece(to_remove, to_remove_row, to_remove_col, now.placement_row, now.placement_column);
            }
        }

        return total;
    }

    private boolean can_put_piece(char[][] piece, int piece_row, int piece_col, int r, int c){
        if(!board.is_empty(r, c) && piece[0][0] != ' '){
            return false;
        }
        
        if(r + piece_row - 1 >= N || c + piece_col - 1 >= M){
            return false;
        }

        for(int i = r; i < r + piece_row; ++i){
            for(int j = c; j < c + piece_col; ++j){
                if(piece[i - r][j - c] == ' '){
                    continue;
                }
                if(!board.is_empty(i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    private void put_piece(char[][] piece, int piece_row, int piece_col, int r, int c){
        for(int i = r; i < r + piece_row; ++i){
            for(int j = c; j < c + piece_col; ++j){
                if(piece[i - r][j - c] == ' '){
                    continue;
                }
                board.set(i, j, piece[i - r][j - c]);
            }
        }
    }

    private void remove_piece(char[][] piece, int piece_row, int piece_col, int r, int c){
        for(int i = r; i < r + piece_row; ++i){
            for(int j = c; j < c + piece_col; ++j){
                if(piece[i - r][j - c] == ' '){
                    continue;
                }
                board.set(i, j, ' ');
            }
        }
    }
}
