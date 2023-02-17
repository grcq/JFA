package dev.grcq.utils;

import com.google.common.collect.Lists;
import dev.grcq.processing.AnnotationByteCodeHandler;
import dev.grcq.processing.TestHandler;

import java.util.ArrayList;
import java.util.List;

public class ProcessingUtils {

    public static List<AnnotationByteCodeHandler<?>> getAllByteCodeHandlers() {
        return Lists.newArrayList(new TestHandler());
    }

}
