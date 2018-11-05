package de.patrick246.intellij.digitalplugin.toolset;

import com.intellij.openapi.projectRoots.*;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class DASdk extends SdkType {
    public DASdk() {
        super("Digital Assembler");
    }

    @Nullable
    @Override
    public String suggestHomePath() {
        return null;
    }

    @Override
    public boolean isValidSdkHome(String path) {
        return Arrays.stream(Objects.requireNonNull((new File(path)).listFiles())).anyMatch(file -> file.getName().equals("asm3.jar"));
    }

    @Override
    public String suggestSdkName(String currentSdkName, String sdkHome) {
        return "Neemann Assembler SDK";
    }

    @Nullable
    @Override
    public AdditionalDataConfigurable createAdditionalDataConfigurable(@NotNull SdkModel sdkModel, @NotNull SdkModificator sdkModificator) {
        return null;
    }

    @NotNull
    @Override
    public String getPresentableName() {
        return "Neemann Assembler";
    }

    @Override
    public void saveAdditionalData(@NotNull SdkAdditionalData additionalData, @NotNull Element additional) {

    }
}
