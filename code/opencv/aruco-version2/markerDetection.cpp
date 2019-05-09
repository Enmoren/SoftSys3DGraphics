// #include <opencv2/aruco.hpp>
#include "/home/enmo/opencv/modules/core/include/opencv2/core.hpp"
#include "/home/enmo/opencv_contrib/modules/aruco/include/opencv2/aruco.hpp"
#include "/home/enmo/opencv/modules/highgui/include/opencv2/highgui.hpp"
#include "/home/enmo/opencv_contrib/modules/aruco/include/opencv2/aruco/dictionary.hpp"
// #include "/home/enmo/opencv/include/opencv/cv.hpp"
// #include "/home/enmo/opencv/modules/core/include/opencv2/core/core.hpp"
#include "/home/enmo/opencv_contrib/modules/xfeatures2d/include/opencv2/xfeatures2d.hpp"
#include "/home/enmo/opencv/modules/features2d/include/opencv2/features2d.hpp"
#include "/home/enmo/opencv/modules/videoio/include/opencv2/videoio.hpp"
#include <sstream>
#include <iostream>
#include <string>
#include <list>
#include <functional>
#include <vector>
// #include <opencv2/legacy/legacy.hpp>

using namespace std;

int main(int argc, char **argv){
cv::VideoCapture inputVideo;
inputVideo.open(0);
cv::Mat cameraMatrix, distCoeffs;
// camera parameters are read from somewhere
// readCameraParameters(cameraMatrix, distCoeffs);
while (inputVideo.grab()) {
    cv::Mat image, imageCopy;
    inputVideo.retrieve(image);
    image.copyTo(imageCopy);
    std::vector<int> ids;
    std::vector<std::vector<cv::Point2f> > corners;
    cv::aruco::detectMarkers(image, dictionary, corners, ids);
    // if at least one marker detected
    if (ids.size() > 0) {
        cv::aruco::drawDetectedMarkers(imageCopy, corners, ids);
        std::vector<cv::Vec3d> rvecs, tvecs;
        cv::aruco::estimatePoseSingleMarkers(corners, 0.05, cameraMatrix, distCoeffs, rvecs, tvecs);
        // draw axis for each marker
        for(int i=0; i<ids.size(); i++)
            cv::aruco::drawAxis(imageCopy, cameraMatrix, distCoeffs, rvecs[i], tvecs[i], 0.1);
    }
    cv::imshow("out", imageCopy);
    // char key = (char) cv::waitKey(waitTime);
    // if (key == 27)
    //     break;
}
}
