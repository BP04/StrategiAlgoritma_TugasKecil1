import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Board {
    private int rows;
    private int cols;
    private char[][] data;

    public Board(int rows, int cols, char[][] info){
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

    public void print_board(){
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                char ch = data[i][j];
                if(ch == ' ' || ch == '.'){
                    System.out.print(" ");
                    continue;
                }
                System.out.print(Constants.get_colored_text(ch));
            }
            System.out.println();
        }
    }

    public boolean is_empty(int row, int col){
        return data[row][col] == ' ';
    }

    public boolean empty_spaces(){
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                if(data[i][j] == ' '){
                    return true;
                }
            }
        }
        return false;
    }

    public int count_unique(){
        boolean[] exist = new boolean[26];

        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                if('A' <= data[i][j] && data[i][j] <= 'Z'){
                    exist[data[i][j] - 'A'] = true;
                }
            }
        }
        
        int counter = 0;
        for(int i = 0; i < 26; ++i){
            if(exist[i]){
                counter++;
            }
        }

        return counter;
    }

    public void save_board_text(String file_name) throws IOException {
        if (file_name == null || file_name.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name))) {
            for (int i = 0; i < rows; i++) {
                writer.write(data[i]);
                writer.newLine();
            }
        }
    }

    public void save_board_image(String file_name) throws IOException {
        if (file_name == null || file_name.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }

        int scale = 64;

        BufferedImage image = new BufferedImage(scale * rows, scale * cols, BufferedImage.TYPE_INT_RGB);
        
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                for(int k = 0; k < scale; ++k){
                    for(int l = 0; l < scale; ++l){
                        char ch = data[i][j];
                        image.setRGB(scale * i + k, scale * j + l, Constants.get_color(ch));
                    }
                }
            }
        }

        File output_file = new File(file_name);
        ImageIO.write(image, "jpg", output_file);
    }
}