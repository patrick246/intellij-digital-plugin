package de.patrick246.intellij.digitalplugin.component;

import com.intellij.openapi.components.ProjectComponent;
import de.patrick246.intellij.digitalplugin.debugger.runner.DigitalHexRunner;

public class CurrentExecutionComponent implements ProjectComponent {
    private DigitalHexRunner runner = null;

    public void setRunner(DigitalHexRunner runner) {
        this.runner = runner;
    }

    public void removeRunner() {
        this.runner = null;
    }

    public DigitalHexRunner getRunner() {
        return runner;
    }
}
