package de.patrick246.intellij.digitalplugin.language.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.PlatformPatterns;
import de.patrick246.intellij.digitalplugin.language.DALanguage;
import de.patrick246.intellij.digitalplugin.language.psi.DATypes;

public class DACompletionContributor extends CompletionContributor {
    public DACompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(DATypes.ALIAS).withLanguage(DALanguage.INSTANCE), new RegisterProvider());
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(DATypes.ALIAS).withLanguage(DALanguage.INSTANCE), new RegisterAliasProvider());
    }
}
