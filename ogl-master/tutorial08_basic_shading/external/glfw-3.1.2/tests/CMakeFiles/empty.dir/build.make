# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.5

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/enmo/SoftSys3DGraphics/ogl-master

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading

# Include any dependencies generated for this target.
include external/glfw-3.1.2/tests/CMakeFiles/empty.dir/depend.make

# Include the progress variables for this target.
include external/glfw-3.1.2/tests/CMakeFiles/empty.dir/progress.make

# Include the compile flags for this target's objects.
include external/glfw-3.1.2/tests/CMakeFiles/empty.dir/flags.make

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.o: external/glfw-3.1.2/tests/CMakeFiles/empty.dir/flags.make
external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.o: ../external/glfw-3.1.2/tests/empty.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.o"
	cd /home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading/external/glfw-3.1.2/tests && /usr/bin/cc  $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/empty.dir/empty.c.o   -c /home/enmo/SoftSys3DGraphics/ogl-master/external/glfw-3.1.2/tests/empty.c

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/empty.dir/empty.c.i"
	cd /home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading/external/glfw-3.1.2/tests && /usr/bin/cc  $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/enmo/SoftSys3DGraphics/ogl-master/external/glfw-3.1.2/tests/empty.c > CMakeFiles/empty.dir/empty.c.i

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/empty.dir/empty.c.s"
	cd /home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading/external/glfw-3.1.2/tests && /usr/bin/cc  $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/enmo/SoftSys3DGraphics/ogl-master/external/glfw-3.1.2/tests/empty.c -o CMakeFiles/empty.dir/empty.c.s

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.o.requires:

.PHONY : external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.o.requires

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.o.provides: external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.o.requires
	$(MAKE) -f external/glfw-3.1.2/tests/CMakeFiles/empty.dir/build.make external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.o.provides.build
.PHONY : external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.o.provides

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.o.provides.build: external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.o


external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.o: external/glfw-3.1.2/tests/CMakeFiles/empty.dir/flags.make
external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.o: ../external/glfw-3.1.2/deps/tinycthread.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building C object external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.o"
	cd /home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading/external/glfw-3.1.2/tests && /usr/bin/cc  $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/empty.dir/__/deps/tinycthread.c.o   -c /home/enmo/SoftSys3DGraphics/ogl-master/external/glfw-3.1.2/deps/tinycthread.c

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/empty.dir/__/deps/tinycthread.c.i"
	cd /home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading/external/glfw-3.1.2/tests && /usr/bin/cc  $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/enmo/SoftSys3DGraphics/ogl-master/external/glfw-3.1.2/deps/tinycthread.c > CMakeFiles/empty.dir/__/deps/tinycthread.c.i

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/empty.dir/__/deps/tinycthread.c.s"
	cd /home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading/external/glfw-3.1.2/tests && /usr/bin/cc  $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/enmo/SoftSys3DGraphics/ogl-master/external/glfw-3.1.2/deps/tinycthread.c -o CMakeFiles/empty.dir/__/deps/tinycthread.c.s

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.o.requires:

.PHONY : external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.o.requires

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.o.provides: external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.o.requires
	$(MAKE) -f external/glfw-3.1.2/tests/CMakeFiles/empty.dir/build.make external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.o.provides.build
.PHONY : external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.o.provides

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.o.provides.build: external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.o


# Object files for target empty
empty_OBJECTS = \
"CMakeFiles/empty.dir/empty.c.o" \
"CMakeFiles/empty.dir/__/deps/tinycthread.c.o"

# External object files for target empty
empty_EXTERNAL_OBJECTS =

external/glfw-3.1.2/tests/empty: external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.o
external/glfw-3.1.2/tests/empty: external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.o
external/glfw-3.1.2/tests/empty: external/glfw-3.1.2/tests/CMakeFiles/empty.dir/build.make
external/glfw-3.1.2/tests/empty: external/glfw-3.1.2/src/libglfw3.a
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/librt.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libm.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libX11.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libXrandr.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libXinerama.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libXi.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libXxf86vm.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libXcursor.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libGL.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/librt.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libm.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libX11.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libXrandr.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libXinerama.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libXi.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libXxf86vm.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libXcursor.so
external/glfw-3.1.2/tests/empty: /usr/lib/x86_64-linux-gnu/libGL.so
external/glfw-3.1.2/tests/empty: external/glfw-3.1.2/tests/CMakeFiles/empty.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Linking C executable empty"
	cd /home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading/external/glfw-3.1.2/tests && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/empty.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
external/glfw-3.1.2/tests/CMakeFiles/empty.dir/build: external/glfw-3.1.2/tests/empty

.PHONY : external/glfw-3.1.2/tests/CMakeFiles/empty.dir/build

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/requires: external/glfw-3.1.2/tests/CMakeFiles/empty.dir/empty.c.o.requires
external/glfw-3.1.2/tests/CMakeFiles/empty.dir/requires: external/glfw-3.1.2/tests/CMakeFiles/empty.dir/__/deps/tinycthread.c.o.requires

.PHONY : external/glfw-3.1.2/tests/CMakeFiles/empty.dir/requires

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/clean:
	cd /home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading/external/glfw-3.1.2/tests && $(CMAKE_COMMAND) -P CMakeFiles/empty.dir/cmake_clean.cmake
.PHONY : external/glfw-3.1.2/tests/CMakeFiles/empty.dir/clean

external/glfw-3.1.2/tests/CMakeFiles/empty.dir/depend:
	cd /home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/enmo/SoftSys3DGraphics/ogl-master /home/enmo/SoftSys3DGraphics/ogl-master/external/glfw-3.1.2/tests /home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading /home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading/external/glfw-3.1.2/tests /home/enmo/SoftSys3DGraphics/ogl-master/tutorial08_basic_shading/external/glfw-3.1.2/tests/CMakeFiles/empty.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : external/glfw-3.1.2/tests/CMakeFiles/empty.dir/depend

