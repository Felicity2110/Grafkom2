import Engine.ShaderProgram;
import Engine.Window;
import Engine.objek;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengles.GLES20.glClearColor;

public class Main {

    private Window window = new Window(500,500,"Helo");
    ArrayList<objek> objects = new ArrayList<objek>();

    public void run(){
        init();
        loop();

        gifwTerminate();

        glfwSetErrorCallback(null).free();
    }

    private void gifwTerminate() {
    }

    public void init(){
        window.init();
        GL.createCapabilities();

        objects.add(new objek(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert",GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag",GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(0,.5f,0),
                                new Vector3f(-0.5f,-0.5f,0),
                                new Vector3f(0.5f,-0.5f,0)
                        )
                )
        ));
    }
    public void loop(){
        while(window.isOpen()){
            window.update();
            glClearColor(0.0f,0.0f,0.0f,0.0f);
            GL.createCapabilities();

            for(objek object:objects){
                object.draw();
            }
            //code

            glDisableVertexAttribArray(0);
            glfwPollEvents();
        }
    }
    public static void main(String[] args) {
        new Main().run();
    }
}