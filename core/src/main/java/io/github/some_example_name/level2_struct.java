package io.github.some_example_name;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class level2_struct {
    level2_struct(World world, Stage stage){
        SmallPig pig1=new SmallPig(world,523,123,22,22);
        SmallPig pig2=new SmallPig(world,675,123,22,22);
        MediumPig pig3=new MediumPig(world,595,142,35,35);
        LargePig pig4 = new LargePig(world,510, 308, 42,42);
        LargePig pig5 = new LargePig(world,678, 300, 42,42);

        Glass2 g1 = new Glass2(world,517, 94, 28,28,(float)Math.toRadians(3));
        Glass2 g2 = new Glass2(world,682, 94, 28,28,(float)Math.toRadians(3));

        // first shape in lvl2
        Verticalwb verticalwb =new Verticalwb(world,475,150,50,150,(float)Math.toRadians(3));
        Verticalwb verticalwb2 =new Verticalwb(world,545,97,50,150,(float)Math.toRadians(3));
        Woodenblock2 wb2=new Woodenblock2(world,510,285,110,40);

        // second
        Verticalwb verticalwb3 =new Verticalwb(world,657,93,50,150,(float)Math.toRadians(3));
        Verticalwb verticalwb4 =new Verticalwb(world,727,93,50,150,(float)Math.toRadians(3));
        Woodenblock2 wb1=new Woodenblock2(world,688,285,110,40);

        Woodenblock3 wb3 = new Woodenblock3(world,589, 93, 50, 50);

        stage.addActor(pig1);
        stage.addActor(pig2);
        stage.addActor(pig3);



        stage.addActor(verticalwb);
        stage.addActor(verticalwb2);
        stage.addActor(verticalwb3);
        stage.addActor(verticalwb4);
        stage.addActor(wb2);
        stage.addActor(wb3);
        stage.addActor(wb1);
        stage.addActor(g1);
        stage.addActor(g2);

        stage.addActor(pig4);
        stage.addActor(pig5);


    }
}
