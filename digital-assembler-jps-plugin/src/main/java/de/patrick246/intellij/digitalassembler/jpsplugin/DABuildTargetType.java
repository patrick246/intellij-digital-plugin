package de.patrick246.intellij.digitalassembler.jpsplugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.BuildTargetLoader;
import org.jetbrains.jps.builders.ModuleBasedBuildTargetType;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.module.JpsModule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DABuildTargetType extends ModuleBasedBuildTargetType<DABuildTarget> {

    public static final DABuildTargetType ASSEMBLE = new DABuildTargetType();

    public DABuildTargetType() {
        super("DABuildTargetType");
    }

    @NotNull
    @Override
    public List<DABuildTarget> computeAllTargets(@NotNull JpsModel model) {
        return model.getProject().getModules()
                .stream()
                .map(DABuildTarget::new)
                .collect(Collectors.toList());
    }


    @NotNull
    @Override
    public BuildTargetLoader<DABuildTarget> createLoader(@NotNull JpsModel model) {
        final Map<String, JpsModule> modules = new HashMap<>();
        for (JpsModule module : model.getProject().getModules()) {
            modules.put(module.getName(), module);
        }
        return new BuildTargetLoader<DABuildTarget>() {
            @Nullable
            @Override
            public DABuildTarget createTarget(@NotNull String targetId) {
                final JpsModule module = modules.get(targetId);
                return module != null ? new DABuildTarget(module) : null;
            }
        };
    }


}
