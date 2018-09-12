package de.patrick246.intellij.digitalplugin.psi;

import com.intellij.psi.tree.IElementType;
import de.patrick246.intellij.digitalplugin.DALanguage;
import org.jetbrains.annotations.NotNull;

public class DAElementType extends IElementType {
    public DAElementType(@NotNull String debugName) {
        super(debugName, DALanguage.INSTANCE);
    }
}
