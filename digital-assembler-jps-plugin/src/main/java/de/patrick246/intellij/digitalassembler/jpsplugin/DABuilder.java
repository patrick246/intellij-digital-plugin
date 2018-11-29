package de.patrick246.intellij.digitalassembler.jpsplugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildOutputConsumer;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.ProjectBuildException;
import org.jetbrains.jps.incremental.TargetBuilder;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;

import java.io.IOException;
import java.util.Collections;

public class DABuilder extends TargetBuilder<DABuildRootDescriptor, DABuildTarget> {
    public DABuilder() {
        super(Collections.singletonList(DABuildTargetType.ASSEMBLE));
    }

    @Override
    public void build(@NotNull DABuildTarget target, @NotNull DirtyFilesHolder<DABuildRootDescriptor, DABuildTarget> holder, @NotNull BuildOutputConsumer outputConsumer, @NotNull CompileContext context) throws ProjectBuildException, IOException {
        context.processMessage(new CompilerMessage("Digital Assembler", BuildMessage.Kind.ERROR, "Hello World"));
    }

    @NotNull
    @Override
    public String getPresentableName() {
        return "DA Build";
    }
}
