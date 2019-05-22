package de.patrick246.intellij.digitalplugin.language.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import de.patrick246.intellij.digitalplugin.language.DALexerAdapter;
import de.patrick246.intellij.digitalplugin.language.psi.DATypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class DASyntaxHighlighting extends SyntaxHighlighterBase {

    private static final TextAttributesKey INSTRUCTION = createTextAttributesKey("DA_INSTRUCTION", DefaultLanguageHighlighterColors.KEYWORD);
    private static final TextAttributesKey COMMENT = createTextAttributesKey("DA_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    private static final TextAttributesKey STRING = createTextAttributesKey("DA_STRING", DefaultLanguageHighlighterColors.STRING);
    private static final TextAttributesKey HEXNUM = createTextAttributesKey("DA_HEXNUM", DefaultLanguageHighlighterColors.NUMBER);
    private static final TextAttributesKey LABEL = createTextAttributesKey("DA_LABEL", DefaultLanguageHighlighterColors.LABEL);
    private static final TextAttributesKey REGISTER = createTextAttributesKey("DA_REGISTER", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL);

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new DALexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (
            tokenType.equals(DATypes.SIMPLEINSTRUCTION) ||
            tokenType.equals(DATypes.REGREGINSTRUCTION) ||
            tokenType.equals(DATypes.REGIMMINSTRUCTION) ||
            tokenType.equals(DATypes.IMMREGINSTRUCTION) ||
            tokenType.equals(DATypes.IMMINSTRUCTION) ||
            tokenType.equals(DATypes.IMMOPTINSTRUCTION) ||
            tokenType.equals(DATypes.INDCALCREGINSTRUCTION) ||
            tokenType.equals(DATypes.INDREGREGINSTRUCTION) ||
            tokenType.equals(DATypes.REGINDCALCINSTRUCTION) ||
            tokenType.equals(DATypes.REGINDREGINSTRUCTION) ||
            tokenType.equals(DATypes.REGINSTRUCTION)
        ) {
            return new TextAttributesKey[]{INSTRUCTION};
        } else if (tokenType.equals(DATypes.COMMENTTEXT) || tokenType.equals(DATypes.COMMENTCHAR)) {
            return new TextAttributesKey[]{COMMENT};
        } else if(tokenType.equals(DATypes.STRINGTEXT)) {
            return new TextAttributesKey[]{STRING};
        } else if(tokenType.equals(DATypes.HEXNUM)) {
            return new TextAttributesKey[]{HEXNUM};
        } else if(tokenType.equals(DATypes.LABELDEFINITION)) {
            return new TextAttributesKey[]{LABEL};
        } else if(tokenType.equals(DATypes.REGNUMBER)) {
            return new TextAttributesKey[]{REGISTER};
        }
        return new TextAttributesKey[0];
    }
}
