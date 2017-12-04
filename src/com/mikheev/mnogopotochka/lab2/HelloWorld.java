package com.mikheev.mnogopotochka.lab2;

import mpi.*;

public class HelloWorld {

        public static void main(String args[]) throws Exception {
                  MPI.Init(args);
                  int me = MPI.COMM_WORLD.Rank();
                  int size = MPI.COMM_WORLD.Size();
                  System.out.println("Hello world from <"+me+"> from <"+size);
                  MPI.Finalize();
               }
}
