package com.mikheev.mnogopotochka.lab2;

import mpi.MPI;

import java.util.Random;

public class Scalar {
    public static void main(String args[]){
        MPI.Init(args);

        double[] result = new double[1];
        int me = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        double startwtime=0.0;
        double endwtime=0.0;
        int total = 80000000;

        int d = total/size+1;

        double[] a = new double[d];
        double[] b = new double[d];
        Random r = new Random();

        MPI.COMM_WORLD.Bcast(d, 1, 0,MPI.INT, 0);

        if (me == 0){
            startwtime = MPI.Wtime();
            for (int dest=1; dest<size;dest++){
                for (int i=0; i<d; i++){
                    a[i] = r.nextInt();
                    b[i] = r.nextInt();
                }

                MPI.COMM_WORLD.Send(a,0, a.length, MPI.DOUBLE, dest,0);
                MPI.COMM_WORLD.Send(b,0, b.length, MPI.DOUBLE, dest,0);
            }

            d = total - d*(size-1);
            for (int i=0; i<d;i++){

                a[i] = r.nextInt();
                b[i] = r.nextInt();
            }

        } else {

            MPI.COMM_WORLD.Recv(a,0,d,MPI.DOUBLE,0,0);
            MPI.COMM_WORLD.Recv(b,0,d,MPI.DOUBLE,0,0);

        }

        int[] sum = new int[1];

        for (int i=0; i<d;i++){

            sum[0]+=a[i]*b[i];

        }

//        MPI.COMM_WORLD.Reduce(sum,0,result,0,1,MPI.DOUBLE,MPI.SUM,0);

        if (me == 0){

            System.out.println("answer is"+result[0]+" time of calcs is equal to "+(MPI.Wtime()-startwtime));

        }
        MPI.Finalize();

    }
}
