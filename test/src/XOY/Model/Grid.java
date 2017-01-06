package XOY.Model;

/**
 * Created by darkr_000 on 24.12.2016.
 */
public class Grid {

    char[][]grids;
    void create(char[][] gridss) {
        int y;
        int x;
//        заполняем все ячейки поля '*'
        for (y = 4; y >= 0; y--) {

            for (x = 0; x < 5; x++) {
                gridss[y][x] = '*';

            }
        }
        System.out.println();

//        отображем на поле 3 символа
        gridss[4][0] = 'Y';
        gridss[0][4] = 'X';
        gridss[0][0] = '/';

//        отображаем на поле числа 123, по оси Х и по У
        for (int a = 1; a < 4; a++){
            gridss[a][0] = Character.forDigit(a,10);
            gridss[0][a] = Character.forDigit(a,10);
        }
//        делаем пустые строки на оси Х и У
        for (y = 4; y > 3; y--) {
            for (x = 1; x < 5; x++){
                gridss[y][x] = ' ';
                gridss[x][y] = ' ';
                System.out.print(gridss[y][x] + " ");
            }
        }
    }
    void show(char[][] gridss) throws InterruptedException {
        int y;
        int x;
        for (y = 4; y >= 0; y--) {
            System.out.println();
            for (x = 0; x < 5; x++) {
                Thread.sleep(50);
                System.out.print(gridss[y][x] + " ");
            }
        }
    }
}
