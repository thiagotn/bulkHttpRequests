package dev.util;

import java.util.UUID;

public class GuidUtil {

    public static void main(String[] args) {
        int total = 1000;
        for (int i=0 ; i<= total; i++){
            System.out.println(UUID.randomUUID().toString());
        }
    }
}
