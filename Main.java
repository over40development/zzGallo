import javax.media.j3d.*;
import javax.swing.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.Text2D;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;
import java.awt.Font;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("3D UI Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a Java 3D canvas
        Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        // Create a virtual universe and add the canvas
        SimpleUniverse universe = new SimpleUniverse();
        universe.getViewingPlatform().setNominalViewingTransform();
        universe.addBranchGraph(createSceneGraph());

        // Add the canvas to the frame
        frame.getContentPane().add(canvas3D);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    private static BranchGroup createSceneGraph() {
        BranchGroup root = new BranchGroup();

        // Create a 2D text object
        Text2D text = new Text2D("Joey, you have a small pp", new Color3f(1.0f, 1.0f, 0.0f), "Helvetica", 18, Font.BOLD);
        TransformGroup transformGroup = new TransformGroup();
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformGroup.addChild(text);
        root.addChild(transformGroup);

        // Add some rotation animation to the text
        Transform3D rotation = new Transform3D();
        Alpha rotationAlpha = new Alpha(-1, 4000);
        RotationInterpolator rotator = new RotationInterpolator(rotationAlpha, transformGroup, rotation, 0.0f, (float) Math.PI * 2.0f);
        rotator.setSchedulingBounds(new BoundingSphere());
        root.addChild(rotator);

        return root;
    }
}
