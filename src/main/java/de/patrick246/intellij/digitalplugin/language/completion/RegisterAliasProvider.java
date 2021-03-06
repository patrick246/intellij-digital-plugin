package de.patrick246.intellij.digitalplugin.language.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import de.patrick246.intellij.digitalplugin.language.psi.DARegisterAliasDefinition;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class RegisterAliasProvider extends CompletionProvider<CompletionParameters> {
    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
        List<LookupElementBuilder> aliases = PsiTreeUtil.collectElementsOfType(parameters.getOriginalFile(), DARegisterAliasDefinition.class)
                .stream()
                .map(element -> LookupElementBuilder
                        .create(element.getText())
                        .withTailText(nextSiblingWithoutWhitespace(element.getParent()).getText(), true)
                        .withTypeText(".reg"))
                .collect(Collectors.toList());
        result.addAllElements(aliases);
    }

    private PsiElement nextSiblingWithoutWhitespace(PsiElement element) {
        PsiElement sibling = element;
        while(sibling instanceof PsiWhiteSpace) {
            sibling = sibling.getNextSibling();
        }
        return sibling;
    }
}
