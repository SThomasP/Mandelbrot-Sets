import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Steffan on 11/03/2016.
 */
public class ExtensionFileFilter extends FileFilter {

    private String extension;
    private String fileType;

    public ExtensionFileFilter(String extension, String fileType) {
        this.extension = extension;
        this.fileType = fileType;
    }

    //shows the files if they have the correct extension or if they are directories
    public boolean accept(File f) {
        String fileName = f.getName();
        int extensionDot = fileName.lastIndexOf('.');
        return (fileName.substring(extensionDot + 1).equalsIgnoreCase(extension) || f.isDirectory());
    }

    //simple get Description method for implementing files
    public String getDescription() {
        return extension + " " + fileType;
    }

    public String getExtension() {
        return extension;
    }
}
