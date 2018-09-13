package de.patrick246.intellij.digitalplugin.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DAFileType extends LanguageFileType {
    public static final DAFileType INSTANCE = new DAFileType();

    private DAFileType() {
        super(DALanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Digital Assembler";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Digital Assembler file, for the processor inside the Digital Circuit Simulator by Helmut Neemann";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "dasm";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return DAIcons.FILE;
    }
}
