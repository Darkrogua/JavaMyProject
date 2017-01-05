package test;

/**
 * Created by darkr_000 on 29.12.2016.
 */
public class Game {
    void start() {
        Grid grid = new Grid();
        grid.init();
        grid.show(grid.cells);

        Ship ship = new Ship();

        for (int i = 0; i < grid.angar.length; i++) {
           ship.make(grid, grid.angar[i]);
            grid.show(grid.cells);
        }

//        grid.show(grid.cells);
    }
}


