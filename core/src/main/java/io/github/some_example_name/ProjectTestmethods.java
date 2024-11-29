package io.github.some_example_name;

import com.badlogic.gdx.math.Vector2;

public class ProjectTestmethods {

    public int nearbird(Vector2 currentpos, Vector2 redbirdpos, Vector2 yellowbirdpos, Vector2 blackbirdpos){
        if(currentpos.dst(redbirdpos) < 50){
            return 0;
        }
        else if(currentpos.dst(yellowbirdpos) < 50){
            return 1;
        }

        else if(currentpos.dst(blackbirdpos) < 50){
            return 2;
        }

        else{
            return -1;
        }
    }
}
