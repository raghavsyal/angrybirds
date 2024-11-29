package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;

public class level2 implements Screen {

    private Main game;
    OrthographicCamera camera;
    private Stage stage;
    private Music musicl;
    private Texture backgroundTexture;

    private int birds_destroyed =0;
    private int total_birds=5;

    private ImageButton back;
    private RedBird redBird;
    private BlackBird blackBird;
    private YellowBird yellowBird;
    private Slingshot slingshot;
    int score;
    BitmapFont font;
    private int currentBird = 0;

    private World world;
    //private Box2DDebugRenderer debugRenderer;
    private static final float pixels = 100f;
    private ContactListener contactListener;

    private boolean isDragging = false;
    private Vector2 dragStart = new Vector2();
    private Vector2 dragCurrent = new Vector2();
    private final Vector2 slingshotPos = new Vector2(120, 120);
    private final float MAX_DRAG_DISTANCE = 100f;

    private ArrayList<MediumPig> list_mediumpigs =new ArrayList<>();
    private ArrayList<SmallPig> list_smallpigs=new ArrayList<>();
    private ArrayList<LargePig> list_largepigs =new ArrayList<>();

    public level2(Main game) {
        world = new World(new Vector2(0, -9.8f), true);

        //debugRenderer = new Box2DDebugRenderer();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.game=game;

        font = new BitmapFont();

        musicl = Gdx.audio.newMusic(Gdx.files.internal("musicl.mp3"));
        musicl.setLooping(true);
        backgroundTexture=new Texture(Gdx.files.internal("background.png"));


        Texture backtexture = new Texture(Gdx.files.internal("back.png"));
        TextureRegionDrawable backdrawable = new TextureRegionDrawable(backtexture);

        back=new ImageButton(backdrawable);
        back.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                musicl.pause();
                game.backgroundmusic.play();
                game.setScreen(new LevelManagerScreen(game));
                return true;
            }
        });

        back.setSize(50,45);
        back.setPosition(15,400);
        stage.addActor(back);

        redBird= new RedBird(world, 162 , 170 ,45,45);
        stage.addActor(redBird);

        blackBird= new BlackBird(world, 40,123,45,45);
        stage.addActor(blackBird);

        yellowBird= new YellowBird(world, 85,120,41,41);
        stage.addActor(yellowBird);
        level2_struct l2_st = new level2_struct(world, stage);

        slingshot= new Slingshot(120,82,102,125);
        stage.addActor(slingshot);

        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Vector2 touchPos = new Vector2(x, y);
                Vector2 redbird_pos=new Vector2(redBird.getX()+redBird.getWidth()/2,redBird.getY()+redBird.getHeight()/2);
                Vector2 blackbird_pos=new Vector2(blackBird.getX()+blackBird.getWidth()/2,blackBird.getY()+blackBird.getHeight()/2);
                Vector2 yellowbird_pos=new Vector2(yellowBird.getX()+yellowBird.getWidth()/2,yellowBird.getY()+yellowBird.getHeight()/2);

                if(touchPos.dst(redbird_pos) < 50 && !redBird.getislaunched()){
                    isDragging = true;
                    dragStart.set(x, y);
                    dragCurrent.set(x, y);
                    return true;
                }
                else if(touchPos.dst(yellowbird_pos) < 50 && !yellowBird.getislaunched()){
                    isDragging = true;
                    dragStart.set(x, y);
                    dragCurrent.set(x, y);
                    return true;
                }
                else if(touchPos.dst(blackbird_pos) < 50 && !blackBird.getislaunched() ){
                    isDragging = true;
                    dragStart.set(x, y);
                    dragCurrent.set(x, y);
                    return true;
                }
                return false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (isDragging) {
                    dragCurrent.set(x, y);
                    if(currentBird==0){
                        redBird.setPosition(dragCurrent.x-redBird.getWidth()/2,dragCurrent.y-redBird.getHeight()/2);
                    }
                    else if (currentBird==1) {
                        yellowBird.setPosition(dragCurrent.x-yellowBird.getWidth()/2,dragCurrent.y-yellowBird.getHeight()/2);
                    }
                    else if (currentBird==2) {
                        blackBird.setPosition(dragCurrent.x-blackBird.getWidth()/2,dragCurrent.y-blackBird.getHeight()/2);
                    }
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (isDragging) {
                    isDragging = false;
                    Vector2 draggingVec = new Vector2(dragCurrent.x-slingshotPos.x,dragCurrent.y-slingshotPos.y);

                    float dragDistance = draggingVec.len();
                    float calculated_force = Math.min(dragDistance / MAX_DRAG_DISTANCE, 1.0f);
                    Vector2 launchVel = draggingVec.nor().scl(-1).scl(calculated_force);

                    if(currentBird==0) {
                        redBird.launch(launchVel.x, launchVel.y);
                        currentBird = 1;
                        yellowBird.body.setTransform(162 / pixels, 170 / pixels, 0);
                        yellowBird.body.setLinearVelocity(0, 0);
                    }
                    else if(currentBird==1) {
                        yellowBird.launch(launchVel.x, launchVel.y);
                        currentBird = 2;
                        blackBird.body.setTransform(162 / pixels, 170 / pixels, 0);
                        blackBird.body.setLinearVelocity(0, 0);
                    }
                    else if(currentBird==2){
                        blackBird.launch(launchVel.x, launchVel.y);
                        currentBird = 3;
                    }
                }
            }
        });

        setupCollisionDetection();
        // creating ground where birds are initially present
        createGround();
        //creating 3 walls right,left,up
        createBoundaries();

    }


    private void createGround() {

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 88/pixels));
        groundBodyDef.type= BodyDef.BodyType.StaticBody;

        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth / pixels, 10/pixels);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.95f;
        fixtureDef.restitution = 0.2f;

        Fixture fixture=groundBody.createFixture(fixtureDef);
        groundBox.dispose();
    }

    private void createBoundaries() {
//        createWall(0, 0, 800, 1);
        createWall(0, 460, 800, 1);
        createWall(0, 0, 1, 460);
        createWall(799, 0, 1, 460);
    }

    private void createWall(float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((x + width/2) / pixels, (y + height/2) / pixels);
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / (2 * pixels), height / (2 * pixels));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.5f;

        Fixture fixture=body.createFixture(fixtureDef);
        shape.dispose();

    }

    @Override
    public void show() {
        musicl.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1/60f, 6, 2);
        for (MediumPig pigs: list_mediumpigs) {
            if(pigs.getHitCount()==2) {
                pigs.remove();
                world.destroyBody(pigs.body);
                birds_destroyed++;
            }
            else if(pigs.getHitCount()<2){
                pigs.increasehitcount();
            }
        }
        list_mediumpigs.clear();

        for (LargePig pigs: list_largepigs) {
            if(pigs.getHitCount()==2) {
                pigs.remove();
                world.destroyBody(pigs.body);
                birds_destroyed++;
            }
            else if(pigs.getHitCount()<2){
                pigs.increasehitcount();
            }
        }
        list_largepigs.clear();

        for (SmallPig pigs: list_smallpigs) {
            pigs.remove();
            world.destroyBody(pigs.body);
            birds_destroyed++;
        }
        list_smallpigs.clear();



        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, 800, 460);
        if(birds_destroyed==total_birds){
            font.draw(game.batch, "Level Completed", 350, 400);
            game.setScreen(new level2complete(game));
        }
        font.draw(game.batch,"Score:",730,450);
        font.draw(game.batch,String.valueOf(score),780,450);
        game.batch.end();

        //debugRenderer.render(world, camera.combined.scl(pixels));

        stage.act(delta);
        stage.draw();
    }

    private void setupCollisionDetection() {
        contactListener = new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                if((fixtureA.getUserData() instanceof RedBird || fixtureA.getUserData() instanceof BlackBird || fixtureA.getUserData() instanceof YellowBird) && fixtureB.getUserData() instanceof MediumPig){
                    list_mediumpigs.add((MediumPig) fixtureB.getUserData());
                }
                else if(fixtureA.getUserData() instanceof MediumPig && (fixtureB.getUserData() instanceof RedBird || fixtureB.getUserData() instanceof BlackBird || fixtureB.getUserData() instanceof YellowBird)){
                    list_mediumpigs.add((MediumPig) fixtureB.getUserData());
                }

                else if((fixtureA.getUserData() instanceof RedBird || fixtureA.getUserData() instanceof BlackBird || fixtureA.getUserData() instanceof YellowBird) && fixtureB.getUserData() instanceof SmallPig){
                    list_smallpigs.add((SmallPig) fixtureB.getUserData());
                }
                else if(fixtureA.getUserData() instanceof SmallPig && (fixtureB.getUserData() instanceof RedBird || fixtureB.getUserData() instanceof BlackBird || fixtureB.getUserData() instanceof YellowBird)){
                    list_smallpigs.add((SmallPig) fixtureB.getUserData());
                }
                else if((fixtureA.getUserData() instanceof RedBird || fixtureA.getUserData() instanceof BlackBird || fixtureA.getUserData() instanceof YellowBird) && fixtureB.getUserData() instanceof LargePig){
                    list_largepigs.add((LargePig) fixtureB.getUserData());
                }
                else if(fixtureA.getUserData() instanceof LargePig && (fixtureB.getUserData() instanceof RedBird || fixtureB.getUserData() instanceof BlackBird || fixtureB.getUserData() instanceof YellowBird)){
                    list_largepigs.add((LargePig) fixtureB.getUserData());
                }


            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }
        };

        world.setContactListener(contactListener);
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
        musicl.stop();
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        musicl.dispose();
        stage.dispose();
        world.dispose();
        //debugRenderer.dispose();
        font.dispose();
    }


}
