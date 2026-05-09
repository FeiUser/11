package com.jta.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    male(0, "男"),
    female(1, "女");

    private final Integer value;
    private final String name;

}