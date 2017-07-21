cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -UNDEBUG")

add_library(native_lib_GifDecoder SHARED
            GifDecoder.cpp
            BitmapIterator.cpp
            BitWritingBlock.cpp
            DataBlock.cpp
            GifFrame.cpp
            native_lib_GifDecoder.cpp)

# Include libraries needed for native-media-jni lib
target_link_libraries(native_lib_GifDecoder
                      -ljnigraphics
                      android
                      log
                      OpenMAXAL)
