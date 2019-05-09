## Setting Up OpenCV's ArUco Module
### Building OpenCV
Using the ArUco library requires a version of OpenCV that is built with the ArUco module included. To do so, execute the following commands in the terminal (we were using Ubuntu 16):
1. Clone the OpenCV repositories from Github (we cloned into our root directory):
```
$ git clone https://github.com/opencv/opencv.git
$ git clone https://github.com/opencv/opencv_contrib.git
```
2. Update dependencies:
```
$ sudo apt-get install libpng12-0 libpng12-dev
```
3. Prepare to build OpenCV. `cd` into the cloned OpenCV folder and make a build directory:
```
$ cd ~/opencv
$ mkdir build && cd build
```

4. Run CMake with the following line. Note that `OPENCV_EXTRA_MODULES_PATH` should be set to the absolute path to your cloned copy of the `opencv_contrib/modules` Github repository from step 1. We set `-DBUILD_EXAMPLES=on` so that we will be able to build and run our ArUco sample code, according to (this)[https://docs.opencv.org/3.4.0/d5/dae/tutorial_aruco_detection.html] tutorial from OpenCV.  
```
$ cmake -D CMAKE_BUILD_TYPE=Release -D CMAKE_INSTALL_PREFIX=/usr/local -D OPENCV_EXTRA_MODULES_PATH=~/opencv_contrib/modules -DBUILD_EXAMPLES=on ..
$ make -j4
```
**NOTE**: If you get errors at this point, we have a couple of suggestions which we found helpful in our process:
* Check out this [Towards Data Science article](https://towardsdatascience.com/how-to-install-opencv-and-extra-modules-from-source-using-cmake-and-then-set-it-up-in-your-pycharm-7e6ae25dbac5) which shows how to use the `cmake-gui` tool, so you can easily select/de-select packages that are either desired or unnecessary. We used steps 1 through 4.
* If the only extra module you need is the ArUco library, you can set `OPENCV_EXTRA_MODULES_PATH` to be `~/opencv_contrib/modules/aruco`

### Building and Running the Program
1. Once you have successfully built OpenCV including the ArUco library and `opencv_contrib` samples, create 2 empty files, `DetectMarkers.cpp` and `CMakeLists.txt`. 
2. Copy the code from the [opencv_contrib Github](https://github.com/opencv/opencv_contrib/blob/master/modules/aruco/samples/detect_markers.cpp) into `DetectMarkers.cpp`.

3. Then, copy/paste the following code into `CMakeLists.txt`:
```
cmake_minimum_required(VERSION 2.8)
project( DetectMarkers )
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
include_directories( ${OpenCV_INCLUDE_DIRS} )
add_executable( DetectMarkers DetectMarkers.cpp )
target_link_libraries( DetectMarkers ${OpenCV_LIBS} )
```
4. Run CMake to generate the executable:
```
$ cmake .
$ make
```
5. Before running, download an image containing any ArUco markers you want to be recognized by the program.
6. Run the code!
```
$ ./DetectMarkers "<path_to_marker_image>/marker.png" -d=10 -id=1
```
<p align="center"> <img src ="https://github.com/Enmoren/SoftSys3DGraphics/blob/master/reports/marker.gif"/> </p>
