package de.patrick246.intellij.digitalplugin.debugger.configuration;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class DASettingsEditor extends SettingsEditor<DARunConfiguration> {

    private JComponent panel;
    private LabeledComponent<ComponentWithBrowseButton> asmFile;
    private TextFieldWithBrowseButton textFieldWithBrowseButton;


    public DASettingsEditor(Project project) {
        textFieldWithBrowseButton = new TextFieldWithBrowseButton();
        textFieldWithBrowseButton.addBrowseFolderListener("ASM File", "Choose an assembler file", project, FileChooserDescriptorFactory.createSingleFileDescriptor());
        asmFile.setComponent(textFieldWithBrowseButton);
    }

    @Override
    protected void resetEditorFrom(@NotNull DARunConfiguration s) {
        this.textFieldWithBrowseButton.setText(s.getAsmFilePath());
    }

    @Override
    protected void applyEditorTo(@NotNull DARunConfiguration s) {
        s.setAsmFilePath(this.textFieldWithBrowseButton.getText());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return panel;
    }

    private void createUIComponents() { // NOSONAR: this is called by .form file
        asmFile = new LabeledComponent<>();

    }
}
