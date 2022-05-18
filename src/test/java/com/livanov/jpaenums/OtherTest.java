package com.livanov.jpaenums;

import com.livanov.jpaenums.domain.MyEnum;
import org.junit.jupiter.api.Test;

import static com.livanov.jpaenums.domain.MyEnum.FIRST;
import static com.livanov.jpaenums.domain.MyEnum.SECOND;
import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OtherTest {

    @Test
    void enum_member_is_instantiated_correctly() {
        assertThat(MyEnum.THIRD.getParents()).isEqualTo(asList(FIRST, SECOND));
    }
}
