package de.patrick246.intellij.digitalassembler.jpsplugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildTargetLoader;
import org.jetbrains.jps.builders.BuildTargetType;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.JpsUrlList;
import org.jetbrains.jps.model.module.JpsModule;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DABuildTargetType extends BuildTargetType<DABuildTarget> {

    public static final DABuildTargetType ASSEMBLE = new DABuildTargetType();

    public DABuildTargetType() {
        super("DABuildTargetType");
    }

    @NotNull
    @Override
    public List<DABuildTarget> computeAllTargets(@NotNull JpsModel model) {
        return model.getProject().getModules()
                .stream()
                .map(JpsModule::getContentRootsList)
                .map(JpsUrlList::getUrls)
                .flatMap(Collection::stream)
                .map(this::uriHelper)
                .filter(Objects::nonNull)
                .map(Paths::get)
                .flatMap(this::walkHelper)
                .filter(path -> path.getFileName().toString().endsWith(".dasm"))
                .map(path -> new DABuildTarget(path.toString()))
                .collect(Collectors.toList());
    }

    private Stream<Path> walkHelper(Path path) {
        try {
            return Files.walk(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.empty();
    }

    private URI uriHelper(String uri) {
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }


    @NotNull
    @Override
    public BuildTargetLoader<DABuildTarget> createLoader(@NotNull JpsModel model) {
        return new DABuildTargetLoader();
    }


}
