package com.mikheev.mnogopotochka.lab2;

import mpi.*;

public class MonteCarloPi {

    public static final double PI = 3.141592653589793238462643;
    public static final int NUM = 2000000000;

    public MonteCarloPi() {
    }

    public static void main(String args[]) throws Exception {
        int[] intervals = new int[1];
        double[] mypi = new double[1];
        double[] pi = new double[1];
        long n;
        double h,x;
        double sum=0.0;

        try {
            args=MPI.Init(args);
            int me = MPI.COMM_WORLD.Rank();
            int size = MPI.COMM_WORLD.Size();
            intervals[0]=NUM;

            if(me==0) {
                System.out.println("Pi calculation with MonterCarlo method");
                System.out.println("Number of intervals (can be changed in source code): "+NUM);
                System.out.println("Number of processes: "+size);
                System.out.print("Calculating...");
            }

            MPI.COMM_WORLD.Bcast(intervals, 0, 1, MPI.INT, 0);
            n = intervals[0];
            h = 1.0 / (double) intervals[0];
            sum = 0.0;

            double start = MPI.Wtime();
            for (int i = me + 1; i <= n; i += size) {
                x = h * ((double)i - 0.5);
                sum += (4.0 / (1.0 + x*x));
            }
            mypi[0] = h * sum;
            double end = MPI.Wtime();
            MPI.COMM_WORLD.Reduce(mypi, 0, pi, 0, 1, MPI.DOUBLE, MPI.SUM, 0);

            if(me==0) {
                System.out.println("done!");
                System.out.format("Time: %f seconds\n",end-start);
                System.out.format("Pi is approximately %.16f\n",pi[0]);
                System.out.format("Error is %.16f\n",pi[0]-MonteCarloPi.PI);
            }
        }
        catch (MPIException e) {
            e.printStackTrace();
        }
        finally
        {
            MPI.Finalize();
        }
    }
}
