{\rtf1\ansi\ansicpg1252\cocoartf1671\cocoasubrtf600
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\margl1440\margr1440\vieww28600\viewh17500\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 class Solution \{\
    int total=0;\
    public int numDecodings(String s) \{\
        if(s==null || s.length()==0)\{\
           return 0;\
        \}\
        int[] arr=new int[s.length()];\
        int i=0;\
        for(String ts : s.split(""))\{\
            arr[i]=Integer.parseInt(ts);\
            i++;\
        \}\
        solve(arr,0);\
        return total;\
    \}\
    \
    \
    public void solve(int[] arr, int cur)\{\
        \
        if(cur==arr.length)\
            total++;\
        else\{\
            if(arr[cur]==0)\
                return;\
            if(cur!=arr.length-1 && (arr[cur]*10+arr[cur+1]<=26))\
                solve(arr, cur+2);\
            solve(arr,cur+1);\
        \}\
    \}\
\}}
