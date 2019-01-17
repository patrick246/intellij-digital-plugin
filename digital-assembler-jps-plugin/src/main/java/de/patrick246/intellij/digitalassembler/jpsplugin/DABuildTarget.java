package de.patrick246.intellij.digitalassembler.jpsplugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.*;
import org.jetbrains.jps.builders.storage.BuildDataPaths;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.indices.IgnoredFileIndex;
import org.jetbrains.jps.indices.ModuleExcludeIndex;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.module.JpsModule;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DABuildTarget extends ModuleBasedTarget<DABuildRootDescriptor> {

    public DABuildTarget(JpsModule module) {
        super(DABuildTargetType.ASSEMBLE, module);
    }

    @Override
    public boolean isTests() {
        return false;
    }

    @Override
    public String getId() {
        return "DABuildTarget:" + getModule().getName();
    }

    @Override
    public Collection<BuildTarget<?>> computeDependencies(BuildTargetRegistry targetRegistry, TargetOutputIndex outputIndex) {
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<DABuildRootDescriptor> computeRootDescriptors(JpsModel model, ModuleExcludeIndex index, IgnoredFileIndex ignoredFileIndex, BuildDataPaths dataPaths) {
        return Collections.singletonList(new DABuildRootDescriptor(getModule(), this));
    }

    @Nullable
    @Override
    public DABuildRootDescriptor findRootDescriptor(String rootId, BuildRootIndex rootIndex) {
        return new DABuildRootDescriptor(getModule(), this);
    }

    @NotNull
    @Override
    public String getPresentableName() {
        return "Assembling module " + getModule().getName();
    }

    @NotNull
    @Override
    public Collection<File> getOutputRoots(CompileContext context) {
        return Collections.singletonList(new DABuildRootDescriptor(getModule(), this).getRootFile());
    }
}
