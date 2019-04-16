#include <stdio.h>
#include <opencv2/opencv.hpp>
using namespace cv;

int main(int argc, char **argv)
{

    if (argc != 2)
    {
        printf("usage: ReduceImage.out <Image_Path>\n");
        return -1;
    }

    Mat image = imread(argv[1], 1);

    if (!image.data)
    {
        printf("No image data provided.\n");
        return -1;
    }

    int divideWith = 64;

    Mat lookUpTable(1, 256, CV_8U);
    uchar *p = lookUpTable.ptr();
    for (int i = 0; i < 256; ++i)
        p[i] = (uchar)(divideWith * (i / divideWith));

    Mat reducedImage;
    LUT(image, lookUpTable, reducedImage);

    namedWindow("Reduce Image", WINDOW_AUTOSIZE);
    imshow("Reduc Image", reducedImage);
    waitKey(0);
    return 0;
}
