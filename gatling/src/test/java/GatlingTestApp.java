import io.gatling.app.Gatling;
import io.gatling.core.config.GatlingPropertiesBuilder;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

public class GatlingTestApp {

    public static void main(String[] args) {
        GatlingPropertiesBuilder props = new GatlingPropertiesBuilder()
                .resourcesDirectory(PathFinder.mavenResourcesDirectory.toString())
                .resultsDirectory(PathFinder.resultsDirectory.toString())
                .binariesDirectory(PathFinder.mavenBinariesDirectory.toString());
        Gatling.fromMap(props.build());
    }

    static class PathFinder {

        static final Path mavenSourcesDirectory;
        static final Path mavenResourcesDirectory;
        static final Path mavenBinariesDirectory;
        static final Path resultsDirectory;

        static {
            try {
                Path projectRootDir = Paths
                        .get(requireNonNull(PathFinder.class.getResource("gatling.conf"), "Couldn't locate gatling.conf")
                                .toURI()).getParent().getParent().getParent();
                Path mavenTargetDirectory = projectRootDir.resolve("target");
                Path mavenSrcTestDirectory = projectRootDir.resolve("src").resolve("test");

                mavenSourcesDirectory = mavenSrcTestDirectory.resolve("java");
                mavenResourcesDirectory = mavenSrcTestDirectory.resolve("resources");
                mavenBinariesDirectory = mavenTargetDirectory.resolve("test-classes");
                resultsDirectory = mavenTargetDirectory.resolve("gatling");
            } catch (URISyntaxException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }


}
