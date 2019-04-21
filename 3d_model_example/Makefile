PKG_CONFIG_PATH=/usr/lib/x86_64-linux-gnu/pkgconfig
# export PKG_CONFIG_PATH=/usr/lib/x86_64-linux-gnu/pkgconfig
CFILES = ../common/shader_copy.cpp ../common/texture_copy.cpp ../common/controls_copy.cpp ../common/ObjLoader.cpp main.cpp
CFLAGS = -c
OPT = -O3
INCLUDES = -I../common -I/usr/include -I/usr/include/SOIL -I. `pkg-config --cflags glfw3` `pkg-config --cflags assimp` `pkg-config --cflags opencv`
LIBS = -lm -L/usr/local/lib -lGLEW -lSOIL `pkg-config --static --libs glfw3` `pkg-config --static --libs assimp` `pkg-config --libs opencv`
CC = g++
OBJECTS=$(CFILES:.cpp=.o)
EXECUTABLE=main

all: $(CFILES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(OPT) $(INCLUDES) $(OBJECTS) -o $@ $(LIBS)
.cpp.o:
	$(CC) $(OPT) $(CFLAGS) $(INCLUDES) $< -o $@

clean:
	rm -v -f *~ ../common/*.o *.o $(EXECUTABLE)
