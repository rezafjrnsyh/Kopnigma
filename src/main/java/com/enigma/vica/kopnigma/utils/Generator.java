package com.enigma.vica.kopnigma.utils;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Generator {
    public Long generaterekening() {
        Random rnd = new Random();
        Long generate = (long) (100000000000L + rnd.nextFloat() * 900000000000L);
        return generate;
    }
}
