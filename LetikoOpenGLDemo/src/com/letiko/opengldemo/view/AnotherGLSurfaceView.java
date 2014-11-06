package com.letiko.opengldemo.view;

import com.letiko.opengldemo.controller.AnotherGL20Renderer;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class AnotherGLSurfaceView extends GLSurfaceView {

	public AnotherGLSurfaceView(Context context) {
		super(context);
										// Create an OpenGL ES 2.0 context
		setEGLContextClientVersion(2);
										// Render the view only when there is a change in the drawing data
										//setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		super.setEGLConfigChooser(8 , 8, 8, 8, 16, 0); //Just for Android Emulator compat
										// Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new AnotherGL20Renderer(context));
	}

	public AnotherGLSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
										// Create an OpenGL ES 2.0 context
		setEGLContextClientVersion(2);
										// Render the view only when there is a change in the drawing data
										// setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		super.setEGLConfigChooser(8 , 8, 8, 8, 16, 0); //Just for Android Emulator compat
										// Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new AnotherGL20Renderer(context));
	}

}