package de.patrick246.intellij.digitalplugin.debugger.runner;

import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;
import de.neemann.assembler.asm.Program;
import de.neemann.assembler.gui.RemoteException;
import de.neemann.assembler.gui.RemoteInterface;
import de.patrick246.intellij.digitalplugin.component.CurrentExecutionComponent;

import java.io.File;
import java.net.UnknownHostException;

public class DigitalHexRunner implements Runnable {

    private RemoteInterface remote;
    private ConsoleView console;
    private String hexFile;
    private Project project;
    private Program program;

    enum RunState {
        DEBUGGING,
        RUNNING
    }

    private RunState runState;

    public DigitalHexRunner(Project project, ConsoleView console, String hexFile, RunState state, Program program) throws UnknownHostException {
        this.remote = new RemoteInterface();
        this.console = console;
        this.hexFile = hexFile;
        this.project = project;
        this.runState = state;
        this.program = program;
    }

    @Override
    public void run() {
        try {
            console.print("Flashing ROM...\n", ConsoleViewContentType.NORMAL_OUTPUT);
            if(runState == RunState.RUNNING) {
                remote.start(new File(hexFile));
            } else {
                remote.debug(new File(hexFile));
            }

            console.print("Successfully flashed\n", ConsoleViewContentType.NORMAL_OUTPUT);
        } catch (RemoteException e) {
            console.print("Error: " + e.getLocalizedMessage() + "\n", ConsoleViewContentType.ERROR_OUTPUT);
        }
    }

    public void stop() {
        try {
            remote.stop();
        } catch (RemoteException e) {
            console.print("Error: " + e.getLocalizedMessage() + "\n", ConsoleViewContentType.ERROR_OUTPUT);
        } finally {
            CurrentExecutionComponent currentExecution = project.getComponent(CurrentExecutionComponent.class);
            currentExecution.removeRunner();
        }
    }

    public int singleStep() {
        if(runState != RunState.DEBUGGING) {
            return -1;
        }

        try {
            int address = remote.step();
            if(address != -1) {
                int line = program.getLineByAddr(address);
                console.print("Next line: " + line + "\n", ConsoleViewContentType.LOG_INFO_OUTPUT);
                return line;
            }
            return -1;

        } catch (RemoteException e) {
            console.print("Error: "+ e.getLocalizedMessage() + "\n", ConsoleViewContentType.ERROR_OUTPUT );
        }
        return -1;
    }

    public int runUntilBreak() {
        try {
            int address = remote.run();
            if(address != -1) {
                int line = program.getLineByAddr(address);
                console.print("Next line: " + line + "\n", ConsoleViewContentType.LOG_INFO_OUTPUT);
                return line;
            }
            console.print("Address " + address, ConsoleViewContentType.LOG_INFO_OUTPUT);
        } catch (RemoteException e) {
            console.print("Error: " + e.getLocalizedMessage() + "\n", ConsoleViewContentType.ERROR_OUTPUT);
        }
        return -1;
    }

    public String getHexFile() {
        return hexFile;
    }
}
