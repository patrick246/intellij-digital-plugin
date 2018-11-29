package de.patrick246.intellij.digitalplugin.compiler;

import com.intellij.compiler.impl.BuildTargetScopeProvider;
import com.intellij.openapi.compiler.CompileScope;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import de.patrick246.intellij.digitalassembler.jpsplugin.DABuildTargetType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.api.CmdlineProtoUtil;
import org.jetbrains.jps.api.CmdlineRemoteProto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DABuildTargetScopeProvider extends BuildTargetScopeProvider {
    @NotNull
    @Override
    public List<CmdlineRemoteProto.Message.ControllerMessage.ParametersMessage.TargetTypeBuildScope> getBuildTargetScopes(@NotNull CompileScope baseScope, @NotNull Project project, boolean forceBuild) {
        final List<String> fileNames = new ArrayList<>();
        Module[] modules = baseScope.getAffectedModules();
        for (Module module : modules) {
            VirtualFile[] sourceRoots = ModuleRootManager.getInstance(module).getContentRoots();
            for (VirtualFile file : sourceRoots) {
                VfsUtilCore.iterateChildrenRecursively(file, searchFile -> searchFile.isDirectory() || searchFile.getName().endsWith(".dasm"), foundFile -> {
                    if (!foundFile.isDirectory()) {
                        fileNames.add(foundFile.getPath());
                    }
                    return true;
                });
            }
        }
        return Collections.singletonList(CmdlineProtoUtil.createTargetsScope(DABuildTargetType.ASSEMBLE.getTypeId(), fileNames, forceBuild));
    }
}
