package de.patrick246.intellij.digitalplugin.psi;

import com.intellij.psi.tree.IElementType;
import de.patrick246.intellij.digitalplugin.DALanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class DATokenType extends IElementType {
    public DATokenType(@NotNull @NonNls String debugName) {
        super(debugName, DALanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "DATokenType." + super.toString();
    }
}
