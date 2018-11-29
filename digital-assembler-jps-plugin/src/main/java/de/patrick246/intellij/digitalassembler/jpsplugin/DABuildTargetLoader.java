package de.patrick246.intellij.digitalassembler.jpsplugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.BuildTargetLoader;

public class DABuildTargetLoader extends BuildTargetLoader<DABuildTarget> {
    @Nullable
    @Override
    public DABuildTarget createTarget(@NotNull String targetId) {
        return new DABuildTarget(targetId);
    }
}
