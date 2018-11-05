package de.patrick246.intellij.digitalplugin.debugger.configuration;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationBase;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DARunConfiguration extends RunConfigurationBase {
    private String asmFilePath = "";

    protected DARunConfiguration(@NotNull Project project, @Nullable ConfigurationFactory factory) {
        super(project, factory, "DARunConfiguration");
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new DASettingsEditor(getProject());
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException {
        return null;
    }

    public String getAsmFilePath() {
        return asmFilePath;
    }

    public void setAsmFilePath(String asmFilePath) {
        this.asmFilePath = asmFilePath;
    }
}
