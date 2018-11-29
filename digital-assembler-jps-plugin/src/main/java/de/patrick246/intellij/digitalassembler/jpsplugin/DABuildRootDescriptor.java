package de.patrick246.intellij.digitalassembler.jpsplugin;

import org.jetbrains.jps.builders.BuildRootDescriptor;
import org.jetbrains.jps.builders.BuildTarget;
import org.jetbrains.jps.model.module.JpsModuleSourceRoot;

import java.io.File;

public class DABuildRootDescriptor extends BuildRootDescriptor {

    private File contentRoot;
    private DABuildTarget target;

    public DABuildRootDescriptor(File contentRoot, DABuildTarget target) {
        this.contentRoot = contentRoot;
        this.target = target;
    }

    @Override
    public String getRootId() {
        return contentRoot.getAbsolutePath();
    }

    @Override
    public File getRootFile() {
        return contentRoot;
    }

    @Override
    public BuildTarget<?> getTarget() {
        return target;
    }
}
