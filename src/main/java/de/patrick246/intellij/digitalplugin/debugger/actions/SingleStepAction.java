package de.patrick246.intellij.digitalplugin.debugger.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import de.patrick246.intellij.digitalplugin.component.CurrentExecutionComponent;
import de.patrick246.intellij.digitalplugin.component.DebugHighlighter;
import de.patrick246.intellij.digitalplugin.debugger.runner.DigitalHexRunner;

import java.io.File;

public class SingleStepAction extends AnAction {

    public SingleStepAction() {

    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();

        if(project != null) {
            CurrentExecutionComponent currentExecutionComponent = project.getComponent(CurrentExecutionComponent.class);
            DigitalHexRunner runner = currentExecutionComponent.getRunner();
            if(runner != null) {
                int line = runner.singleStep();

                String hexFile = runner.getHexFile();
                String asmFile = hexFile.substring(0, hexFile.lastIndexOf('.'));

                Editor editor = e.getData(CommonDataKeys.HOST_EDITOR);
                if(editor == null || line == -1 || line == 0) {
                    return;
                } else {
                    line--;
                }

                DebugHighlighter highlighter = project.getComponent(DebugHighlighter.class);
                highlighter.highlightLine(editor, new File(asmFile), line);
            }
        }
    }

    @Override
    public void update(AnActionEvent e) {
        Project project = e.getProject();
        if (project != null) {
            CurrentExecutionComponent currentExecutionComponent = project.getComponent(CurrentExecutionComponent.class);
            e.getPresentation().setEnabledAndVisible(currentExecutionComponent.getRunner() != null);
        } else {
            e.getPresentation().setEnabledAndVisible(false);
        }
    }
}
