package de.patrick246.intellij.digitalplugin.language.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import de.patrick246.intellij.digitalplugin.language.psi.DAImmediateAliasDefinition;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class ConstAliasProvider extends CompletionProvider<CompletionParameters> {
    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
        List<LookupElementBuilder> aliases = PsiTreeUtil.collectElementsOfType(parameters.getOriginalFile(), DAImmediateAliasDefinition.class)
                .stream()
                .map(element -> LookupElementBuilder.create(element.getText()).withTypeText(".const"))
                .collect(Collectors.toList());
        result.addAllElements(aliases);
    }
}
