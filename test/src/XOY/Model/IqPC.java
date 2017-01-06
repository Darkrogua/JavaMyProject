package XOY.Model;

import java.io.IOException;

/**
 * Created by darkr_000 on 26.12.2016.
 */
public class IqPC implements FirstGame {
    boolean pcIsWin = false;
    char pcTeam;
    int[] verticalOne = {11, 12, 13};
    int ver1 = 0;
    boolean v1Win = false;
    int[] verticalTwo = {21, 22, 23};
    int ver2 = 0;
    boolean v2Win = false;
    int[] verticalThree = {31, 32, 33};
    int ver3 = 0;
    boolean v3Win = false;

    int[] horizonOne = {11, 21, 31};
    int h1 = 0;
    boolean h1Win = false;
    int[] horizonTwo = {12, 22, 32};
    int h2 = 0;
    boolean h2Win = false;
    int[] horizonThree = {13, 23, 33};
    int h3 = 0;
    boolean h3Win = false;

    int[] diagonalOne = {11, 22, 33};
    int dia1 = 0;
    boolean d1Win = false;
    int[] diagonalTwo = {13, 22, 31};
    int dia2 = 0;
    boolean d2Win = false;
    boolean[] Wins = {false,false,false,false,false,false,false,false};


    int nextOneWin(int[]freeCells, IqPC a, char player, char[][]cells){
        //проверка свободных ячеек
        a.ver1 = 0;
        a.ver2 = 0;
        a.ver3 = 0;
        a.h1 = 0;
        a.h2 = 0;
        a.h3 = 0;
        a.dia1 = 0;
        a.dia2 = 0;
        for (int i = 0; i < freeCells.length; i++){
            for (int j = 0; j < a.verticalOne.length ; j++) {
                if(freeCells[i] == a.verticalOne[j]){
                    a.ver1++;
                }
                if(freeCells[i] == a.verticalTwo[j]){
                    a.ver2++;
                }
                if(freeCells[i] == a.verticalThree[j]){
                    a.ver3++;
                }
                if(freeCells[i] == a.horizonOne[j]){
                    a.h1++;
                }
                if(freeCells[i] == a.horizonTwo[j]){
                    a.h2++;
                }
                if(freeCells[i] == a.horizonThree[j]){
                    a.h3++;
                }
                if(freeCells[i] == a.diagonalOne[j]){
                    a.dia1++;
                }
                if(freeCells[i] == a.diagonalTwo[j]){
                    a.dia2++;
                }
            }
        }
        //проверка можно ли выйграть за 1 ход т.е не более 1 ячейки должно быть свободно в линии
        if (a.ver1 >= 2 || a.ver1 == 0 ) {
            a.v1Win = false;
        }
        else {
            a.v1Win = true;
        }
            a.Wins[0] = a.v1Win;

        if (a.ver2 >= 2 || a.ver2 == 0 ) {
            a.v2Win = false;
        }
        else {
            a.v2Win = true;
        }
            a.Wins[1] = a.v2Win;

        if (a.ver3 >= 2 || a.ver3 == 0 ) {
            a.v3Win = false;
        }
        else {
            a.v3Win = true;
        }
            a.Wins[2] = a.v3Win;

        if (a.h1 >= 2 || a.h1 == 0 ) {
            a.h1Win = false;
        }
        else {
            a.h1Win = true;
        }
            a.Wins[3] = a.h1Win;

        if (a.h2 >= 2 || a.h2 == 0 ) {
            a.h2Win = false;
        }
        else {
            a.h2Win = true;
        }
            a.Wins[4] = a.h2Win;

        if (a.h3 >= 2 || a.h3 == 0 ) {
            a.h3Win = false;
        }
        else {
            a.h3Win = true;
        }
            a.Wins[5] = a.h3Win;

        if (a.dia1 >= 2 || a.dia1 == 0 ) {
            a.d1Win = false;
        }
        else {
            a.d1Win = true;
        }
            a.Wins[6] = a.d1Win;

        if (a.dia2 >= 2 || a.dia2 == 0 ) {
            a.d2Win = false;
        }
        else {
            a.d2Win = true;
        }
            a.Wins[7] = a.d2Win;
        //пробежаться по массиву и найти все true
        int position;
        for (int i = 0; i < a.Wins.length ; i++) {
            if(a.Wins[i] == true) {
                switch (i){
                    case 0:
                        int c = 0;
                        position = -1;
                        for (int j = 0; j < a.verticalOne.length ; j++) {
                            String s = String.valueOf(a.verticalOne[j]);
                            char l = s.charAt(0);
                            char ll = s.charAt(1);
                            int x = Character.getNumericValue(l);
                            int y = Character.getNumericValue(ll);
                            if(cells[y][x] == player){
                                c++;
                            }
                            else {
                                position = j;
                            }
                        }

                        if (c == 2){
                            return a.verticalOne[position];
                        }
                        break;
                    case 1:
                        c = 0;
                        position = -1;
                        for (int j = 0; j < a.verticalTwo.length ; j++) {
                            String s = String.valueOf(a.verticalTwo[j]);
                            char l = s.charAt(0);
                            char ll = s.charAt(1);
                            int x = Character.getNumericValue(l);
                            int y = Character.getNumericValue(ll);
                            if(cells[y][x] == player){
                                c++;
                            }
                            else {
                                position = j;
                            }
                        }

                        if (c == 2){
                            return a.verticalTwo[position];
                        }
                        break;
                    case 2:
                        c = 0;
                        position = -1;
                        for (int j = 0; j < a.verticalThree.length ; j++) {
                            String s = String.valueOf(a.verticalThree[j]);
                            char l = s.charAt(0);
                            char ll = s.charAt(1);
                            int x = Character.getNumericValue(l);
                            int y = Character.getNumericValue(ll);
                            if(cells[y][x] == player){
                                c++;
                            }
                            else {
                                position = j;
                            }
                        }

                        if (c == 2){
                            return a.verticalThree[position];
                        }
                        break;
                    case 3:
                        c = 0;
                        position = -1;
                        for (int j = 0; j < a.horizonOne.length ; j++) {
                            String s = String.valueOf(a.horizonOne[j]);
                            char l = s.charAt(0);
                            char ll = s.charAt(1);
                            int x = Character.getNumericValue(l);
                            int y = Character.getNumericValue(ll);
                            if(cells[y][x] == player){
                                c++;
                            }
                            else {
                                position = j;
                            }
                        }

                        if (c == 2){
                            return a.horizonOne[position];
                        }
                        break;
                    case 4:
                        c = 0;
                        position = -1;
                        for (int j = 0; j < a.horizonTwo.length ; j++) {
                            String s = String.valueOf(a.horizonTwo[j]);
                            char l = s.charAt(0);
                            char ll = s.charAt(1);
                            int x = Character.getNumericValue(l);
                            int y = Character.getNumericValue(ll);
                            if(cells[y][x] == player){
                                c++;
                            }
                            else {
                                position = j;
                            }
                        }

                        if (c == 2){
                            return a.horizonTwo[position];
                        }
                        break;
                    case 5:
                        c = 0;
                        position = -1;
                        for (int j = 0; j < a.horizonThree.length ; j++) {
                            String s = String.valueOf(a.horizonThree[j]);
                            char l = s.charAt(0);
                            char ll = s.charAt(1);
                            int x = Character.getNumericValue(l);
                            int y = Character.getNumericValue(ll);
                            if(cells[y][x] == player){
                                c++;
                            }
                            else {
                                position = j;
                            }
                        }

                        if (c == 2){
                            return a.horizonThree[position];
                        }
                        break;
                    case 6:
                        c = 0;
                        position = -1;
                        for (int j = 0; j < a.diagonalOne.length ; j++) {
                            String s = String.valueOf(a.diagonalOne[j]);
                            char l = s.charAt(0);
                            char ll = s.charAt(1);
                            int x = Character.getNumericValue(l);
                            int y = Character.getNumericValue(ll);
                            if(cells[y][x] == player){
                                c++;
                            }
                            else {
                                position = j;
                            }
                        }

                        if (c == 2){
                            return a.diagonalOne[position];
                        }
                        break;
                    case 7:
                        c = 0;
                        position = -1;
                        for (int j = 0; j < a.diagonalTwo.length ; j++) {
                            String s = String.valueOf(a.diagonalTwo[j]);
                            char l = s.charAt(0);
                            char ll = s.charAt(1);
                            int x = Character.getNumericValue(l);
                            int y = Character.getNumericValue(ll);
                            if(cells[y][x] == player){
                                c++;
                            }
                            else {
                                position = j;
                            }
                        }

                        if (c == 2){
                            return a.diagonalTwo[position];
                        }
                        break;
                }
            }

        }

        return 0;
    }
//    char teamPc(char team){
//        char pc;
//        if (team == '0'){
//            pc = 'X';
//        }
//        else {
//            pc = '0';
//        }
//        return pc;
//    }
    void init(IqPC a, Player b) throws IOException {
        a.pcTeam = a.selectTeam(b.playerTeam);

    }

    @Override
    public char selectTeam(char team) throws IOException {
        char pc;
        if (team == '0'){
            pc = 'X';
        }
        else {
            pc = '0';
        }
        return pc;
    }

    @Override
    public int makeShoot() {
        return 0;
    }
}
