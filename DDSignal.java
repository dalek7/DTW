package com.example.dtw.geardtw;

/**
 * Created by Seung-Chan on 2015-06-20.
 */
public class DDSignal {

    public static boolean areEqualDouble(double a, double b, int precision) {
        return Math.abs(a - b) <= Math.pow(10, -precision);
    }

    public static boolean areEqualFloat(float a, float  b, int precision) {
        return Math.abs(a - b) <= Math.pow(10, -precision);
    }

    public static float DTW(float[] s, float[] t, int w, float gain_mult_template)
    {
        float d=0;
        int ns = s.length;
        int nt = t.length;
        //String aa = String.format("%d %d w%d g%.2f", ns, nt,w, gain_mult_template); //100 70
        //Dbg.out(aa);
        int i,j;
        int j1,j2;
        float cost,temp;

        int sizediff = Math.abs(ns - nt);

        float[][] D = new float[ns+1][nt+1];

        // initialization
        for(i=0;i<ns+1;i++)
        {
            for(j=0;j<nt+1;j++)
            {
                D[i][j]=-1;
            }
        }
        D[0][0]=0;

        // Dynamic programming
        for(i=1;i<=ns;i++)
        {
            if(w==-1)
            {
                j1=1;
                j2=nt;
            }
            else
            {
                j1= i-w>1 ? i-w : 1;
                j2= i+w<nt ? i+w : nt;
            }
            for(j=j1;j<=j2;j++)
            {
                cost=vectorDistance2(s,t,i-1,j-1,gain_mult_template);
                /*
                if(j==30)
                {
                    String aa = String.format("c%.3f",cost); //100 70
                    Dbg.out(aa);

                }
                */
                temp=D[i-1][j];
                if(D[i][j-1]!=-1)
                {
                    if(temp==-1 || D[i][j-1]<temp) temp=D[i][j-1];
                }
                if(D[i-1][j-1]!=-1)
                {
                    if(temp==-1 || D[i-1][j-1]<temp) temp=D[i-1][j-1];
                }

                D[i][j]=cost+temp;
            }


        }


        // resulting distance
        //D[ns-1][nt-1] = 0.1f;

        //String aa = String.format("D%.3f", D[ns][nt]); //100 70
        //Dbg.out(aa);

        d=D[ns-1][nt-1];


        return d;
    }

    //inline float DTW(float* s, int ns,  float *t, int nt, int w=-1, float gain_mult_template=1.0)



    public static float DTW(float[] s, float[] t, int w)
    {
        return DTW(s, t, w, 1.0f);

    }


    public static float vectorDistance2(float []s, float []t, int i, int j)
    {
        return vectorDistance2(s, t, i, j, 1);
    }
    public static float vectorDistance2(float []s, float []t, int i, int j, float gain_mult_template)
    {

        int ns = s.length;
        int nt = t.length;

        float ss,tt;

        ss=s[i];
        tt=gain_mult_template*t[j];
        float result1 =(ss-tt)*(ss-tt);

        float result = (float) Math.sqrt(result1);

        return result;
    }



}
