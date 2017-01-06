package XOY.Model;

import java.io.IOException;

/**
 * Created by Артём on 05.01.2017.
 */
public interface FirstGame {
    char selectTeam(char team) throws IOException;
    int makeShoot();
}


