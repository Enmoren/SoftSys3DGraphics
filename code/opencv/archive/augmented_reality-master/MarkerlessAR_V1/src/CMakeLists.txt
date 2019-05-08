add_executable(ARProject ARDrawingContext.cpp
ARDrawingContext.hpp
CameraCalibration.cpp
CameraCalibration.hpp
GeometryTypes.cpp
GeometryTypes.hpp
main.cpp
ARPipeline.hpp
ARPipeline.cpp
Pattern.cpp
Pattern.hpp
PatternDetector.cpp
PatternDetector.hpp
DebugHelpers.hpp
)

target_link_libraries( ARProject ${OpenCV_LIBRARIES} )
target_link_libraries( ARProject ${OPENGL_LIBRARIES} )
     
install (TARGETS ARProject DESTINATION bin)
