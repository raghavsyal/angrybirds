package io.github.some_example_name;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class level3_struct{

    level3_struct(World world,Stage stage){


        Verticalwb verticalwb =new Verticalwb(world,420,97,50,280,(float)Math.toRadians(0));
        Verticalwb verticalwb2 =new Verticalwb(world,680,97,50,280,(float)Math.toRadians(0));
        Woodenblock1 wb1=new Woodenblock1(world,560,360,290,25,(float)Math.toRadians(0));
        Glass2 g2 = new Glass2(world, 564, 380, 35,35,(float)Math.toRadians(0));
        SmallPig pig1=new SmallPig(world,563,410,28,28);
        LargePig pig2 = new LargePig(world,480, 380, 42,42);
        LargePig pig3 = new LargePig(world,650, 380, 42,42);


        VerticalGlass verticalg1 =new VerticalGlass(world,472,97,20,220,(float)Math.toRadians(1));
        VerticalGlass verticalg2 =new VerticalGlass(world,623,97,20,220,(float)Math.toRadians(1));
        Glass1 gl=new Glass1(world,547,295,180,15,(float)Math.toRadians(0));
        LargePig pig4 = new LargePig(world,560, 320, 32,32);


        Verticalwb innerverticalwb =new Verticalwb(world,505,97,30,150,(float)Math.toRadians(3));
        Verticalwb innerverticalwb2 =new Verticalwb(world,588,97,30,150,(float)Math.toRadians(3));
        Woodenblock1 innerwb1 =new Woodenblock1(world,540,240,95,15,(float)Math.toRadians(0));
        Glass2 g3 = new Glass2(world, 560, 100, 35,35,(float)Math.toRadians(0));
        SmallPig pig5=new SmallPig(world,543,260,28,28);


        stage.addActor(verticalwb);
        stage.addActor(verticalwb2);
        stage.addActor(wb1);
        stage.addActor(g2);
        stage.addActor(pig1);
        stage.addActor(pig3);
        stage.addActor(pig2);

        stage.addActor(verticalg1);
        stage.addActor(verticalg2);
        stage.addActor(gl);
        stage.addActor(pig4);
        stage.addActor(pig5);

        stage.addActor(innerverticalwb);
        stage.addActor(innerverticalwb2);
        stage.addActor(innerwb1);
        stage.addActor(g3);

    }
}
