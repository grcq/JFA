package dev.grcq.language;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CMSField {

    private final String name;
    private final Class<?> identifier;
    private String value;

}
