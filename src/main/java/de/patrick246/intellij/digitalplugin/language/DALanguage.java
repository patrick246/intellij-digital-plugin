package de.patrick246.intellij.digitalplugin.language;

import com.intellij.lang.Language;

public class DALanguage extends Language {
    public static final DALanguage INSTANCE = new DALanguage();

    private DALanguage() {
        super("DigitalAssembler");
    }

}
