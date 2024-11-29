package io.github.some_example_name;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class level1_struct{

    level1_struct(World world,Stage stage){
        SmallPig pig1=new SmallPig(world,520,320,25,25);
        SmallPig pig2=new SmallPig(world,638,320,25,25);
        MediumPig pig3=new MediumPig(world,580,320,45,45);

        Woodenblock2 wb2=new Woodenblock2(world,580,245,75,40);
        Verticalwb verticalwb =new Verticalwb(world,500,97,50,150,(float)Math.toRadians(3));
        Verticalwb verticalwb2 =new Verticalwb(world,654,97,50,150,(float)Math.toRadians(3));
        Woodenblock1 wb1=new Woodenblock1(world,578,230,176,25,(float)Math.toRadians(0));


        stage.addActor(pig1);
        stage.addActor(pig2);
        stage.addActor(pig3);
        stage.addActor(wb1);

        stage.addActor(verticalwb);
        stage.addActor(verticalwb2);
        stage.addActor(wb2);


    }
}
