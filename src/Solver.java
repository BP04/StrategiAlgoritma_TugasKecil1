import java.time.Instant;
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

        IntBooleanPair total = solve(0);
        
        Instant end = Instant.now();

        System.out.println();
        
        if(total.get_second()){
            board.print_board();
        }
        else{
            System.out.println("Tidak ada solusi yang ditemukan");
        }

        System.out.println();
        System.out.println("Waktu pencarian: " + Duration.between(start, end).toMillis() + " ms");
        System.out.println();
        System.out.println("Banyak kasus yang ditinjau: " + total.get_first());
        System.out.println();

        return total.get_second();
    }

    private IntBooleanPair solve(int index){
        if(index >= P){
            boolean possible = (!board.empty_spaces()) && (board.count_unique() == P);
            return new IntBooleanPair(1, possible);
        }

        IntBooleanPair total = new IntBooleanPair(0, false);

        ArrayList<char[][]> orientations = pieces.get(index).orientations;

        for(int r = 0; r < N; ++r){
            for(int c = 0; c < M; ++c){
                if(!board.is_empty(r, c)){
                    continue;
                }

                for(char[][] current : orientations){

                    int current_row = current.length;
                    int current_col = current[0].length;

                    if(can_put_piece(current, current_row, current_col, r, c)){

                        IntBooleanPair result = solve(index + 1);

                        total.set_first(total.get_first() + result.get_first());
                        total.set_second(total.get_second() || result.get_second());

                        if(total.get_second()){
                            return total;
                        }

                        remove_piece(current, current_row, current_col, r, c);
                    }
                }
            }
        }

        if(total.get_first() == 0){
            total.set_first(1);
        }

        return total;
    }

    private boolean can_put_piece(char[][] piece, int piece_row, int piece_col, int r, int c){
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

        for(int i = r; i < r + piece_row; ++i){
            for(int j = c; j < c + piece_col; ++j){
                if(piece[i - r][j - c] == ' '){
                    continue;
                }
                board.set(i, j, piece[i - r][j - c]);
            }
        }

        return true;
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
