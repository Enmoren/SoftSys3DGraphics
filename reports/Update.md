## 3DGraphics Project Update
#### Authors: Enmo Ren, Cassandra Overney, Hwei-Shin Harriman

### Project Vision
For this project, we will be implementing data visualization using OpenGL. Our MVP is to create an interactive 3D plot with perspective rendering using OpenGL. We are thinking of visualizing a simple Gaussian distribution. Our medium range goal is to render a volumetric dataset in OpenGL. Our stretch goal is to implement AR-based data visualization by overlaying 3D data on real-world objects and scenes. Another stretch goal is to potentially apply an AR-based data visualization on mobile devices via an Android app.
#### Our collective learning goals include:
- Improve our C programming skills
- Learn some C++
- Gain exposure in computer graphics, especially in terms of using the OpenGL library
#### Our individual learning goals include:
- **Cassandra**: I want to learn about how linear algebra connects to graphics with all of the coordinate transformations. I have never done any graphics projects, and it would be nice to gain exposure into that field via OpenGL. I am also really interested in the 3D interactive components of our project. I enjoyed creating 2D interactive data visualizations for Software Design and want to expand my interactive data visualization skills to the next level. Connecting OpenGL 3D visualizations with a mobile app would be a dream come true!
- **Enmo**: I would like to learn some fundamental computer graphics concepts by utilizing OpenGL. And I am also interested in creating 3D visualizations of datasets and potentially incorporating interactive components such as keyboard input and motion sensor input. Meanwhile, I would like to gain experience writing code in C++.
- **Hwei-Shin**: Since I have been involved with SIGGRAPH for a few years, I am very excited to be learning a bit about computer graphics. I am also really interested in using OpenGL to create 3D visualizations of a dataset, since this is the perfect intersection between linear algebra, data visualization, and computer science. I’m also excited to gain some more experience with OpenCV, since our final SoftDes project involved some of its functionality. Overall, I am very enthusiastic about all aspects of this project and can’t wait to see how much we can learn over the next 5 weeks.

### Current Progress
We made quite some progress on our project. We accomplished our MVP and medium range goal, combined OpenGL with Android Studio, started exploring how OpenCV can be used in AR, loaded more complicated objects with `mtl` files, and improved the shading of 3d models.     

