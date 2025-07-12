package com.shouldsun;

import lombok.extern.slf4j.Slf4j;
import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;
import org.jboss.jandex.Index;
import org.jboss.jandex.MethodInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Unit test for simple App.
 */
@Slf4j
public class JandexTest {
    @Test
    public void testGainClassInfo() {
        try {
            Index index = Index.of(Map.class);
            ClassInfo clazz=index.getClassByName(DotName.createSimple("java.util.Map"));
            for (MethodInfo methodInfo : clazz.methods()) {
                log.debug("");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}