## 3DGraphics
#### Authors: Enmo Ren, Cassandra Overney, Hwei-Shin Harriman

For this project, we will be implementing matrix multiplication using high-performance scientific computation. Our MVP is to use caching and simple matrix operations (i.e. transpose) to optimize matrix multiplication. Our medium range goal is to apply a divide and conquer algorithm with parallelization. Our stretch goal is to apply our matrix multiplication algorithms to mesh networking. Another stretch goal involves implementing the Coppersmith- Winograd algorithm, which is currently one of the most efficient algorithms for matrix multiplication.
##### Our collective learning goals include:
- Improve our C programming skills (designing small tools)
- Gain exposure in optimization methods
##### Our individual learning goals include:
- **Cassandra**: I love linear algebra, so I just want to apply what we are learning in class to my favorite math topic. I am also interested in tiling multiplication with loop nest optimization and potentially learning about mesh networks.
- **Enmo**: I want to get better at programming in C, as well as familiarize myself with shared memory parallelism and distributed computing for optimization. By potentially implementing multi-thread processing for our program, I will gain a deeper understanding of the operating system.
- **Hwei-Shin**: I am interested in learning about memory parallelism and the tradeoffs of different matrix multiplication algorithms. I am also interested in gaining an intuitive, low-level understanding of why specific algorithms and optimization techniques are preferred. I want to gain experience writing clean, robust code in C. I would also like to gain some exposure to manipulating meshes using linear algebra if we have the time.

We did some research on different matrix multiplication algorithms and found a very helpful [Github tutorial](https://gist.github.com/nadavrot/5b35d44e8ba3dd718e595e40184d03f0) on implementing matrix multiplication with C. We are planning to go through that tutorial as our first step, which includes basic matrix multiplication, caching, register blocking, and tiling.
We have also purchased the Udemy course on Scientific Computing in C, which has tutorials on linear equations and matrix methods, as well as optimization techniques using parallelism, pipelining, and CUDA.  We intend to go through the matrix multiplication part of that course, and reference additional portions of the course as necessary.

##### Our next three concrete tasks are:
1. **Hwei-Shin**: Implement and test the most trivial matrix multiplication algorithm with no optimization
2. **Cassandra**: Find large matrices to multiply and test with task 1
3. **Enmo**: Find a way to quantify the efficiency of a program (UNIX command) and apply to task 1
4. **Everyone**: Start the Udemy course (“Linear Equations and Matrix Methods” video)
5. **Whoever finishes first**: Create a resources file (markdown)

##### Resources:
1. [GitHub tutorial](https://github.com/gigamailer/simplenin)
2. [Udemy Course](https://www.udemy.com/high-performance-scientific-computing-with-c/)
