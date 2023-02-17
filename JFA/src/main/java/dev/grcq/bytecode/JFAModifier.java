package dev.grcq.bytecode;

import org.jetbrains.annotations.NonNls;

public enum JFAModifier {

    PUBLIC,
    PRIVATE,
    PROTECTED,
    STATIC,
    FINAL,
    SYNCHRONIZED;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
