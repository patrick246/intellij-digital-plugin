package de.patrick246.intellij.digitalplugin;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class DALexerAdapter extends FlexAdapter {
    public DALexerAdapter() {
        super(new DALexer((Reader)null));
    }
}
