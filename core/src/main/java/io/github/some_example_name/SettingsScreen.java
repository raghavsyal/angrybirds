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


public class SettingsScreen extends MainMenu implements Screen {
    private Main game;
    private Texture backgroundTexture;
    private Stage stage;
    private ImageButton volumeup;
    private ImageButton volumedown;
    private ImageButton back;
    private ImageButton quit;
    private OrthographicCamera camera;

    public SettingsScreen(Main game) {
        super();
        this.game=game;
        backgroundTexture=new Texture(Gdx.files.internal("settingsbg.png"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        Texture backtexture = new Texture(Gdx.files.internal("back.png"));
        TextureRegionDrawable backdrawable = new TextureRegionDrawable(backtexture);
        back=new ImageButton(backdrawable);

        Texture volumeup_texture = new Texture(Gdx.files.internal("volume.png"));
        TextureRegionDrawable volumeup_drawable = new TextureRegionDrawable(volumeup_texture);
        volumeup=new ImageButton(volumeup_drawable);

        Texture volumedown_texture = new Texture(Gdx.files.internal("novolume.png"));
        TextureRegionDrawable volumedown_drawable = new TextureRegionDrawable(volumedown_texture);
        volumedown=new ImageButton(volumedown_drawable);


        Texture exitTexture = new Texture(Gdx.files.internal("quit.png"));
        TextureRegionDrawable exitDrawable = new TextureRegionDrawable(exitTexture);
        quit = new ImageButton(exitDrawable);


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        back.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenu(game));
                return true;
            }
        });

        quit.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });

        volumeup.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!game.backgroundmusic.isPlaying()){
                    game.backgroundmusic.play();
                }
                return true;
            }

        });

        volumedown.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (game.backgroundmusic.isPlaying()){
                    game.backgroundmusic.pause();
                }
                return true;
            }
        });


        back.setSize(50,45);
        back.setPosition(15,400);
        stage.addActor(back);

        quit.setSize(135, 83.96f);
        quit.setPosition(430,215);
        stage.addActor(quit);

        volumedown.setSize(280, 110.96f);
        volumedown.setPosition(170,200);
        stage.addActor(volumedown);

        volumeup.setSize(170, 250.96f);
        volumeup.setPosition(320,120);
        stage.addActor(volumeup);



    }

    @Override
    public void show() {

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
        backgroundTexture.dispose();

    }
}
