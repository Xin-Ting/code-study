package com.example.study.base.initalization;

import java.io.Serializable;

class Bowl implements Serializable {
        Bowl(int marker) {
            System.out.println("Bowl(" + marker + ")");
        }

        void f1(int marker) {
            System.out.println("f1(" + marker + ")");
        }
    }