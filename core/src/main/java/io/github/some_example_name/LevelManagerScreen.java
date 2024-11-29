package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LevelManagerScreen implements Screen {
    Stage stage;
    private Texture backgroundTexture;
    private Main game;
    private Table table;
    private ImageButton l1,l2,l3,back;
    private OrthographicCamera camera;


    LevelManagerScreen(Main game){
        backgroundTexture=new Texture(Gdx.files.internal("select level background.png"));

        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);



        Texture l1Texture = new Texture(Gdx.files.internal("lvl1.png"));
        Texture l2Texture = new Texture(Gdx.files.internal("lvl2.png"));
        Texture l3Texture = new Texture(Gdx.files.internal("lvl3.png"));
        Texture backtexture = new Texture(Gdx.files.internal("back.png"));

        TextureRegionDrawable l1Drawable = new TextureRegionDrawable(l1Texture);
        TextureRegionDrawable l2Drawable = new TextureRegionDrawable(l2Texture);
        TextureRegionDrawable l3Drawable = new TextureRegionDrawable(l3Texture);
        TextureRegionDrawable backdrawable = new TextureRegionDrawable(backtexture);

        l1 = new ImageButton(l1Drawable);
        l2 = new ImageButton(l2Drawable);
        l3 = new ImageButton(l3Drawable);
        back=new ImageButton(backdrawable);




        l1.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.backgroundmusic.pause();
                game.setScreen(new level1(game));
                return true;
            }
        });

        l2.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.backgroundmusic.pause();
                game.setScreen(new level2(game));
                return true;
            }
        });


        l3.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.backgroundmusic.pause();
                game.setScreen(new level3(game));
                return true;
            }
        });


        back.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenu(game));
                return true;
            }
        });
        back.setSize(60,55);
        back.setPosition(20,375);
        stage.addActor(back);

        l1.setPosition(205,265);
        stage.addActor(l1);
        l2.setPosition(318,265);
        stage.addActor(l2);
        l3.setPosition(432,265);
        stage.addActor(l3);


    }

    @Override
    public void show() {
        game.backgroundmusic.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0,800, 460);
        game.batch.end();

        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        backgroundTexture.dispose();
    }
}
