package XOY.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by darkr_000 on 29.12.2016.
 */
public class Game {
    public static int raund = -1;
    public static boolean isPlayer;

    boolean replay = true;

    void start() throws IOException, InterruptedException {
        while (replay == true) {
            Player player = new Player();
            IqPC anastas = new IqPC();
            Grid grid = new Grid();
            grid.grids = new char[5][5];
            grid.create(grid.grids); //создаем поле
            player.init(player);
            anastas.init(anastas, player);
            grid.show(grid.grids); //показываем всё что сделали
            int[] freeCells = {11, 21, 31, 12, 22, 32, 13, 23, 33};
            int[] busyTone = new int[8];
            firstRandomSteep(player, anastas, grid, freeCells, busyTone);
            grid.show(grid.grids);
            generalGameAlgoritm(player, anastas, grid, freeCells, busyTone);
            pcWins(player, anastas, grid, freeCells);
            playerWins(player, anastas, grid, freeCells);
            drawNoWins(player, anastas, grid, freeCells);
            replay = replayGame(isGameOver(grid.grids, player.playerTeam, anastas.pcTeam, freeCells));
        }
    }

    private void firstRandomSteep(Player player, IqPC anastas, Grid grid, int[] freeCells, int[] busyTone) throws IOException {
        if (randomRate() == true) {
            playerMakeMove(player, grid, freeCells, busyTone);
        } else {
            int xod = anastas.nextOneWin(freeCells, anastas, anastas.pcTeam, grid.grids);
            int pcWin = 0;
            int cells = 0;
            if (xod == 0) {
                pcWin = anastas.nextOneWin(freeCells, anastas, player.playerTeam, grid.grids);
                if (pcWin == 0) {
                    cells = randomMove(freeCells, grid.grids, anastas.pcTeam, player);

                    isPlayer = true;
                } else {
                    pcMakeMove(player, anastas, grid, pcWin);
                    pcIsWin(player, anastas, grid, freeCells);
                    cells = pcWin;
                    isPlayer = true;
                }
            } else {
                pcMakeMove(player, anastas, grid, xod);
                pcIsWin(player, anastas, grid, freeCells);
                cells = xod;
                isPlayer = true;
            }
            freeCells(freeCells, cells);
        }
    }

    private void generalGameAlgoritm(Player player, IqPC anastas, Grid grid, int[] freeCells, int[] busyTone) throws IOException, InterruptedException {
        while (isGameOver(grid.grids, player.playerTeam, anastas.pcTeam, freeCells) != true) {

            if (isPlayer == true) {
                playerMakeMove(player, grid, freeCells, busyTone);
                if (isGameOver(grid.grids, player.playerTeam, anastas.pcTeam, freeCells) == true && drawn(freeCells, isGameOver(grid.grids, player.playerTeam, anastas.pcTeam, freeCells)) == false) {
                    player.playerIsWin = true;
                }
                isPlayer = false;
            } else {
                int xod = anastas.nextOneWin(freeCells, anastas, anastas.pcTeam, grid.grids);
                int pcWin = 0;
                int cells = 0;
                if (xod == 0) {
                    pcWin = anastas.nextOneWin(freeCells, anastas, player.playerTeam, grid.grids);
                    if (pcWin == 0) {
                        cells = randomMove(freeCells, grid.grids, anastas.pcTeam, player);
                        isPlayer = true;
                    } else {
                        pcMakeMove(player, anastas, grid, pcWin);
                        pcIsWin(player, anastas, grid, freeCells);
                        cells = pcWin;
                        isPlayer = true;
                    }
                } else {
                    pcMakeMove(player, anastas, grid, xod);
                    pcIsWin(player, anastas, grid, freeCells);
                    cells = xod;
                    isPlayer = true;
                }
                freeCells(freeCells, cells);
                grid.show(grid.grids);


            }
        }
    }

    private void drawNoWins(Player player, IqPC anastas, Grid grid, int[] freeCells) throws InterruptedException {
        if (anastas.pcIsWin != true && player.playerIsWin != true) {
            drawn(freeCells, isGameOver(grid.grids, player.playerTeam, anastas.pcTeam, freeCells));
            grid.show(grid.grids);
            System.out.println("");
            System.out.println("Бой был напряженным, но я не могу определить победителя, может следующая игра покажет кто сильнее?");
        }
    }

    private void playerWins(Player player, IqPC anastas, Grid grid, int[] freeCells) throws InterruptedException {
        if (isGameOver(grid.grids, player.playerTeam, anastas.pcTeam, freeCells) == true && player.playerIsWin == true) {
            grid.show(grid.grids);
            System.out.println();
            System.out.println("Поздравляю " + player.playerName + " - ты победил эту бездушную машину..");
        }
    }

    private void pcWins(Player player, IqPC anastas, Grid grid, int[] freeCells) {
        if (isGameOver(grid.grids, player.playerTeam, anastas.pcTeam, freeCells) == true && anastas.pcIsWin == true) {
            System.out.println();
            System.out.println("Тебя одолел компьютер, не стоит растраиваться повезет в другой раз");
        }
    }

