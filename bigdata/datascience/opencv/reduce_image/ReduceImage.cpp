#include <stdio.h>
#include <opencv2/opencv.hpp>
using namespace cv;

Mat& ScanImageAndReduceC(Mat& I, const uchar* const table)
{
    // accept only char type matrices
    CV_Assert(I.depth() == CV_8U);
    int channels = I.channels();
    int nRows = I.rows;
    int nCols = I.cols * channels;

    if (I.isContinuous())
    {
        nCols *= nRows;
        nRows = 1;
    }

    int i,j;
    uchar* p;

    for( i = 0; i < nRows; ++i)
    {
        p = I.ptr<uchar>(i);
        for ( j = 0; j < nCols; ++j)
        {
            p[j] = table[p[j]];
        }
    }

    return I;
}

int main(int argc, char** argv )
{

    if (argc != 2)
    {
        printf("usage: ReduceImage.out <Image_Path>\n");
        return -1;
    }

    Mat image = imread( argv[1], 1 );

    if (!image.data)
    {
        printf("No image data provided.\n");
        return -1;
    }


    int divideWith = 32;

    uchar table[256];
    for (int i = 0; i < 256; ++i) {
       table[i] = (uchar)(divideWith * (i/divideWith));
    }


    Mat reducedImage = ScanImageAndReduceC(image, table);

    namedWindow("Reduce Image", WINDOW_AUTOSIZE);
    imshow("Reduc Image", reducedImage);
    waitKey(0);
    return 0;
}
