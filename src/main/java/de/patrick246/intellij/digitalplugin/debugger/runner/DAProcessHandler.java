package de.patrick246.intellij.digitalplugin.debugger.runner;

import com.intellij.execution.process.ProcessHandler;
import org.jetbrains.annotations.Nullable;

import java.io.OutputStream;

public class DAProcessHandler extends ProcessHandler {

    private DigitalHexRunner digitalHexRunner;

    public DAProcessHandler(DigitalHexRunner digitalHexRunner) {
        this.digitalHexRunner = digitalHexRunner;
    }

    @Override
    protected void destroyProcessImpl() {
        digitalHexRunner.stop();
        notifyProcessTerminated(0);
    }

    @Override
    protected void detachProcessImpl() {
        notifyProcessDetached();
    }

    @Override
    public boolean detachIsDefault() {
        return false;
    }

    @Nullable
    @Override
    public OutputStream getProcessInput() {
        return null;
    }
}