#### MVP
For our MVP, we created an interactive Gaussian distribution with perspective rendering using OpenGL as shown below. We based this model off of Chapter 3 in the [OpenGL Data Visualization Cookbook](https://www.oreilly.com/library/view/opengl-data-visualization/9781782169727/).

<p align="center"> <img src ="https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/tutorial3.gif"/> </p>

We created perspective rendering by setting up a virtual camera. This allowed us to render a scene that resembles how the human eye sees the world. We also took the output of a Gaussian distribution and generated a 3d dataset. To get a better visualization effect, we applied a heat map to each vertex within a shader file. We also incorporated user inputs, such as mouse and keyboard inputs, to enable more dynamic interactions that we might need when we integrate OpenGL with OpenCV. Following the tutorial, we wrote all of the callback functions interacting with mouse and keyboard inputs and linked those functions to the GLFW library event handlers. Our final result for the MVP is an interactive interface that allows the user to control the rendering object freely in space. Users can rotate the object at different angles by holding the mouse button and dragging the object in various directions. The gif above shows how we can rotate the virtual objects at any angle and can be extremely useful for visualizing complex datasets.

#### Medium Range Goal
For our medium range goal, we rendered a volumetric dataset in OpenGL (reference gif below). The dataset was an `obj` file obtained from the [Stanford 3D Scanning Repository](http://graphics.stanford.edu/data/3Dscanrep/). An `obj` file consists of a series of materials, vertices, and surfaces. Surfaces contain sets of polygons and lines that reference a material that should be used when rendering them. We chose a simplified `obj` file of a dragon that did not include any specific materials. In order to get the dragon to load in an OpenGL window, we had to write the following components:
-  An `ObjLoader` that loads in an `obj` file via recursion.
-  Some simple interactions that would rotate the model when the user presses certain keys.
-  Texture and shader programs that deal with how a model appears on the screen. The shader program loads in a vertex (`pointcloud.vert`) and fragment shader (`pointcloud.frag`).  
- A `main` program that initializes the OpenGL window object, calls the object loader, and responds to various keyboard commands.

The work we did for our medium range goal was based off of Chapter 6 in the [OpenGL Data Visualization Cookbook](https://www.oreilly.com/library/view/opengl-data-visualization/9781782169727/).      

<p align="center"> <img src ="https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/dragon.gif"/> </p>

#### Stretch Goal
##### Android Studio
In our first attempt at tackling our stretch goal, we decided to complete Chapters 7 and 8 of the OpenGL cookbook. These chapters walked through the steps to create an Android app using the Android SDK and NDK, which would allow us to program the majority of our Android code using C. Completing these 2 chapters successfully would have resulted in an Android app that uses the phone’s camera to project an object file rendered with OpenGL onto surfaces (detected using OpenCV), effectively creating a basic augmented reality app. Unfortunately, we encountered many issues along the way, as the Cookbook’s instructions to configure the file structure and dependencies indirectly caused issues with some of our Ubuntu partitions. We also discovered that most of the code was deprecated, so we attempted to transfer the project into Android Studio. However, the file structures were not compatible, and we were never able to successfully build the project, even after the code was examined by all of us both collaboratively and individually.

At this point, we switched gears and searched the internet for similar tutorials, but all of the examples online made almost no use of C. We initially followed a couple of these tutorials, and were able to successfully get them up and running, but we have decided to set this aspect of the project aside in favor of other, C-based aspects of our project. We are keeping the app implementation open for consideration for our final stretch goal.

The following gif is an example of an Android and OpenGL [tutorial](https://github.com/doggycoder/AndroidOpenGLDemo) that we found online and were able to run successfully. When we tried to load other objects, we found that the mtl loader requires texture maps in the form of images. This limitation motivated us to learn more about texture and shading techniques.

<p align="center"> <img src ="https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/android.gif"/> </p>

##### OpenCV and C++
After switching gears and tabling the Android AR application, we decided to dig into OpenCV (and consequently, some C++). We are currently working towards implementing marker detection in OpenCV by accessing our computers’ webcams. So far, we have read through multiple tutorials, some OpenCV documentation, compared potential libraries, and are taking some time to learn C++ syntax. We are working towards having a working program that can detect ArUco markers such as [these](https://docs.opencv.org/3.1.0/d5/dae/tutorial_aruco_detection.html). The ArUco markers are being used because they are really easy to detect and make the calibration process far more straightforward than standard object detection.

##### Texture and Shading
In order to incorporate materials from our `obj` files, we needed to improve our `ObjLoader` program. We found a really helpful [obj loader library](https://github.com/rlk/obj), which we decided to incorporate into our project. From the Android tutorials, we learned that some obj files come with `mtl` (material library) files. Material library files contain various material definitions (color, texture, and reflection) for a 3d object, which are applied to the surfaces and vertices of objects. Our `ObjLoader` from our medium range goal was not able to load in `mtl` files, so we transitioned to a more versatile obj loader library. The new library basically parses the `obj` file line by line, categorizes each line by a tag (e.g. `f`, `v`, `use`), and calls the corresponding helper function to deal with the data. With this library, we were able to load in a treasure chest object, as shown below.     

<p align="center"> <img src ="https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/chest.gif"/> </p>


In an effort to make the objects look more realistic, we researched different shader files. [Basic shading](https://learnopengl.com/Getting-started/Shaders) helps us understand how to write shader files using the language, GLSL. The shader we used for our MVP and medium stretch goals was simply a heat map. In order to render graphics more realistically, we decided to incorporate some basic shading techniques from [another tutorial](http://www.opengl-tutorial.org/beginners-tutorials/tutorial-8-basic-shading/). These techniques include being brighter when the object is closer to a light source, being darker when the light is not directly pointing at the model, and incorporating ambient light on the model. Following this tutorial, we were able to render a basic realistic illumination for the object we loaded.
<p align="center"> <img src ="https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/shader.png"/> </p>

### Next Steps
1. (Hwei-Shin) Complete the OpenCV aspect of the project to detect ArUco markers and test on our own laptops. In order to complete this, we want to fully understand some specific syntax that we have encountered, such as:
- Overloaded `<<` and `>>`
- What it means when `cv::Mat& [variable name]` is passed as a parameter to a function.
2. (Enmo and Cassandra) Consolidate the texture and shading code to produce a versatile `obj` file loader with realistic illumination based on where the user sets a light source.
3. (Enmo and Cassandra) Refine interactive components.
4. (All) Combine OpenGL and OpenCV code.
5. Make objects even more realistic by writing custom shader files.

### Resources
- [OpenGL Cookbook](https://www.oreilly.com/library/view/opengl-data-visualization/9781782169727/): we used this to accomplish our MVP and medium stretch goal.
- [Android OpenGL Tutorial](https://github.com/doggycoder/AndroidOpenGLDemo): used in android studio stretch goal.
-[OpenCV Markerless Augmented Reality](https://medium.com/@ahmetozlu93/marker-less-augmented-reality-by-opencv-and-opengl-531b2af0a130): used for the OpenCV stretch goal.
-[Poly: Open-Source 3D Models](https://poly.google.com/): contains many open source 3d models that we tried loading in.
- [Obj Loader Library](https://github.com/rlk/obj): used for the texture and shading stretch goal.
- [Shader tutorial](http://www.opengl-tutorial.org/): used for texture and shading stretch goal.
