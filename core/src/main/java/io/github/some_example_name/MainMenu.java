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



public class MainMenu implements Screen {
    private Main game;
    private Stage stage;
    private ImageButton playButton, settingsButton;
    public ImageButton exitButton;
    private Texture backgroundTexture;
    private Table table1,table2,table3;
    private OrthographicCamera camera;




    public MainMenu(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        backgroundTexture=new Texture(Gdx.files.internal("homepage.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        Texture playTexture = new Texture(Gdx.files.internal("play.png"));
        Texture settingsTexture = new Texture(Gdx.files.internal("settings.png"));
        Texture exitTexture = new Texture(Gdx.files.internal("quit.png"));

        TextureRegionDrawable playDrawable = new TextureRegionDrawable(playTexture);
        TextureRegionDrawable settingsDrawable = new TextureRegionDrawable(settingsTexture);
        TextureRegionDrawable exitDrawable = new TextureRegionDrawable(exitTexture);

        playButton = new ImageButton(playDrawable);

        settingsButton = new ImageButton(settingsDrawable);

        exitButton = new ImageButton(exitDrawable);
//        exitButton.setSize(10,104);


        playButton.setSize(137, 82.96f);

        playButton.setPosition(318,125);
        stage.addActor(playButton);


        settingsButton.setSize(120, 120);

        settingsButton.setPosition(668,10);
        stage.addActor(settingsButton);

        exitButton.setSize(150, 97.96f);

        exitButton.setPosition(6,27);
        stage.addActor(exitButton);






        playButton.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LevelManagerScreen(game));
                return true;
            }
        });

        settingsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //game.backgroundmusic.pause();
                game.setScreen(new SettingsScreen(game));
                return true;
            }
        });

        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });

    }

    public MainMenu(){}

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
