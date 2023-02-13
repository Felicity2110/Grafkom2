package Engine;

import org.joml.Vector3f;

import java.util.List;
import java.util.Vector;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL20.glGenBuffers;

public class objek extends ShaderProgram{

    List<Vector3f> vertices;
    int vao;
    int vbo;

    public  objek(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices) {
        super(shaderModuleDataList);
        this.vertices=vertices;
         setupVAOVBO();


    }

    private void setupVAOVBO() {
        vao = glGenBuffers();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,vbo);

        glBufferData(GL_ARRAY_BUFFER,Utils.listoFloat(vertices),GL_STATIC_DRAW);

    }

    public void draw(){
        drawSetip();
        glLineWidth(10);
        glPointSize(10);
        glDrawArrays(GL_TRIANGLES,0,vertices.size());
    }

    public void drawSetip(){
        bind();
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER,vbo);
        glVertexAttribPointer(0,3,GL_FLOAT,false,0,0);
    }
}
