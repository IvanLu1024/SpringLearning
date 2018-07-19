package com.ivan.conditions;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    /**
     *
     * @param importingClassMetadata 当前标注@Import注解的类的所有信息
     * @return 导入到容器中的组件全类名
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {


        //方法不能返回null值
        return new String[]{"com.ivan.beans.Yellow","com.ivan.beans.Blue"};
    }
}
