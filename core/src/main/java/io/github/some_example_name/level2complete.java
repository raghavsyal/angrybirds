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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class level2complete implements Screen {
    private Main game;
    private Stage stage;
    private ImageButton mainmenu, nextlevel;
    public ImageButton replay;
    private Texture backgroundTexture;
    private OrthographicCamera camera;

    public level2complete(Main game){
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        backgroundTexture=new Texture(Gdx.files.internal("levelcompleted_toedit.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        Texture mainmenutexture = new Texture(Gdx.files.internal("levelscreenmenu.png"));
        Texture nextleveltexture = new Texture(Gdx.files.internal("nextlevelicon.png"));
        Texture replaytexture = new Texture(Gdx.files.internal("replay.png"));

        TextureRegionDrawable mainmenudrawable = new TextureRegionDrawable(mainmenutexture);
        TextureRegionDrawable nextleveldrawable = new TextureRegionDrawable(nextleveltexture);
        TextureRegionDrawable replaydrawable = new TextureRegionDrawable(replaytexture);

        mainmenu = new ImageButton(mainmenudrawable);

        nextlevel = new ImageButton(nextleveldrawable);

         replay= new ImageButton(replaydrawable);


        mainmenu.setSize(137, 82.96f);
        mainmenu.setPosition(328,199);
        stage.addActor(mainmenu);


        nextlevel.setSize(120, 120);
        nextlevel.setPosition(228,180);
        stage.addActor(nextlevel);


        replay.setSize(150, 97.96f);
        replay.setPosition(428,192);
        stage.addActor(replay);



        mainmenu.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LevelManagerScreen(game));
                return true;
            }
        });
        nextlevel.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.backgroundmusic.pause();
                game.setScreen(new level3(game));
                return true;
            }
        });

        replay.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.backgroundmusic.pause();
                game.setScreen(new level2(game));
                return true;
            }
        });

    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen
        game.backgroundmusic.play();
    }

    @Override
    public void render(float delta) {
        // Clear the screen with a black color
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

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
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        backgroundTexture.dispose();
    }

}
