package de.patrick246.intellij.digitalplugin.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.ModifiableRootModel;

public class DAModuleBuilder extends ModuleBuilder {
    @Override
    public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {
        modifiableRootModel.addContentEntry(
                modifiableRootModel.getProject().getBaseDir()
        );
    }

    @Override
    public ModuleType getModuleType() {
        return DAModuleType.getInstance();
    }

    @Override
    public boolean isSuitableSdkType(SdkTypeId sdkType) {
        return sdkType.getName().equals("Digital Assembler");
    }
}
