import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Steffan on 11/03/2016.
 */
public class ExtensionFileFilter extends FileFilter {

    private String extension;

    public ExtensionFileFilter(String extension) {
        this.extension = extension;
    }

    //shows the files if they have the correct extension or if they are directories
    public boolean accept(File f) {
        return f.getName().endsWith(extension) || f.isDirectory();
    }

    //simple get Description method for implementing files
    public String getDescription() {
        return "." + extension + " files";
    }

    public String getExtension() {
        return extension;
    }
}
