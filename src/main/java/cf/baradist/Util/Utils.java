package cf.baradist.Util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Utils {
    public static int copyURLToFile(URL source, File destination) throws IOException {
        InputStream input = source.openStream();
        return copyInputStreamToFile(input, destination);
    }

    public static int copyInputStreamToFile(InputStream source, File destination) throws IOException {
        int copied = -1;
        try {
            FileOutputStream output = FileUtils.openOutputStream(destination);
            try {
                copied = IOUtils.copy(source, output);
                output.close(); // don't swallow close Exception if copy completes normally
            } finally {
                IOUtils.closeQuietly(output);
            }
        } finally {
            IOUtils.closeQuietly(source);
        }
        return copied;
    }
}