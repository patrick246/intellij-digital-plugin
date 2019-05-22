package de.patrick246.intellij.digitalplugin.component;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.HighlighterLayer;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;

import java.awt.*;
import java.io.File;

public class DebugHighlighter implements ProjectComponent {

    private Project project;

    public DebugHighlighter(Project project) {
        this.project = project;
    }

    public void highlightLine(Editor editor, File file, int line) {
        VirtualFile vfile = LocalFileSystem.getInstance().findFileByIoFile(file);
        if(vfile == null) {
            return;
        }

        new OpenFileDescriptor(project, vfile, line, 0).navigate(true);

        editor.getMarkupModel().removeAllHighlighters();
        editor.getMarkupModel().addLineHighlighter(
                line,
                HighlighterLayer.LAST - 1,
                TextAttributes.merge(
                        editor.getCaretModel().getTextAttributes(),
                        new TextAttributes(null, JBColor.LIGHT_GRAY, null, null, Font.PLAIN)
                ));
    }
}
