package test;

import java.util.Random;

/**
 * Created by darkr_000 on 29.12.2016.
 */
public class Ship {
    boolean isVertical;
    int length;
    Cell leftUp;
    Cell rightDown;
    Ship[] ships = new Ship[10];
    Ship() {

    }
    static int count = -1;

    void arrayShips (Ship ship, Ship[] ships){
        ships[count] = ship;
    }

    public Ship(int length, Cell leftUp, boolean isVertical) {
        int s = 0;
        int a = 0;
        this.length = length;
        if (count > 0) {
            do {
                this.leftUp = leftUp;
                for (int i = 0; i < count; i++) {
                    if (ships[i].leftUp.x == leftUp.x && ships[i].leftUp.y == leftUp.y) {
                        s = 0;
                    } else s++;
                }
                if (s == 1 + count) {
                    a = 1;
                }
            }
            while (a == 0);
        }
        else this.leftUp = leftUp;

        this.isVertical = isVertical;
        rightDown = new Cell();
        if (isVertical == false) {
            rightDown.x = leftUp.x + length*2 - 2;
            rightDown.y = leftUp.y;
        } else {
            rightDown.x = leftUp.x;
            rightDown.y = leftUp.y + length - 1;
        }

    }


    boolean isIntersect(Ship ship, Ship[] ships) {
        //верхняя проверка
        int numbers = 0;
        for (int i = 0; i <= count-1 ; i++) {
//            if (ship.rightDown.y + 1 < ships[i].leftUp.y) {
//               return false;
//                numbers++;
//            }
            if ((ships[i].rightDown.x == ship.rightDown.x && ships[i].rightDown.y == ship.rightDown.y )|| (ships[i].leftUp.x == ship.leftUp.x && ships[i].leftUp.y == ship.leftUp.y)  ){
                return true;
            }
            if (ships[i].rightDown.y < ship.leftUp.y - 1) {
//                return false;
                numbers++;
                continue;
            }
//            if (ship.leftUp.y - 1 > ships[i].rightDown.y) {
//                return false;
//                numbers++;
//            }
            if (ships[i].leftUp.y > ship.rightDown.y + 1) {
//                return false;
                numbers++;
                continue;
            }
//            if (ship.rightDown.x + 2 < ships[i].leftUp.x ) {
//                return false;
//                numbers++;
//            }
            if (ships[i].rightDown.x < ship.leftUp.x - 4) {
//                return false;
                numbers++;
                continue;
            }
            //справа проверка
//            if (ship.leftUp.x - 2 > ships[i].rightDown.x) {
//                numbers++;
//                return false;
//            }
            if (ships[i].leftUp.x - 4 > ship.rightDown.x) {
                numbers++;
                continue;
            }
        }
//        if (count == 0) {
//            return false;
//        }
        if (count == numbers){
            return false;
        }
        //нижняя проверка
        //слева проверка
        return true;
    }

    void make(Grid grid, int length) {
        Random random = new Random();
        count++;
            Ship ship;
        do{
            do {
                ship = new Ship(length, new Cell((random.nextInt(20) + 4), (random.nextInt(9) + 1)), random.nextBoolean());
                arrayShips(ship,ships);
            }
            //невыходил за поле
            while ((ship.rightDown.x > 22 || ship.rightDown.y > 10) || ((ship.leftUp.x) % 2 != 0) || ship.leftUp.x < 4);
        }
        while(isIntersect(ship, ships) == true);

        for (int i = 0; i < ship.length; i++) {
            //устанавливаем
            if (ship.isVertical == true) {
                grid.cells[ship.leftUp.y + i][ship.leftUp.x] = 'X';
            } else {
                grid.cells[ship.leftUp.y][ship.leftUp.x + (i + 1) * 2] = 'X';
//                grid.cells[ship.leftUp.y][ship.leftUp.x] = ' ';
            }
        }
//        System.out.print(ship.leftUp.x);
//        System.out.print("");
//        System.out.println(ship.leftUp.y);
//        System.out.print(ship.rightDown.x);
//        System.out.println(ship.rightDown.y);
//        System.out.println("деление" + (ship.rightDown.x > 10) +" " + (ship.rightDown.y >10)+ " " + ship.leftUp.x%2);

    }
}


