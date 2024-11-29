package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RedBird extends Actor {
    private final Texture redbird_texture;
    private Body body;
    private World world;
    private float width, height;
    private boolean isLaunched = false;
    public static final float pixels = 100f;


    public RedBird(World world, float x, float y, float width, float height) {
        this.world = world;
        this.width = width;
        this.height = height;

        redbird_texture = new Texture("red.png");

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / pixels, y / pixels);

        body = world.createBody(bodyDef);

        body.setActive(false);

        PolygonShape blockShape = new PolygonShape();
        blockShape.setAsBox(width /(2.5f*pixels), height / (2.3f*pixels));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = blockShape;
        fixtureDef.density = 0.9f;
        fixtureDef.friction = 0.6f;
        fixtureDef.restitution = 0.4f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        blockShape.dispose();
    }

    public boolean getislaunched(){
        return isLaunched;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
    }


    @Override
    public void act(float delta) {
        Vector2 position = body.getPosition();
        Vector2 velocity = body.getLinearVelocity();

        setPosition(position.x * pixels - width / 2, position.y * pixels - height / 2);
        setRotation((float) Math.toDegrees(body.getAngle()));
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(redbird_texture, getX(), getY(), width, height);
    }

    public void launch(float forceX, float forceY) {
        if (isLaunched) {
            return;
        }
        else if(!isLaunched){
            body.setActive(true);
            body.applyLinearImpulse(forceX, forceY, body.getPosition().x, body.getPosition().y, true);
            isLaunched = true;
        }
    }

    public void dispose() {
        redbird_texture.dispose();
    }
}
