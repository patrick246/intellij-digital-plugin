package de.patrick246.intellij.digitalplugin.debugger.runner;

import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.NopProcessHandler;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import de.neemann.assembler.asm.InstructionException;
import de.neemann.assembler.asm.Program;
import de.neemann.assembler.expression.ExpressionException;
import de.neemann.assembler.parser.Parser;
import de.neemann.assembler.parser.ParserException;
import de.patrick246.intellij.digitalplugin.component.CurrentExecutionComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

public class DARunProfileState implements RunProfileState {

    private Project project;
    private String path;
    private boolean debugMode;
    private Program program;

    public DARunProfileState(Project project, String path, boolean debugMode) {
        this.project = project;
        this.path = path;
        this.debugMode = debugMode;
        try(Parser parser = new Parser(new File(path.substring(0, path.length() - 4)))) {
            this.program = parser.parseProgram().optimizeAndLink();
        } catch (IOException | ParserException | ExpressionException | InstructionException e) {
            throw new IllegalStateException("Could not assemble program: " + e.getMessage());
        }
    }

    @Nullable
    @Override
    public ExecutionResult execute(Executor executor, @NotNull ProgramRunner runner) throws ExecutionException {
        ConsoleView console = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        CurrentExecutionComponent currentExecution = project.getComponent(CurrentExecutionComponent.class);
        try {
            DigitalHexRunner.RunState runState = debugMode ? DigitalHexRunner.RunState.DEBUGGING : DigitalHexRunner.RunState.RUNNING;
            DigitalHexRunner digitalHexRunner = new DigitalHexRunner(project, console, path, runState, program);
            currentExecution.setRunner(digitalHexRunner);
            ApplicationManager.getApplication().executeOnPooledThread(digitalHexRunner);
            return new DefaultExecutionResult(console, new DAProcessHandler(digitalHexRunner));
        } catch (UnknownHostException e) {
            console.print("Error: " + e.getLocalizedMessage(), ConsoleViewContentType.ERROR_OUTPUT);
        }

        return new DefaultExecutionResult(console, new NopProcessHandler());
    }
}
