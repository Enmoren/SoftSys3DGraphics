package com.example.openglround2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.Charset;
import java.util.*;

public class Dragon {
    private List<String> verticesList;
    private List<String> facesList;
    private FloatBuffer verticesBuffer;
    private ShortBuffer facesBuffer;
    private int program;

    public Dragon(Context context) throws IOException{
        verticesList =new ArrayList<>();
        facesList = new ArrayList<>();

        Scanner scanner;
        scanner = new Scanner(context.getAssets().open("dragon.obj"));

        //Loop through all its lines
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line.startsWith("v ")){
                //add vertext line to list of vertices
                verticesList.add(line);
            } else if (line.startsWith("f ")){
                //add face line to faces list
                facesList.add(line);
            }
        }

        //close the scanner
        scanner.close();

        //Create buffer for vertices. Allocate 4 bytes for each coord since coords are
        //floating point numbers
        ByteBuffer buffer1 = ByteBuffer.allocateDirect(verticesList.size() * 3 * 4);
        buffer1.order(ByteOrder.nativeOrder());
        verticesBuffer = buffer1.asFloatBuffer();

        //Create buffer for faces. Allocate 2 bytes per index since indices are unsigned
        //short literals
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(facesList.size() * 3 * 2);
        buffer2.order(ByteOrder.nativeOrder());
        facesBuffer = buffer2.asShortBuffer();

        //populate vertices buffer
        for (String vertex : verticesList){
            String coords[] = vertex.split(" ");
            float x = Float.parseFloat(coords[1]);
            float y = Float.parseFloat(coords[2]);
            float z = Float.parseFloat(coords[3]);
            verticesBuffer.put(x);
            verticesBuffer.put(y);
            verticesBuffer.put(z);
        }
        verticesBuffer.position(0);

        //populate faces buffer. indices start at 1, subtract 1 to start at 0
        for (String face: facesList){
            String vertexIndices[] = face.split(" ");
            short vertex1 = Short.parseShort(vertexIndices[1]);
            short vertex2 = Short.parseShort(vertexIndices[2]);
            short vertex3 = Short.parseShort(vertexIndices[3]);
            facesBuffer.put((short)(vertex1 - 1));
            facesBuffer.put((short)(vertex2 - 1));
            facesBuffer.put((short)(vertex3 - 1));
        }
        facesBuffer.position(0);

        //Convert vertex_shader.txt to a string
        InputStream vertextShaderStream = context.getResources().openRawResource(R.raw.vertex_shader);
        String vertexShaderCode = IOUtils.toString(vertextShaderStream, Charset.defaultCharset());
        try {
            vertextShaderStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Convert fragment_shader.txt to a string
        InputStream fragmentShaderStream = context.getResources().openRawResource(R.raw.fragment_shader);
        String fragmentShaderCode = IOUtils.toString(fragmentShaderStream, Charset.defaultCharset());
        try {
            fragmentShaderStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create shader objects for both the vertex shader and fragment shader
        int vertesShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        GLES20.glShaderSource(vertesShader, vertexShaderCode);

        int fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        GLES20.glShaderSource(fragmentShader, fragmentShaderCode);

        //Pass shader objects to glCompileShader to compile code they contain
        GLES20.glCompileShader(vertesShader);
        GLES20.glCompileShader(fragmentShader);

        //create new program, attach vertex and fragment shader objects to it
        program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertesShader);
        GLES20.glAttachShader(program, fragmentShader);

        //link program
        GLES20.glLinkProgram(program);
        GLES20.glUseProgram(program);
    }

    public void draw(){
        //send vertex position data to shader
        int position = GLES20.glGetAttribLocation(program, "position");
        GLES20.glEnableVertexAttribArray(position);
        //point position handle to veritces buffer, needs num coords/vertex, type of coords
        //byte offset for each vertex. Since each coord = float, byte offset is 3 * 4
        GLES20.glVertexAttribPointer(position, 3, GLES20.GL_FLOAT, false, 3 * 4, verticesBuffer);

        //create view projection matrices
        //view matrix = allows to specify locations of camera & pt its looking at
        //projection matrix, allows mapping square coord sys of OpenGL ES to rect screen of
        //Android device
        float[] projectionMatrix = new float[16];
        float[] viewMatrix = new float[16];
        float[] productMatrix = new float[16];

        //initialize projection matrix
        //expects locations of L, R, bottom, top, near and far clip panes. B/c canvas already
        //square, can use -1, 1 for LR, bottom/top. Can experiment with near/far.
        Matrix.frustumM(projectionMatrix, 0,
                        -1, 1,
                        -1, 1,
                        2, 9);

        //initialize view matrix. Free to experiment w different vals
        Matrix.setLookAtM(viewMatrix, 0,
                            0, 3, -4,
                            0, 0, 0,
                            0, 1, 0);

        //Calculate matrix prod
        Matrix.multiplyMM(productMatrix, 0, projectionMatrix, 0, viewMatrix, 0);

        //pass product matrix to vertex shader
        int matrix = GLES20.glGetUniformLocation(program, "matrix");
        GLES20.glUniformMatrix4fv(matrix, 1, false, productMatrix, 0);

        //Use faces buffer to create triangles. Expects total num of vertex indices, type of
        //each index, faces buffer
        GLES20.glDrawElements(GLES20.GL_TRIANGLES,
                facesList.size() * 3, GLES20.GL_UNSIGNED_SHORT, facesBuffer);

        //Disable the attribute handler to pass vertex data to vertex shader
        GLES20.glDisableVertexAttribArray(position);
    }
}