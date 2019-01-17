package de.patrick246.intellij.digitalassembler.jpsplugin;

import org.jetbrains.jps.builders.BuildRootDescriptor;
import org.jetbrains.jps.builders.BuildTarget;
import org.jetbrains.jps.model.module.JpsModule;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class DABuildRootDescriptor extends BuildRootDescriptor {

    private JpsModule jpsModule;
    private DABuildTarget target;

    public DABuildRootDescriptor(JpsModule jpsModule, DABuildTarget target) {
        this.jpsModule = jpsModule;
        this.target = target;
    }

    @Override
    public String getRootId() {
        return "DABuildRootDescriptor:" + jpsModule.getName();
    }

    @Override
    public File getRootFile() {

        List<String> urls = jpsModule.getContentRootsList().getUrls();
        if(!urls.isEmpty()) {
            String contentRoot = urls.get(0);
            try {
                return new File(new URI(contentRoot));
            } catch (URISyntaxException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public BuildTarget<?> getTarget() {
        return target;
    }
}
