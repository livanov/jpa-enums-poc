package com.livanov.jpaenums;

import com.livanov.jpaenums.domain.MyClass;
import com.livanov.jpaenums.domain.MyEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:9.6.12:///databasename?TC_INITSCRIPT=schema.sql"
})
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaEnumsApplicationTests {

    @Autowired
    private EntityManager entityManager;

    @Test
    void write_read_enum_values_from_db() {
        MyClass obj = new MyClass(MyEnum.FIRST);
        entityManager.persist(obj);
        assertThat(getEnumValueFromDb()).isEqualTo("FIRST");

        {
            MyClass persisted = entityManager.find(MyClass.class, obj.getId());
            persisted.setMyEnum(MyEnum.SECOND);
            entityManager.persist(persisted);
            assertThat(getEnumValueFromDb()).isEqualTo("SECOND");
        }

        {
            MyClass persisted = entityManager.find(MyClass.class, obj.getId());
            persisted.setMyEnum(MyEnum.THIRD);
            entityManager.persist(persisted);
            assertThat(getEnumValueFromDb()).isEqualTo("THIRD");
        }
    }

    private String getEnumValueFromDb() {
        return entityManager.createNativeQuery("SELECT my_enum FROM my_class").getResultList()
                .get(0)
                .toString();
    }
}
