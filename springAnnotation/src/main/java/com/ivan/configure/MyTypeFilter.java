package com.ivan.configure;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyTypeFilter implements TypeFilter {
    /**
     *
     * @param metadataReader ,读取到当前正在扫描的类的信息
     * @param metadataReaderFactory，可以获取到其他任何类的信息的
     * @return
     * @throws IOException
     */

    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        //TODO 获取当前类注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //TODO 获取当前正在扫描的类的类信息，例如类型，接口等信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //TODO 获取当前资源（类的路径）
        Resource resource = metadataReader.getResource();

        String className = classMetadata.getClassName();
        System.out.println("---->"+className);
        if (className.contains("er")){
            return true;
        }


        return false;
    }
}
