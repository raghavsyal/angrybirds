//package io.github.some_example_name;
//
//import com.badlogic.gdx.math.Vector2;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//
//
//
//public class ProjectTest {
//    public Vector2 redbirdpos;
//    public Vector2 blackbirdpos;
//    public Vector2 yellowbirdpos;
//
//    @BeforeClass
//    public static void setup(){
//        Vector2 redbirdpos=new Vector2(0,100);
//        Vector2 blackbirdpos=new Vector2(100,100);
//        Vector2 yellowbirdpos=new Vector2(200,100);
//    }
//    @Test
//    public void nearbird1Test(){
//        Vector2 currentpos=new Vector2(0,100);
//        ProjectTestmethods p_temp=new ProjectTestmethods();
//        int bird=p_temp.nearbird(currentpos,redbirdpos,blackbirdpos,yellowbirdpos);
//        assertEquals(0,bird);
//    }
//    @Test
//    public void nearbird2Test(){
//        Vector2 currentpos=new Vector2(100,100);
//        ProjectTestmethods p_temp=new ProjectTestmethods();
//        int bird=p_temp.nearbird(currentpos,redbirdpos,blackbirdpos,yellowbirdpos);
//        assertEquals(1,bird);
//    }
//    @Test
//    public void nearbird3Test(){
//        Vector2 currentpos=new Vector2(240,100);
//        ProjectTestmethods p_temp=new ProjectTestmethods();
//        int bird=p_temp.nearbird(currentpos,redbirdpos,blackbirdpos,yellowbirdpos);
//        assertEquals(2,bird);
//    }
//
//
//}
