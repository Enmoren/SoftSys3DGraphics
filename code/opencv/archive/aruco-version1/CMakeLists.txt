cmake_minimum_required(VERSION 2.8)
project(helloAR)

SET(CMAKE_MODULE_PATH ${CMAKE_INSTALL_PREFIX}/lib/cmake/ )
set (CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

# Required packages
find_package(OpenCV REQUIRED)

add_executable(helloAR arucodetection.cpp)
target_link_libraries(helloAR ${OpenCV_LIBS} aruco)
