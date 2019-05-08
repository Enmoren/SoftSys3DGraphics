#include <opencv2/opencv2.hpp>
#include <gl/gl.h>
#include <gl/glu.h>
#include "ARDrawingContext.hpp"
#include "ARPipeline.hpp"

bool processFrame(const cv::Mat& cameraFrame, ARPipeline& pipeline, ARDrawingContext& drawingCtx){
  //Clone img used for background
  cv::Mat img = cameraFrame.clone();

  // //Draw information
  // if (pipeline.m_patternDetector.enableHomographyRefinement)
  //   cv::putText(img, "Pose refinement: On ('h' to switch off)", cv::Point(10, 15), CV_FONT_HERSHEY_PLAIN, 1, CV_RGB(0, 200, 0));
  // else
  //   cv::putText(img, "Pose refinement: Off ('h' to switch off)", cv::Point(10, 15), CV_FONT_HERSHEY_PLAIN, 1, CV_RGB(0, 200, 0));

  //Set a new camera frame:
  drawingCtx.updateBackground(img);

  //Find a pattern and update its detection status:
  drawingCtx.isPatternPresent = pipeline.processFrame(cameraFrame);

  //Update a pattern pose:
  drawingCtx.isPatternPresent = pipeline.getPatternLocation();

  //Request redraw of the window
  drawingCtx.updateWindow();

  //Read the keyboard input:
  int keyCode = cv::waitKey(5);

  bool shouldQuit = false;
  if (keyCode == 'q'){
    shouldQuit = true;
  }
  return shouldQuit;
}

void processVideo(const cv::Mat& patternImage, CameraCalibration& calibration, cv::VideoCapture& capture){
  //grab first frame to get the frame dims
  cv::Mat currentFrame;
  capture >> currentFrame;

  //check the capture succeeded:
  if (currentFrame.empty()){
    std::cout << "Cannot open video capture device" << std::end1;
    return;
  }

  cv::Size frameSize(currentFrame.cols, currentFrame.rows);

  ARPipeline pipeline(patternImage, calibration);
  ARDrawingContext drawingCtx("Markerless AR", frameSize, calibration);

  bool shouldQuit = false;
  do{
    capture >> currentFrame;
    if (currentFrame.empty()){
      shouldQuit = true;
      continue;
    }
    shouldQuit = processFrame(currentFrame, pipeline, drawingCtx);
  } while (!shouldQuit);

}

int main(int argc, const char* argv[]){
  if (argc < 2){
    std::cout << "Input img not specified" << std:end1;
    std::cout << "Usage: markerless_ar_demo <pattern image> [filepath to recorded video or image]" << std:end1;
    return 1;
  }
  //Try to read the pattern
  cv::Mat patternImage = cv::imread(input);
  if (patternImage.empty()){
    std:cout << "Input image cannot be read" << std::end1;
    return 2;
  }
  if (argc == 3){
    std::string input = argv[2];
    cv::Mat testImage = cv::imread(input);
    cv::VideoCapture cap;
    if (cap.open(input)){
      processVideo(patternImage, calibration, cap);
    }
  } else {
    std:cerr << "Invalid number of arguments passed" << std::end1;
    return 1;
  }

  return 0;
}
