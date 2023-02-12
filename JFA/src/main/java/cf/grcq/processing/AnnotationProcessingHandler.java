package cf.grcq.processing;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Annotation;
import java.util.Set;

@AutoService(Processor.class)
public abstract class AnnotationProcessingHandler<T> extends AbstractProcessor {

    abstract public void handle(Element element, T t);

    @SuppressWarnings("all")
    @Override
    public final boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        annotations.forEach((anno) -> {
            for (Element element : roundEnv.getElementsAnnotatedWith(anno)) {

                try {
                    Class<? extends Annotation> clazz = (Class<? extends Annotation>) Class.forName(anno.getQualifiedName().toString());

                    T t = (T) element.getAnnotation(clazz);
                    if (t == null) throw new RuntimeException("Annotation is null.");

                    handle(element, t);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return true;
    }

    @Override
    public final SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }
}
