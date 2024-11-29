package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Verticalwb extends Actor{
    private Texture wb2_texture;
    float width,height;
    public Body body;
    private World world;
    private static final float pixels = 100f;
    private float initialAngle = 0f;

    public Verticalwb(World world, float x, float y, float width, float height, float angle){
        this.world = world;
        this.width=width;
        this.height=height;
        this.initialAngle=angle;

        wb2_texture=new Texture("verticalwb.png");
        setWidth(width);
        setHeight(height);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / pixels, y / pixels);
        bodyDef.angle=angle;

        body = world.createBody(bodyDef);


        // Use a box shape for rectangular blocks
        PolygonShape blockShape = new PolygonShape();
        blockShape.setAsBox(width /2.8f / pixels, height / 2.2f / pixels);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = blockShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0.3f;
        fixtureDef.filter.categoryBits = 0x0004;
        fixtureDef.filter.maskBits = 0x0002 | 0x0003 |0x0004 | 0x0005 | 0x0006 | 0x0007 ;
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        blockShape.dispose();
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
        batch.draw(wb2_texture, getX(), getY(), width / 2, height / 2, width, height, 1f, 1f, (float) Math.toDegrees(body.getAngle()), 0, 0, wb2_texture.getWidth(), wb2_texture.getHeight(), false, false);
    }

    public void dispose() {
        wb2_texture.dispose();
    }

}
