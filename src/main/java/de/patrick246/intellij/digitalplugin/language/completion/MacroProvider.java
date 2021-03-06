package de.patrick246.intellij.digitalplugin.language.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.ElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import de.patrick246.intellij.digitalplugin.language.psi.DAFile;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class MacroProvider extends CompletionProvider<CompletionParameters> {

    static final ElementPattern<? extends PsiElement> PATTERN = psiElement()
            .withParent(DAFile.class);

    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
        result.addAllElements(Stream.of(
                "POP",
                "RET",
                "CALL",
                "DEC",
                "LEAVE",
                "LEAVEI",
                "ENTER",
                "ENTERI",
                "_SCALL",
                "PUSH",
                "INC"

        ).map(instruction -> LookupElementBuilder.create(instruction).withTypeText("Macro"))
        .collect(Collectors.toList()));
    }
}
