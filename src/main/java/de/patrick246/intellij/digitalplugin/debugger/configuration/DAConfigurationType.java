package de.patrick246.intellij.digitalplugin.debugger.configuration;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import de.patrick246.intellij.digitalplugin.language.DAIcons;
import org.jetbrains.annotations.NotNull;

public class DAConfigurationType extends ConfigurationTypeBase {
    public DAConfigurationType() {
        super("DAConfiguration", "Digital", "Run with Digital Circuit Simulator", DAIcons.FILE);
    }

    @NotNull
    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{new DAConfigurationFactory(this)};
    }
}
