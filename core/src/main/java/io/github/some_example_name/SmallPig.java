package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SmallPig extends Actor {
    private Texture pigTexture;
    public Body body;
    private World world;
    private float width, height;
    private int hitCount = 0;

    public static final float pixels = 100f;



    public SmallPig(World world, float x, float y, float width, float height) {
        this.world = world;
        this.width = width;
        this.height = height;

        pigTexture = new Texture("pig.png");
        setWidth(width);
        setHeight(height);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / pixels, y / pixels);
        bodyDef.angularDamping = 0.5f;
        bodyDef.linearDamping = 0.5f;

        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(width / (1.8f*pixels));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.7f;
        fixtureDef.friction = 0.9f;
        fixtureDef.restitution = 0.3f;
        fixtureDef.filter.categoryBits = 0x0002;
        fixtureDef.filter.maskBits = 0x0001 | 0x0002 | 0x0003 | 0x0004;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        circle.dispose();

        body.setActive(true);
    }


    @Override
    public void act(float delta) {
        Vector2 position = body.getPosition();
        Vector2 speed=body.getLinearVelocity();

        setPosition(position.x * pixels - width / 2, position.y * pixels - height / 2);
        setRotation((float) Math.toDegrees(body.getAngle()));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(pigTexture, getX(), getY(), width, height);
    }

    public int getHitCount(){
        return hitCount;
    }

    public void increasehitcount(){
        hitCount+=1;
    }

    public void dispose() {
        pigTexture.dispose();
    }

}
