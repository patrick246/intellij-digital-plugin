package de.patrick246.intellij.digitalplugin.module;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class DAModuleType extends ModuleType<DAModuleBuilder> {

    private static final String ID = "DIGITAL_ASSEMBLER_MODULE_TYPE";

    public DAModuleType() {
        super(ID);
    }

    public static DAModuleType getInstance() {
        return (DAModuleType) ModuleTypeManager.getInstance().findByID(ID);
    }

    @NotNull
    @Override
    public DAModuleBuilder createModuleBuilder() {
        return new DAModuleBuilder();
    }

    @NotNull
    @Override
    public String getName() {
        return "Digital Assembler";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "An Assembler project for the Digital simulator assembler by hneemann";
    }

    @Override
    public Icon getNodeIcon(boolean isOpened) {
        return AllIcons.General.Information;
    }

    @NotNull
    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull DAModuleBuilder moduleBuilder, @NotNull ModulesProvider modulesProvider) {
        return super.createWizardSteps(wizardContext, moduleBuilder, modulesProvider);
    }
}
