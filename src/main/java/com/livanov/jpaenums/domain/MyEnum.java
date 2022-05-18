package com.livanov.jpaenums.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public enum MyEnum {
    FIRST(),
    SECOND(FIRST),
    THIRD(MyEnum.allButLast());

    private static List<MyEnum> allButLast() {
        return asList(FIRST, SECOND);
    }

    private final List<MyEnum> parents;

    public List<MyEnum> getParents() {
        return Collections.unmodifiableList(parents);
    }

    MyEnum(List<MyEnum> parents) {
        this.parents = new ArrayList<>(parents);
    }

    MyEnum(MyEnum... parents) {
        this(asList(parents));
    }
}
