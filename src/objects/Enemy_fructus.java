package objects;

import engine.Engine;
import processing.core.PApplet;
import processing.core.PVector;

import static objects.Enemy.stateList.ATTACKPLAYER;
import static objects.Enemy.stateList.SEEKTOOTH;


/**
 * Created by ujansengupta on 3/31/17.
 */

public class Enemy_fructus extends Enemy{

    private static int life = 40;
    private static PVector color = new PVector(0,0,204);
    private static int size = 20;
    private static int PursueRadius  =100;
    private static float DEFAULT_FRUCTUS_SPEED = 1;
    private stateList state;

    public Enemy_fructus(PApplet app, float posX, float posY, float orientation){

        //The rational here is that each fructus enemy will have the same colour, size and life.
        //Since they are default values, they need not be constructor parameters.
        super (app, color, size, posX, posY, orientation, life,PursueRadius);
        this.setMaxVel(DEFAULT_FRUCTUS_SPEED);
        state = SEEKTOOTH;
    }


    private void setCurrentMode()
    {
        float playerdist, toothdist;
        playerdist = PVector.sub(this.position, Engine.player.position).mag();
        toothdist = PVector.sub(this.position, Engine.tooth.tooth.position).mag();
        if(playerdist<this.PURSUE_RADIUS && playerdist<toothdist){
            this.state = ATTACKPLAYER;
        }
        else{
            this.state = SEEKTOOTH;
        }

    }

    public void defaultBehaviour()
    {
        //for now, default behaviour is "SEEK TOOTH"

        setCurrentMode();

        switch(state)
        {
            case SEEKTOOTH:
                this.finalTarget = Engine.tooth.tooth;
                Seek(this.finalTarget.position);
                break;

            case ATTACKPLAYER:
                this.finalTarget = Engine.player;
                Seek(this.finalTarget.position);
                break;
        }

    }
}
