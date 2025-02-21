import java.util.ArrayList;

public class Piece2D {
    private int rows;
    private int cols;
    private char[][] data;
    public ArrayList<char[][]> orientations = new ArrayList<char[][]>();

    public Piece2D(int rows, int cols, char[][] info){
        this.rows = rows;
        this.cols = cols;
        data = new char[rows][cols];

        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                data[i][j] = info[i][j];
            }
        }
    }

    public void set(int row, int col, char value){
        data[row][col] = value;
    }

    public char get(int row, int col){
        return data[row][col];
    }

    public int get_rows(){
        return rows;
    }

    public int get_cols(){
        return cols;
    }

    public void generate_orientations(){
        char[][] temp = new char[rows][cols];
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                temp[i][j] = data[i][j];
            }
        }

        for(int i = 0; i < 4; ++i){
            if(unique(temp)){
                orientations.add(temp);
            }
            temp = rotate(temp);
        }

        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                temp[i][j] = data[i][j];
            }
        }
        temp = flip(temp);
        
        for(int i = 0; i < 4; ++i){
            if(unique(temp)){
                orientations.add(temp);
            }
            temp = rotate(temp);
        }

        // for(char[][] orientation : orientations){
        //     print_piece(orientation);
        //     System.out.println();
        // }
    }

    public void print_piece(char[][] piece){
        int piece_rows = piece.length;
        int piece_cols = piece[0].length;
        for(int i = 0; i < piece_rows; ++i){
            for(int j = 0; j < piece_cols; ++j){
                char ch = piece[i][j];
                if(ch == ' '){
                    System.out.print(" ");
                    continue;
                }
                System.out.print(Constants.get_color(ch));
            }
            System.out.println();
        }
    }

    private char[][] rotate(char[][] piece){
        int temp_rows = piece.length;
        int temp_cols = piece[0].length;
        char[][] rotated = new char[temp_cols][temp_rows];
        for(int i = 0; i < temp_rows; ++i){
            for(int j = 0; j < temp_cols; ++j){
                rotated[j][temp_rows - i - 1] = piece[i][j];
            }
        }
        return rotated;
    }

    private char[][] flip(char[][] piece){
        char[][] flipped = new char[rows][cols];
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                flipped[i][j] = piece[i][cols - j - 1];
            }
        }
        return flipped;
    }

    private boolean unique(char[][] piece){
        int piece_rows = piece.length;
        int piece_cols = piece[0].length;

        for(char[][] orientation : orientations){
            if(piece_rows != orientation.length || piece_cols != orientation[0].length){
                continue;
            }
            boolean equal = true;
            for(int i = 0; i < piece_rows; ++i){
                for(int j = 0; j < piece_cols; ++j){
                    if(piece[i][j] != orientation[i][j]){
                        equal = false;
                        break;
                    }
                }
                if(!equal){
                    break;
                }
            }
            if(equal){
                return false;
            }
        }
        return true;
    }
}
