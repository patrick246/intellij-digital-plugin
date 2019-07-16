package de.patrick246.intellij.digitalplugin.debugger.configuration;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.LocatableConfigurationBase;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import de.patrick246.intellij.digitalplugin.debugger.runner.DARunProfileState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DARunConfiguration extends LocatableConfigurationBase implements PersistentStateComponent<DARunConfiguration.RunConfigState> {

    public static class RunConfigState {
        private String asmFilePath = "";
        private boolean debugMode = false;

        public String getAsmFilePath() {
            return asmFilePath;
        }

        public void setAsmFilePath(String asmFilePath) {
            this.asmFilePath = asmFilePath;
        }

        public boolean isDebugMode() {
            return debugMode;
        }

        public void setDebugMode(boolean debugMode) {
            this.debugMode = debugMode;
        }
    }

    private RunConfigState state;

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
        return new DARunProfileState(getProject(), state.asmFilePath, state.debugMode);
    }

    public String getAsmFilePath() {
        return state.asmFilePath;
    }

    public void setAsmFilePath(String asmFilePath) {
        state.asmFilePath = asmFilePath;
    }

    public boolean getDebugMode() {
        return state.debugMode;
    }

    public void setDebugMode(boolean mode) {
        state.debugMode = mode;
    }

    @Nullable
    @Override
    public RunConfigState getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull RunConfigState state) {
        this.state = state;
    }
}
