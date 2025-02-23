import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N, M, P;
    static String S;
    static char[][] board;
    static ArrayList<char[][]> pieces = new ArrayList<>();

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        parse(scanner);

        Solver solver = new Solver(N, M, P, board, pieces);
        boolean solution = solver.solve();
    
        if(!solution){
            return;
        }

        String answer;

        System.out.print("Apakah Anda ingin menyimpan solusi dalam bentuk text? (ya/tidak) ");
        answer = scanner.nextLine();

        if(answer.equals("ya")){
            System.out.print("Masukkan nama file: ");
            String file_name = scanner.nextLine();

            file_name = "../test/" + file_name + ".txt";

            try {
                solver.board.save_board_text(file_name);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Solusi berhasil disimpan dalam bentuk text.");
            System.out.println();
        }

        System.out.print("Apakah Anda ingin menyimpan solusi dalam bentuk image? (ya/tidak) ");
        answer = scanner.nextLine();

        if(answer.equals("ya")){
            System.out.print("Masukkan nama file: ");
            String file_name = scanner.nextLine();

            file_name = "../test/" + file_name + ".jpg";

            try {
                solver.board.save_board_image(file_name);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Solusi berhasil disimpan dalam bentuk image.");
            System.out.println();
        }

        System.out.println();

        scanner.close();
    }

    private static void parse(Scanner scanner){

        System.out.print("Masukkan nama file: ");
        String file_name = scanner.nextLine();

        file_name = "../test/" + file_name;

        try (BufferedReader br = new BufferedReader(new FileReader(file_name))){
            String[] firstLine = br.readLine().split(" ");
            N = Integer.parseInt(firstLine[0]);
            M = Integer.parseInt(firstLine[1]);
            P = Integer.parseInt(firstLine[2]);

            S = br.readLine();

            if(S.equals("DEFAULT")){
                board = new char[N][M];
                for(int i = 0; i < N; ++i){
                    for(int j = 0; j < M; ++j){
                        board[i][j] = ' ';
                    }
                }
            }
            else if(S.equals("CUSTOM")){
                board = new char[N][M];
                for(int i = 0; i < N; ++i){
                    String line = br.readLine();
                    for(int j = 0; j < M; ++j){
                        board[i][j] = line.charAt(j);
                        if(board[i][j] != '.'){
                            board[i][j] = ' ';
                        }
                    }
                }
            }

            List<String> piece = new ArrayList<>();
            char current = '\0';
            String line;

            while((line = br.readLine()) != null){
                if (line.isEmpty()) continue;
                
                char first = '\0';
                for(int i = 0; i < line.length(); i++){
                    if('A' <= line.charAt(i) && line.charAt(i) <= 'Z'){
                        first = line.charAt(i);
                        break;
                    }
                }
                
                if(first != current){
                    if(!piece.isEmpty()){
                        pieces.add(convert(piece));
                        piece.clear();
                    }
                    current = first;
                }
                
                piece.add(line);
            }

            if(!piece.isEmpty()){
                pieces.add(convert(piece));
            }

            // System.out.println("N: " + N + ", M: " + M + ", P: " + P);
            // System.out.println("S: " + S);
            
            // for(int i = 0; i < N; ++i){
            //     for(int j = 0; j < M; ++j){
            //         System.out.print(board[i][j]);
            //     }
            //     System.out.println();
            // }

            // System.out.println("Parsed Puzzle Shapes:");
            // for(int i = 0; i < pieces.size(); i++){
            //     System.out.println("Puzzle " + (i + 1) + ":");
            //     for(char[] row : pieces.get(i)){
            //         System.out.println(new String(row));
            //     }
            // }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char[][] convert(List<String> piece){
        int piece_row = piece.size();
        int piece_col = 0;
        for(int i = 0; i < piece_row; i++){
            piece_col = Math.max(piece_col, piece.get(i).length());
        }

        char[][] shape = new char[piece_row][piece_col];
        for(int i = 0; i < piece_row; ++i){
            String line = piece.get(i);
            for(int j = 0; j < piece_col; ++j){
                if(j >= piece.get(i).length()){
                    shape[i][j] = ' ';
                }
                else{
                    shape[i][j] = line.charAt(j);
                }
            }
        }
        return shape;
    }
}
