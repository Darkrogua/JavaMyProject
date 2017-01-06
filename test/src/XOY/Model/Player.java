package XOY.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by darkr_000 on 24.12.2016.
 */
public class Player implements FirstGame {
    String playerName;
    int playerInt;
    int playerX;
    int playerY;
    char playerTeam;
    boolean playerIsWin = false;

    Player(){}

    //метод позволяет из введенных данных пользователем взять число по оси Х для хода
    int playerX(int a){
        String s = String.valueOf(a);
        char l = s.charAt(0);
        int x = Character.getNumericValue(l);
//        System.out.println("Вы ввели по оси Х число = "+x);
        return x;
    }
    //метод позволяет из введенных данных пользователем взять число по оси Y для хода
    int playerY(int a){
        String s = String.valueOf(a);
        char l = s.charAt(1);
        int y = Character.getNumericValue(l);
//        System.out.println("Вы ввели по оси Y число = "+y);
        return y;
    }
    //Предлагает игроку ввести двухзначное число, первое число означет по оси х второе по оси У
    int playerInt(int[]cell) throws IOException {
        System.out.println();
        System.out.println("Введите два числовых значения, 1 значение соответсвует оси Х, 2 значение оси Y" );
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
        int playerInt;
        int c = 0;
        playerInt = Integer.parseInt(reader.readLine());
        while ( c == 0 ){
            for (int i = 0; i < cell.length; i++) {
                if(cell[i] == playerInt){
                    c = 1;
                }
            }
            if( c == 0) {
                System.out.println("Error: Эта ячейка занята, введите другую.");
                playerInt = Integer.parseInt(reader.readLine());
            }
        }

        int b = 3;

        while (b==3)
        {
            if (playerInt == 11 || playerInt == 12 || playerInt == 13 || playerInt == 21 || playerInt == 22 || playerInt == 23 || playerInt == 31 || playerInt == 32 || playerInt == 33){
                System.out.println("Ваш выбор - "+playerInt);
                return playerInt;
            }
            System.out.println("Вы ввели число, которое не может быть задано в поле 3х3, попробуйте снова");
            playerInt = Integer.parseInt(reader.readLine());
        }
        return playerInt;

    }
    void init(Player a) throws IOException, InterruptedException {
        if(true) {
            String[]b = {"B","a","c"," ","п","р","и","в","e","т","с","т","в","у","е","т"," ","и","г","р","а"," ","Х","О","У"," ","и","л","и"," ","К","Р","Е","С","Т","И","К","И","-","Н","О","Л","И","К","И",","," ","п","о","ж","а","л","у","й","с","т","а"," ","п","р","е","д","с","т","а","в","ь","т","е","с","ь"};
            for (int i = 0; i < b.length; i++) {
                System.out.print(b[i]);
                Thread.sleep(100);
            }
            System.out.println();
        }
        a.playerName = a.enterName();
        a.playerTeam = a.selectTeam(playerTeam);
        System.out.println("Здравствуй "+a.playerName+" рад тебя видеть, а мы НАЧИНАЕМ!");
    }
//    void init(XOY.Model.Player a) throws IOException, InterruptedException {
//        if(true) {
//            Thread.sleep(50);
//            System.out.println("Вас приветствует игра ХОУ или КРЕСТИКИ-НОЛИКИ,пожалуйста представьтесь");
//        }
//        a.playerName = a.enterName();
//        a.playerTeam = a.playerSelectTeam();
//        Thread.sleep(50);
//        System.out.println("Здравствуй "+a.playerName+" рад тебя видеть, а мы НАЧИНАЕМ!");
//    }
//    char playerSelectTeam() throws IOException {
//
//    }
    static String enterName() throws IOException {
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
        String name = reader.readLine();
        return name;
    }

    @Override
    public char selectTeam(char team) throws IOException {
        System.out.println("Выберите сторону \"X\" или \"0\" введите необходимый символ");
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
        String b;
        char a;
        b = reader.readLine();
        a = b.charAt(0);
        int s = 6;
        while (s==6){
            if (a== 'x' || a== 'X' || a== 'х' || a== 'Х' || a=='0'){
                System.out.println("Хороший выбор! Вы выбрали сторону \""+a+"\"");
                return a;
            }
            b = reader.readLine();
            a = b.charAt(0);
            System.out.println("Такой команды нет, введите либо X либо 0 (ноль)");
        }
        return a;
    }

    @Override
    public int makeShoot() {
        return 0;
    }
}
