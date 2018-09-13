package de.patrick246.intellij.digitalplugin.language.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import de.patrick246.intellij.digitalplugin.language.psi.DAAliasRegisterDirectiveLine;
import de.patrick246.intellij.digitalplugin.language.psi.DAFile;
import de.patrick246.intellij.digitalplugin.language.psi.DAVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegisterProvider extends CompletionProvider<CompletionParameters> {
    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
        result.addAllElements(Stream.of(
                "R1",
                "R2",
                "R3",
                "R4",
                "R5",
                "R6",
                "R7",
                "R8",
                "R9",
                "R10",
                "R11",
                "R12",
                "BP",
                "SP",
                "RA"
        ).map(LookupElementBuilder::create).collect(Collectors.toList()));
    }
}
