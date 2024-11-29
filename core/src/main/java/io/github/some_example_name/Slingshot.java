package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Slingshot extends Actor{
    private Texture slingshot;
    private SpriteBatch batch;
    int x,y,width,height;

    public Slingshot(int x, int y, int width, int height){
        batch = new SpriteBatch();
        slingshot =new Texture("slingshotphoto1.png");
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(slingshot,x,y,width,height);
    }
}
