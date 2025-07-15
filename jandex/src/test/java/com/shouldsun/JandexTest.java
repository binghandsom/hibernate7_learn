package com.shouldsun;

import lombok.extern.slf4j.Slf4j;
import org.jboss.jandex.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
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
                log.debug("method: {}", methodInfo.name());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testGainAnnotationInfo() {
        Indexer indexer=new Indexer();
        try {
            indexer.indexClass(Thread.class);
            indexer.indexClass(String.class);
            Index index=indexer.complete();

            DotName deprecated=DotName.createSimple("java.lang.Deprecated");
            List<AnnotationInstance> annotationInstanceList=index.getAnnotations(deprecated);

            for(AnnotationInstance annotationInstance:annotationInstanceList){
                switch (annotationInstance.target().kind()){
                    case METHOD:
                        log.debug(annotationInstance.target().toString());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}