package de.patrick246.intellij.digitalassembler.jpsplugin;

import de.neemann.assembler.asm.InstructionException;
import de.neemann.assembler.asm.Program;
import de.neemann.assembler.asm.formatter.HexFormatter;
import de.neemann.assembler.expression.ExpressionException;
import de.neemann.assembler.parser.Parser;
import de.neemann.assembler.parser.ParserException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildOutputConsumer;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.ProjectBuildException;
import org.jetbrains.jps.incremental.TargetBuilder;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;

public class DABuilder extends TargetBuilder<DABuildRootDescriptor, DABuildTarget> {

    public static final String COMPILER_NAME = "Digital Assembler";

    public DABuilder() {
        super(Collections.singletonList(DABuildTargetType.ASSEMBLE));
    }

    @Override
    public void build(@NotNull DABuildTarget target, @NotNull DirtyFilesHolder<DABuildRootDescriptor, DABuildTarget> holder, @NotNull BuildOutputConsumer outputConsumer, @NotNull CompileContext context) throws ProjectBuildException, IOException {
        holder.processDirtyFiles((buildTarget, file, root) -> {

            if(!file.getName().endsWith(".dasm")) {
                return true;
            }

            File targetFile = new File(file.getPath() + ".hex");


            try(Parser parser = new Parser(file)) {
                Program program = parser.parseProgram().optimizeAndLink();
                program.traverse(new HexFormatter(new PrintStream(targetFile)));

                outputConsumer.registerOutputFile(targetFile, Collections.singletonList(file.getPath()));
            } catch (IOException e) {
                context.processMessage(new CompilerMessage(COMPILER_NAME, BuildMessage.Kind.ERROR, "IOException during build, " + e.getLocalizedMessage()));
            } catch (ParserException e) {
                context.processMessage(new CompilerMessage(COMPILER_NAME, BuildMessage.Kind.ERROR, "Parser error: " + e.getMessage()));
            } catch (ExpressionException e) {
                context.processMessage(new CompilerMessage(COMPILER_NAME, BuildMessage.Kind.ERROR, "Expression error: " + e.getMessage()));
            } catch (InstructionException e) {
                context.processMessage(new CompilerMessage(COMPILER_NAME, BuildMessage.Kind.ERROR, "Instruction error: " + e.getMessage()));
            }
            return true;
        });
    }

    @NotNull
    @Override
    public String getPresentableName() {
        return "DA Build";
    }
}
