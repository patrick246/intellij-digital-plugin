package de.patrick246.intellij.digitalplugin.language.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import de.patrick246.intellij.digitalplugin.language.DAFileType;
import de.patrick246.intellij.digitalplugin.language.DALanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DAFile extends PsiFileBase {
    public DAFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, DALanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return DAFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Digital Assembler File";
    }

    @Nullable
    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
