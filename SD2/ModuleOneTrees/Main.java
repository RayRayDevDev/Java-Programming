import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

class TreePractice {

    public static void main(String[] args) throws Exception {

        Path currentPath = Paths.get("C:/Users/Cole/OneDrive/Documents/GitHub/BeginnerProgrammingJava/Final Project");
        ListDir(currentPath, 0);
    }

    public static void ListDir(Path path, int depth) throws Exception {
        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

        if (attr.isDirectory()) {
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            System.out.println(Depth(depth) + " >" + path.getFileName());
            for (Path tempPath : paths) {
                ListDir(tempPath, depth += 1);
            }
        } else
            System.out.println(Depth(depth) + " --" + path.getFileName());
    }

    public static String Depth(int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append(" ");
        }

        return builder.toString();
    }
}