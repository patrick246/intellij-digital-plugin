package de.patrick246.intellij.digitalassembler.jpsplugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.BuildRootIndex;
import org.jetbrains.jps.builders.BuildTarget;
import org.jetbrains.jps.builders.BuildTargetRegistry;
import org.jetbrains.jps.builders.TargetOutputIndex;
import org.jetbrains.jps.builders.storage.BuildDataPaths;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.indices.IgnoredFileIndex;
import org.jetbrains.jps.indices.ModuleExcludeIndex;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.module.JpsModuleSourceRoot;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DABuildTarget extends BuildTarget<DABuildRootDescriptor> {
    private File asmFile;

    public DABuildTarget(String targetId) {
        super(DABuildTargetType.ASSEMBLE);
        this.asmFile = new File(targetId);
    }

    @Override
    public String getId() {
        return "DABuildTarget:" + asmFile.getAbsolutePath();
    }

    @Override
    public Collection<BuildTarget<?>> computeDependencies(BuildTargetRegistry targetRegistry, TargetOutputIndex outputIndex) {
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public List<DABuildRootDescriptor> computeRootDescriptors(JpsModel model, ModuleExcludeIndex index, IgnoredFileIndex ignoredFileIndex, BuildDataPaths dataPaths) {
        return Collections.singletonList(new DABuildRootDescriptor(asmFile, this));
    }

    @Nullable
    @Override
    public DABuildRootDescriptor findRootDescriptor(String rootId, BuildRootIndex rootIndex) {
        return new DABuildRootDescriptor(asmFile, this);
    }

    @NotNull
    @Override
    public String getPresentableName() {
        return "Assembling " + asmFile.getName();
    }

    @NotNull
    @Override
    public Collection<File> getOutputRoots(CompileContext context) {
        return Collections.singletonList(asmFile.getParentFile());
    }
}
