package de.verschwiegener.lwjgl3.opengl;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

public class GLContext {

    public static GLCapabilities getCapabilities(){
        return GL.getCapabilities();
    }
}
