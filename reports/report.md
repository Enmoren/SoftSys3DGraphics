## 3D Graphics with OpenGL
#### Authors: Enmo Ren, Cassandra Overney, Hwei-Shin Harriman

### Project Overview and Goals
This project is an exploration into 3D Graphics using OpenGL. The goal was to create an interactive visualization of 3D objects rendered and shaded using OpenGL, which could then potentially be overlayed in real-world environments by detecting OpenCV’s augmented reality markers (ArUco markers) via a webcam or Android device. Accomplishing this task involved learning about topics such as:
* Perspective Rendering
* C++
* Loading `.obj` and `.mtl` files
* Rendering textures and shading
* CMake
* ArUco Markers for use with AR, using OpenCV

### Resources and Inspiration
We were inspired to take on this project through our shared interest in computer graphics and the OpenGL cookbook that we discovered online. This source’s earlier chapters (up through Chapter 6) were especially helpful in the early stages of our project.
To gain a basic understanding of computer graphics, we went through the cookbook chapters 1-7. The cookbook contains not only a step-by-step implementation of OpenGL examples but also instructions for environment settings needed by our implementation.

#### Resources for the Android OpenGL Visualization
We caution others from trying to follow the cookbook beyond Chapter 6, because the instructions for developing the Android AR application are now outdated, and do not work with the current Android SDK. Instead, we recommend downloading the current Android Studio and SDK from the [Android Developer](https://developer.android.com/studio) website and using this [Android OpenGL Tutorial](https://github.com/doggycoder/AndroidOpenGLDemo) (NOTE: The github is in Chinese). We were able to successfully interact with OpenGL renders through Android with this repository.

Below are some resources that we found useful throughout the course of our project:
* [OpenGL Cookbook](https://www.oreilly.com/library/view/opengl-data-visualization/9781782169727/): Used for rendering a Gaussian Distribution and volumetric dataset.
* [Android OpenGL Tutorial](https://github.com/doggycoder/AndroidOpenGLDemo): Used in our further exploration of implementing OpenGL in an Android application.
* [ArUco Marker Tutorial from OpenCV](https://docs.opencv.org/3.4.2/d5/dae/tutorial_aruco_detection.html)
* [Poly: Open-Source 3D Models](https://poly.google.com/): Contains many open source 3d models that we tried loading in.
* [Stanford 3D Scanning Repository](http://graphics.stanford.edu/data/3Dscanrep/): We found our volumetric dataset and `obj` files here.
* [Obj Loader Library](https://github.com/rlk/obj): Used for the improved texture and shading
* [Shader tutorial](http://www.opengl-tutorial.org/): Also used for the improved texture and shading
* A full list of the resources we used can be found [here](resources.md).

### Results
We successfully implemented an interactive visualization of object files using OpenGL by first plotting a simple interactive Gaussian distribution and then exploring different ways to make our visualization more complex. Currently, we are able to visualize most object files and their corresponding mtl files with more realistic shading.    
#### Interactive Rendering of a Gaussian Distribution
To begin, we created an interactive Gaussian distribution with perspective rendering using OpenGL as shown below. We based this model off of Chapter 3 in the [OpenGL Data Visualization Cookbook](https://www.oreilly.com/library/view/opengl-data-visualization/9781782169727/).

<p align="center"> <img src ="https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/tutorial3.gif"/> </p>

  
We created perspective rendering by setting up a virtual camera. This allowed us to render a scene that resembles how the human eye sees the world. We also took the output of a Gaussian distribution and generated a 3D dataset. To get a better visualization effect, we applied a heat map to each vertex within a shader file. We also incorporated user inputs, such as mouse and keyboard inputs, to enable more dynamic interactions that we might need when we integrate OpenGL with OpenCV. Following the tutorial, we wrote all of the callback functions interacting with mouse and keyboard inputs and linked those functions to the GLFW library event handlers.

The result is an interactive interface that allows the user to control the rendering object freely in space. Users can rotate the object at different angles by holding the mouse button and dragging the object in various directions. The gif above shows how we can rotate the virtual objects at any angle and can be extremely useful for visualizing complex datasets.

#### Rendering Volumetric Datasets in OpenGL
We also rendered a volumetric dataset in OpenGL (reference gif below). The dataset was an `.obj` file obtained from the [Stanford 3D Scanning Repository](http://graphics.stanford.edu/data/3Dscanrep/). An `.obj` file consists of a series of materials, vertices, and surfaces. Surfaces contain sets of polygons and lines that reference a material that should be used when rendering them. We chose a simplified `.obj` file of a dragon that did not include any specific materials. In order to get the dragon to load in an OpenGL window, we had to write the following components:
*  An `ObjLoader` that loads in an `.obj` file via recursion.
*  Some simple interactions that would rotate the model when the user presses certain keys.
*  Texture and shader programs that deal with how a model appears on the screen. The shader program loads in a vertex (`pointcloud.vert`) and fragment shader (`pointcloud.frag`).  
* A `main` program that initializes the OpenGL window object, calls the object loader, and responds to various keyboard commands.

<p align="center"> <img src ="https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/dragon.gif"/> </p>

  
#### Improving Texture and Shading
In order to incorporate materials from our `.obj` files, we needed to improve our `ObjLoader` program. We found a really helpful [obj loader library](https://github.com/rlk/obj), which we decided to incorporate into our project. From the Android tutorials, we learned that some obj files come with `.mtl` (material library) files. Material library files contain various material definitions (color, texture, and reflection) for a 3d object, which are applied to the surfaces and vertices of objects. Our original `ObjLoader` was not able to load in `.mtl` files, so we transitioned to a more versatile `.obj` loader library. The new library basically parses the `.obj` file line by line, categorizes each line by a tag (e.g. `f`, `v`, `use`), and calls the corresponding helper function to deal with the data. With this library, we were able to load in a treasure chest object, as shown below.     

<p align="center"> <img src ="https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/chest.gif"/> </p>

In an effort to make the objects look more realistic, we researched different shader files. [Basic shading](https://learnopengl.com/Getting-started/Shaders) helps us understand how to write shader files using the language, GLSL. The shader we used for the Gaussian Distribution and volumetric dataset was simply a heat map. In order to render graphics more realistically, we decided to incorporate some basic shading techniques from [another tutorial](http://www.opengl-tutorial.org/beginners-tutorials/tutorial-8-basic-shading/). These techniques include being brighter when the object is closer to a light source, being darker when the light is not directly pointing at the model, and incorporating ambient light on the model. Following this tutorial, we were able to render a basic realistic illumination for the object we loaded.
<p align="center"> <img src ="https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/shader.png"/> </p>

#### Further Exploration: ArUco Markers with OpenCV
We also learned about the ArUco library, which is provided as an extra module in OpenCV. ArUco markers such as the ones seen in the image below, are commonly used for image processing calibration, and are well-suited for augmented reality. This is because it is easy to calculate the distortion and perspective of these easy-to-detect patterns. Once the ArUco marker is detected by OpenCV, it is possible to render 3D models on top of the marker. This is a bare-bones implementation of augmented reality. 
<p align="center"> <img src ="https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/markers.jpg"/> </p>

Getting this code to work involves building OpenCV with extra modules using CMake, and proved to be tricky to set up correctly. Many sources we found online used outdated versions of OpenCV, or oversimplified the process of building OpenCV with CMake. We were able to get the OpenCV marker detection as seen on [this webpage](https://docs.opencv.org/3.4.2/d5/dae/tutorial_aruco_detection.html) to work by using the instructions we have compiled in [aruco-setup.md](https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/aruco-setup.md). 

Once compiled and running, the final output is ArUco marker detection through a laptop webcam, as shown in the `.gif` below. With quite a bit more research, the next step would be to link OpenGL with this code, and render our own object on top of the recognized marker. 
<p align="center"> <img src ="https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/marker.gif"/> </p>

  
#### Further Exploration: Interfacing OpenGL with Android
The following gif is an example of an Android and OpenGL [tutorial](https://github.com/doggycoder/AndroidOpenGLDemo) that we found online and were able to run successfully. When we tried to load other objects, we found that the `.mtl` loader requires texture maps in the form of images. This limitation motivated us to learn more about texture and shading techniques.

<p align="center"> <img src ="https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/android.gif"/> </p>

  
### Reflection
We were able to achieve all of our original learning goals, throughout the course of this project, which included improving our programming skills, learning some C++, and gaining exposure to computer graphics through the OpenGL library. In addition, we also got the chance to learn additional valuable skills along the way, through our further explorations with Android and OpenCV. These included working with CMake, working through dependency issues and deprecated code, debugging, and installing software with poor (sparse) documentation, and some practice with Java thrown in for good measure (thanks to Android).

In the end, we successfully implemented our MVP as well as our medium stretch goal. Our MVP was able to take in user input such as keyboard commands and mouse clicking, then create a 3D plot using OpenGL, and also succeeded in rendering a volumetric dataset in OpenGL. We were also able to successfully render `.mtl` files in OpenGL and interact with them in Android.
We were not able to accomplish our final stretch goals of overlaying 3D data on real-world objects nor applying the AR-based data visualization on mobile devices via an Android app. We attempted to accomplish these goals through multiple avenues, whether that be through an Android’s cell phone camera, USB webcam, or built-in laptop webcam, with or without AR marker detection. However, unforeseen (and various) difficulties with dependencies, differences in our individual Ubuntu partition setups, and outdated resources prevented us from breaking through. We were able to get each individual component (accessing both USB and built-in webcams, rendering shaded and textured `.mtl` files in Android, detecting ArUco markers through the built-in webcam) required for the final stretch goal working, but given the amount of information and resources that were available and accessible to us, we were unable to get all of the pieces to work together. Given the amount of time that we each put into achieving this goal, we do not believe that time or effort expended were the limiting factors. Comprehensive understanding of the Android NDK (used to allow programming in C/C++ as opposed to Java), OpenGL, OpenCV, and C++ documentation would be necessary for us to achieve our final stretch goals.

That being said, we are pleased with the result of our project, since we were able to achieve all of our learning goals and pass almost all of our project milestones.
