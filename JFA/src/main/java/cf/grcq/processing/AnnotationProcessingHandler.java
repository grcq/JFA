package cf.grcq.processing;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

public abstract class AnnotationProcessingHandler<T> extends AbstractProcessor {

    abstract public void handle(List<Annotation> annotations, T t);

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