    private void pcIsWin(Player player, IqPC anastas, Grid grid, int[] freeCells) {
        if (isGameOver(grid.grids, player.playerTeam, anastas.pcTeam, freeCells)) {
            anastas.pcIsWin = true;
        }
    }

    private void pcMakeMove(Player player, IqPC anastas, Grid grid, int pcWin) {
        int x = player.playerX(pcWin);
        int y = player.playerY(pcWin);
        grid.grids[y][x] = anastas.pcTeam;
    }

    private void playerMakeMove(Player player, Grid grid, int[] freeCells, int[] busyTone) throws IOException {
        player.playerInt = player.playerInt(freeCells);
        player.playerX = player.playerX(player.playerInt);
        player.playerY = player.playerY(player.playerInt);
        makeGoPlayer(grid.grids, player.playerX, player.playerY, player.playerTeam);
        recordHistoruGame(busyTone, player.playerInt);
        for (int i = 0; i < busyTone.length; i++) {
            int i1 = busyTone[i];
        }
        freeCells(freeCells, player.playerInt);
    }

    boolean replayGame(boolean isGameOver) throws IOException {
        System.out.println("Тут все просто если хочешь уйти то пиши - 'END', а если хочешь еще - 'START'(регистр неважен)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        boolean replay = true;
        boolean r = true;
        String[] si = {"END", "end", "eND", "End", "EnD", "eNd"};
        String[] siOut = {"START", "start"};

        for (int i = 0; i < siOut.length; i++) {
            if (s.equals(siOut[i])) {
                r = false;
            }
        }
        for (int i = 0; i < si.length; i++) {
            if (s.equals(si[i])) {
                r = true;
            }
        }
        if (isGameOver == true && r == true) {
            replay = false;
            System.out.println(" ПРИХОДИТЕ ЕЩЕ.... НАДЕЮСЬ ВАМ ПОНРАВИЛОСЬ!");
            return replay;
        }
        return replay;
    }

    boolean drawn(int[] a, boolean c) {
        boolean bb = false;
        int ab = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                ab++;
            }
            if (ab == a.length) {
                bb = true;
            }
        }
        if (bb == true && c == true) {
            return bb = true;
        }
        return bb;
    }

    int randomMove(int[] freeCells, char[][] grids, char pcTeam, Player player) {
        int freeCell = 0;
        for (int i = 0; i < freeCells.length; i++) {

            if (freeCells[i] != 0) {
                int x = player.playerX(freeCells[i]);
                int y = player.playerY(freeCells[i]);
                grids[y][x] = pcTeam;
                freeCell = freeCells[i];
                break;
            }

        }
        return freeCell;
    }

    int[] freeCells(int[] a, int b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b) {
//                System.out.println("Из свободных ячеек удаляется "+b);
                a[i] = 0;
                return a;
            }
        }
        return a;
    }

    boolean isGameOver(char[][] grid, char player, char pc, int[] freeCells) {
        boolean gameOver = false;
        int cell = 0;
        //проверка ----- если так y1

        for (int i = 1; i < 4; i++) {
            if ((grid[i][1] == player && grid[i][2] == player && grid[i][3] == player) || (grid[i][1] == pc && grid[i][2] == pc && grid[i][3] == pc)) {
                gameOver = true;
                return gameOver;
            }
        }
        //проверка вертикальной если так y1
        for (int i = 1; i < 4; i++) {
            if ((grid[1][i] == player && grid[2][i] == player && grid[3][i] == player) || (grid[1][i] == pc && grid[2][i] == pc && grid[3][i] == pc)) {
                gameOver = true;
                return gameOver;
            }
        }
        //проверка по диагоналям c слева на право снизу вверх
        if ((grid[1][1] == player && grid[2][2] == player && grid[3][3] == player) || (grid[1][1] == pc && grid[2][2] == pc && grid[3][3] == pc)) {
            gameOver = true;
            return gameOver;
        }
        //проверка по диагоналям c слева на право сверху вниз
        if ((grid[3][1] == player && grid[2][2] == player && grid[1][3] == player) || (grid[3][1] == pc && grid[2][2] == pc && grid[1][3] == pc)) {
            gameOver = true;
            return gameOver;
        }
        for (int i = 0; i < freeCells.length; i++) {
            if (freeCells[i] == 0) {
                cell++;
            }
            if (cell == freeCells.length) {
                gameOver = true;
                return gameOver;
            }
        }
        return gameOver;
    }

    void makeGoPlayer(char[][] grids, int a, int b, char x) {
        grids[b][a] = x;
        raund++;
        isPlayer = false;
    }

    int[] recordHistoruGame(int[] as, int a) {
        as[raund] = a;
        return as;
    }

    boolean randomRate() {
        System.out.println(" ");
        System.out.println("Сейчас подбросим монетку и выберем, чей ход первый");
        Random random = new Random();
        int g = random.nextInt(2);
        boolean ratePlayer;
        if (g == 0) {
            ratePlayer = false;
            System.out.println("Первым ходит противник");
        } else {
            ratePlayer = true;

            System.out.println("Поздравляю! вы ходите первым");
        }
        return ratePlayer;
    }
}
