cmake_minimum_required(VERSION 3.18)
project(aimbot)

add_library(aimbot SHARED
    aimbot.cpp
)

target_include_directories(aimbot PRIVATE .)
target_link_libraries(aimbot log)
