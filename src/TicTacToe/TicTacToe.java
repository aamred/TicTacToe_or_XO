package TicTacToe;

import java.util.Scanner;

/**
 *  Created by Anton Muzhytskyi 23/06/2022.
 */

public class TicTacToe {

    public static final String empty = "   ",
            cross = " X ",
            zero = " O ";
    public static String activePlayer;
    public static final int colums = 3,
            lines =3;
    public static String[][] field = new String[lines][colums];
    public static int gameStatus;
    public static final int gameStatus_Going = 0,
            gameStatus_NoWiners =1,
            gameStatus_X_wins =3,
            gameStatus_O_wins =4;
    public static Scanner in = new Scanner(System.in);


    public static void main(String[] args) {


        StartGame();
        do {
            GetEnterFromPlayer();
            AnalizeField();
            PrintField();

            if(gameStatus == gameStatus_X_wins) {
                System.out.println(" 'X' WINS!!!");
            }else if(gameStatus == gameStatus_O_wins) {
                System.out.println(" 'O' WINS!!!");
            }else if(gameStatus == gameStatus_NoWiners) {
                System.out.println(" No Winers");
            }
            activePlayer= (activePlayer==cross?zero:cross);
        }
        while(gameStatus == gameStatus_Going);
    }





    public static void StartGame() {
        for(int line = 0; line<lines; line++) {
            for(int colum =0; colum<colums; colum++) {
                field[line][colum] =empty;
            }
        }
        activePlayer =cross;
        PrintField();
    }

    public static void GetEnterFromPlayer()
    {
        boolean isEnter =false;
        do {
            System.out.println("Dear Player "+activePlayer+" enter number of line , space and number of colum please");
            int enteredLine = in.nextInt()-1;
            int enteredColum = in.nextInt()-1;
            if(enteredLine>=0 && enteredLine <= lines && enteredColum>=0 && enteredColum <=colums && field[enteredLine][enteredColum]==empty) {
                field[enteredLine][enteredColum] = activePlayer;
                isEnter = true;
            } else {
                System.out.println("This enter was incorrect. Try one more time : ");
            }
        }
        while(!isEnter);
    }

    public static void AnalizeField() {
        String winner = FindTheWinner();
        if(winner == cross) {
            gameStatus =gameStatus_X_wins;
        } else
        if(winner == zero) {
            gameStatus =gameStatus_O_wins;
        } else
        if(IsAllFieldFull()) {
            gameStatus=gameStatus_NoWiners;
        }
        else {
            gameStatus = gameStatus_Going;
        }
    }

    public static boolean IsAllFieldFull() {
        for(int line = 0; line<lines; line++) {
            for (int colum = 0; colum < colums; colum++) {
                if(field[line][colum]==empty)
                    return false;
            }
        }
        return true;
    }

    public static String FindTheWinner() {
        int sameElements;

        // check lines
        for(int line =0; line < lines; line++) {
            sameElements =0;
            for(int colum = 0; colum<colums; colum++) {
                if(field[line][0]!=empty &&  field[line][0]==field[line][1] &&field[line][1]==field[line][2] ) {
                    sameElements++;
                }
            }
            if(sameElements ==3) {
                return field[line][0];
            }
        }

        //check colums
        for(int colum =0; colum < colums; colum++) {
            sameElements =0;
            for(int line = 0; line<lines; line++) {
                if(field[0][colum]!=empty &&  field[0][colum]==field[1][colum] &&field[1][colum]==field[2][colum] ) {
                    sameElements++;
                }
            }
            if(sameElements ==3) {
                return field[0][colum];
            }
        }

        //check diagonals
        if(field[0][0]!=empty && field[0][0] == field[1][1] && field[1][1]==field[2][2]) {
            return field[0][0];
        }
        if(field[0][2]!=empty && field[0][2] == field[2][2] && field[2][2]==field[2][0]) {
            return field[0][2];
        }
        return empty;
    }

    public static void PrintField() {
        for(int line =0; line<lines; line++)
        {
            for(int colum =0; colum <colums; colum++)
            {
                System.out.print(field[line][colum]);
                if(colum!= colums-1)
                    System.out.print("|");
            }
            System.out.println();
            if(line != lines-1)
                System.out.println("-----------");
        }
        System.out.println();
    }
}
