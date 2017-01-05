package test;

/**
 * Created by darkr_000 on 29.12.2016.
 */
public class Grid {
    char[][] cells;
    boolean isPlayer;
    int[] angar = {4,3,3,2,2,2,1,1,1,1};


    void init(){
        cells = new char[20][100];
        for (int i = 0; i < 11 ; i++) {
            for (int j = 0; j < 100 ; j++) {
                cells[i][j] = ' ';
            }
        }
        char c = 1040;
        for (int i = 1; i < 11 ; i++) {

            cells[0][(i+1)*2] = c;
            c++;
        }
        cells[0][22] = 'K';

        char as = 1040;
        for (int i = 39; i < 49; i++) {
            cells[0][(i+1)*2] = as;
            as++;
        }
        cells[0][98] = 'K';
        char a = 49;
        for (int i = 0; i < 10; i++) {

            cells[i+1][0] = a;
            cells[i][1] = ' ';
            cells[i][2] = ' ';
            a++;
        }

        cells[10][0] = '1';
        cells[10][1] = '0';
        cells[10][2] = ' ';

        char ad = 49;
        for (int i = 0; i < 10; i++) {

            cells[i+1][76] = ad;
            cells[i][77] = ' ';
            cells[i][78] = ' ';
            ad++;
        }
        cells[10][76] = '1';
        cells[10][77] = '0';
        cells[10][78] = ' ';
//
//        cells[10][80] = 'X';
//        char a = 1000;
//        for (int i = 0; i < 100 ; i++) {
//            System.out.print(a);
//            System.out.println(" "+a+i);
//            a++;
//        }
    }
    void show(char[][] cells){
        for (int i = 0; i < 11 ; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.print(cells[i][j]);
            }
            System.out.println();
        }
    }

//
}
