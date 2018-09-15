package de.patrick246.intellij.digitalplugin.language.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.PlatformPatterns;
import de.patrick246.intellij.digitalplugin.language.DALanguage;
import de.patrick246.intellij.digitalplugin.language.psi.DAConstalias;
import de.patrick246.intellij.digitalplugin.language.psi.DARegRegInstructionLine;
import de.patrick246.intellij.digitalplugin.language.psi.DARegister;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;

public class DACompletionContributor extends CompletionContributor {
    public DACompletionContributor() {
        extend(CompletionType.BASIC, psiElement().withParent(DARegister.class), new RegisterProvider());
        extend(CompletionType.BASIC, psiElement().withParent(DARegister.class), new RegisterAliasProvider());
        extend(CompletionType.BASIC, psiElement().withParent(DAConstalias.class), new ConstAliasProvider());
    }

    @Override
    public void fillCompletionVariants(@NotNull CompletionParameters parameters, @NotNull CompletionResultSet result) {
        super.fillCompletionVariants(parameters, result);
    }
}
